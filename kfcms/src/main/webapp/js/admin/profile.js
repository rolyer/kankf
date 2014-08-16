$(function() {
	$('#profile-tab a').click(function(e) {
		$(this).tab('show');
	});
	
	$('#btn_add').click(function(){
		add();
	});
	
	$('#add input').keyup(function(e){
	    if(e.keyCode == 13) {
	    	add();
	    }
	});
	
	$('#btn_save_pwd').click(function(){
		savepwd();
	});
	
	$('#pwd input').keyup(function(e){
	    if(e.keyCode == 13) {
	    	savepwd();
	    }
	});
})

function savepwd(){
	var opwd = $.trim($('#opwd').val());
	var npwd = $.trim($('#npwd').val());
	var secpwd = $.trim($('#secpwd').val());
	
	if(utils.isEmpty(opwd)) {
		utils.alert('#sys_msg', '请输入原密码', 'danger');
		return false;
	}
	if(utils.isEmpty(npwd)) {
		utils.alert('#sys_msg', '请输入新密码', 'danger');
		return false;
	}
	
	if(!utils.checkFormat(npwd,'password')){
		utils.alert('#sys_msg', '新密码格式不正确', 'danger');
		return false;
	}
	
	if(secpwd!=npwd){
		utils.alert('#sys_msg', '两次输入的密码不一致', 'danger')
		return false;
	}
	
	$.ajax({
		type : "POST",
		url : Context.PATH + '/admin/updatepwd.html?t=' + utils.random(),
		data : {
			secpwd : secpwd,
			npwd : npwd,
			opwd : opwd
		},
		dataType : "json",
		success : function(response, textStatus, xhr) {
			if (response.success) {
				$('#npwd').val('');
				$('#opwd').val('');
				$('#secpwd').val('');
				utils.alert('#sys_msg', '密码更新成功', null);
			} else {
				var msg = utils.isEmpty(response.data)?"更新失败":response.data;
				utils.alert('#sys_msg', msg, 'danger');
			}
		},
		error : function(xhr, textStatus, errorThrown) {
			utils.alert('#sys_msg', Message.SERVER_ERROR, 'danger');
		}
	});
}

function add() {
	var account = $.trim($('#account').val());
	var password = $.trim($('#password').val());
	
	if(utils.isEmpty(account)) {
		utils.alert('#sys_msg', '账号不能为空', 'danger');
		return false;
	}
	
	if(!utils.checkFormat(account,'account')){
		utils.alert('#sys_msg', '账号格式不正确', 'danger');
		return false;
	}
	
	if(utils.isEmpty(password)) {
		utils.alert('#sys_msg', '密码不能为空', 'danger');
		return false;
	}
	
	if(!utils.checkFormat(password,'password')){
		utils.alert('#sys_msg', '密码格式不正确', 'danger');
		return false;
	}

	$.ajax({
		type : "POST",
		url : Context.PATH + '/admin/addadmin.html?t=' + utils.random(),
		data : {
			account : account,
			password : password
		},
		dataType : "json",
		success : function(response, textStatus, xhr) {
			if (response.success) {
				$('#account').val('');
				$('#password').val('');
				
				utils.alert('#sys_msg', '账号添加成功', null);
			} else {
				var msg = utils.isEmpty(response.data)?"添加失败":response.data;
				utils.alert('#sys_msg', msg, 'danger');
			}
		},
		error : function(xhr, textStatus, errorThrown) {
			utils.alert('#sys_msg', Message.SERVER_ERROR, 'danger');
		}
	});
}

function del(id) {
	var msg = "您真的确定要删除吗？";
	if (confirm(msg)==false){
		return false;
	} 
	
	if(utils.isEmpty(id)) {
		utils.alert('#sys_msg', '参数错误', 'danger');
		return false;
	}
	
	$.ajax({
		type : "POST",
		url : Context.PATH + '/admin/delete.html?t=' + utils.random(),
		data : {
			id : id
		},
		dataType : "json",
		success : function(response, textStatus, xhr) {
			if (response.success) {
				utils.alert('#sys_msg', '账号删除成功', null);
				
				$('#admin_list_tr_gid_'+id).remove();
			} else {
				var msg = utils.isEmpty(response.data)?"删除失败":response.data;
				utils.alert('#sys_msg', msg, 'danger');
			}
		},
		error : function(xhr, textStatus, errorThrown) {
			utils.alert('#sys_msg', Message.SERVER_ERROR, 'danger');
		}
	});
}