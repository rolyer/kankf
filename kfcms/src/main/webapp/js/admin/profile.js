$(function() {
	$('#profile-tab a').click(function(e) {
		$(this).tab('show');
	});
	
	$('#btn_save_profile').click(function(){
		update();
	});
	
	$('#info input').keyup(function(e){
	    if(e.keyCode == 13) {
	    	update();
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
			npwd : npwd,
			opwd : opwd
		},
		dataType : "json",
		success : function(response, textStatus, xhr) {
			if (response.success) {
				utils.alert('#sys_msg', '密码更新成功', null);
			} else {
				utils.alert('#sys_msg', response.data, 'danger');
			}
		},
		error : function(xhr, textStatus, errorThrown) {
			utils.alert('#sys_msg', Message.SERVER_ERROR, 'danger');
		}
	});
}

function update() {
	var tel = $.trim($('#tel').val());
	var im = $.trim($('#im').val());
	
	if(utils.isEmpty(tel)) {
		utils.alert('#sys_msg', '电话号码不能为空', 'danger');
		return false;
	}
	if(utils.isEmpty(im)) {
		utils.alert('#sys_msg', 'IM号不能为空', 'danger');
		return false;
	}
	if(!utils.checkFormat(tel,'mobile') && !utils.checkFormat(tel,'tel')){
		utils.alert('#sys_msg', '电话格式不正确', 'danger');
		return false;
	}

	$.ajax({
		type : "POST",
		url : Context.PATH + '/admin/updateprofile.html?t=' + utils.random(),
		data : {
			im : im,
			tel : tel
		},
		dataType : "json",
		success : function(response, textStatus, xhr) {
			if (response.success) {
				utils.alert('#sys_msg', '个人信息更新成功', null);
			} else {
				utils.alert('#sys_msg', response.data, 'danger');
			}
		},
		error : function(xhr, textStatus, errorThrown) {
			utils.alert('#sys_msg', Message.SERVER_ERROR, 'danger');
		}
	});
}