/**
 * Utils
 * 
 * @returns {Utils}
 */
Utils = function() {
};
$.extend(Utils.prototype, {
	/**
	 * get timestamp
	 * @returns
	 */
	random : function() {
		var timestamp = Date.parse(new Date());
		return timestamp;
	},

	/**
	 * @param str
	 * @param ftype
	 * @returns {Boolean} 若符合对应的格式，返回true，否则返回false
	 */
	checkFormat : function(str, ftype) {
		var nReg = Regexs[ftype];
		if (str == null || str == "")
			return false; // 输入为空，认为是验证通过
		if (ftype == 'num') {
			if (nReg.test(str) && chkChinese(str)) {// 10.23 tenfy 必须为数字且不能有中文
				return true;
			} else {
				return false;
			}
		}

		if (nReg.test(str)) {
			return true;
		} else {
			return false;

		}
	}
});

utils = new Utils();

/**
 * 定义验证各种格式类型的正则表达式对象
 */
var Regexs = {
	email : (/^[0-9a-z][0-9a-z\-\_\.]+@([0-9a-z][0-9a-z\-]*\.)+[a-z]{2,}$/i),// 邮箱
	tel : (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/),// 座机号码
	mobile : (/^(((13[0-9]{1})|15[0-9]{1}|18[0-9]{1}|)+\d{8})$/),// 手机号码
	url : (/^http:\/\/([0-9a-z][0-9a-z\-]*\.)+[a-z]{2,}(:\d+)?\/[0-9a-z%\-_\/\.]+/i),// 网址
	num : (/[^0-9]/),// 数字
	account : (/^[A-Za-z0-9]\w{5,16}$/),
	password : (/^[A-Za-z0-9]\w{5,19}$/),
	photo : (/\.jpg$|\.jpeg$|\.gif$/i),// 图片格式
	row : (/\n/ig)
};

/**
 * 
 * @param s
 * @returns {Boolean}
 */
function chkChinese(s) {
	for ( var i = 0; i < s.length; i++) {
		if (s.charCodeAt(i) > 255)
			return true;
	}
	return false;
}