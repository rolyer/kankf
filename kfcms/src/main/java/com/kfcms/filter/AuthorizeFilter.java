package com.kfcms.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.kfcms.model.User;
import com.kfcms.util.Constants;

public class AuthorizeFilter implements Filter {
	private String loginURL = "";

	private Set<String> needLoginPage;
	private Set<String> noAuthPage;

	@Override
	public void init(FilterConfig config) throws ServletException {
		loginURL = config.getInitParameter("loginURL");

		String tmp[] = null;
		needLoginPage = new HashSet<String>();
		String e1 = config.getInitParameter("needLoginPage");
		if (StringUtils.isNotEmpty(e1)) {
			tmp = e1.split("\\|");
			for (String ex : tmp) {
				needLoginPage.add(ex);
			}
		}

		noAuthPage = new HashSet<String>();
		String e2 = config.getInitParameter("noAuthPage");
		if (StringUtils.isNotEmpty(e2)) {
			tmp = e2.split("\\|");
			for (String ex : tmp) {
				noAuthPage.add(ex);
			}
		}

	}

	@Override
	public void doFilter(ServletRequest rq, ServletResponse rp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) rq;
		HttpServletResponse response = (HttpServletResponse) rp;

		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		if (!StringUtils.isEmpty(queryString)) {
			queryString = "?" + queryString;
		} else {
			queryString = "";
		}
		String path = request.getContextPath();
		path = path == null ? "" : path;
		String url = loginURL;

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute(Constants.LOGIN_USER);

		do {
			if (canFilter(noAuthPage, path, uri)) {
				chain.doFilter(request, response);
				return;
			}

			if (!canFilter(needLoginPage, path, uri)) { // no need login
				chain.doFilter(request, response);
				return;
			} else {
				if (loginUser != null) {
					chain.doFilter(request, response);
					return;
				}
			}

			if (loginUser == null) { // no login
				url = loginURL;
				break;
			}

		} while (false);

		// ajax request filter
		String redirectUrl = path + url + "?url=" + uri + queryString;
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			response.setHeader("sessionstatus", "timeout");
			response.setHeader("redirectUrl", redirectUrl);
			return;
		}

		response.sendRedirect(redirectUrl);
		return;

	}

	@Override
	public void destroy() {
	}

	/**
	 * 
	 * @param exclude
	 * @param path
	 * @param uri
	 * @return
	 */
	public boolean canFilter(Set<String> exclude, String path, String uri) {
		for (String regex : exclude) {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(uri);
			if (m.find()) {
				return true;
			}
		}
		return false;
	}
}
