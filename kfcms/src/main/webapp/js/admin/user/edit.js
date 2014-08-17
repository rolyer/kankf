$(document).ready(function(){
	$('#btn_save').click(function(){
		update();
	});
	
	$('#btn_reg').click(function(){
		reg();
	});
});

function reset(){
	$('input[type="text"]').each(function( index ) {
		$(this).val('');
	});
}

function update() {
	var account = $.trim($('#account').val());
	var password = $.trim($('#password').val());
	var email = $.trim($('#email').val());
	var tel = $.trim($('#tel').val());
	var im = $.trim($('#im').val());
	var status = $.trim($('#status').val());
	var id = $.trim($('#id').val());

	if(!utils.checkFormat(account,'account')){
		utils.alert("#sys_msg", "账号格式不正确", "danger");
		return false;
	}
	
	if(!utils.isEmpty(password) && !utils.checkFormat(password,'password')){
		utils.alert("#sys_msg", "密码格式不正确", "danger");
		return false;
	}
	
	if(!utils.checkFormat(email,'email')){
		utils.alert("#sys_msg", "邮箱格式不正确", "danger");
		return false;
	}
	
	if(tel != null && tel != "" && !utils.checkFormat(tel,'mobile') && !utils.checkFormat(tel,'tel')){
		utils.alert("#sys_msg", "电话格式不正确", "danger");
		return false;
	}
	
	$.ajax({
        type: "POST",
        url: Context.PATH + '/admin/user/update.html?t='+utils.random(),
        data : {
			id:id,
			account: account,
			password: password,
			email: email,
			tel: tel,
			im: im,
			status : status
        },
        dataType:"json",
        success: function(response, textStatus, xhr) {
        	if(response.success){
        		utils.alert("#sys_msg", "更新成功", "succes");
        	} else {
				utils.alert("#sys_msg", response.data, "danger");
			}
        },
        error: function(xhr, textStatus, errorThrown) {
			utils.alert("#sys_msg", "服务器连接错误", "danger");
        }
    });
}

function reg() {
	
	var account = $.trim($('#account').val());
	var password = $.trim($('#password').val());
	var email = $.trim($('#email').val());
	var tel = $.trim($('#tel').val());
	var im = $.trim($('#im').val());
	var status = $.trim($('#status').val());

	if(!utils.checkFormat(account,'account')){
		utils.alert("#sys_msg", "账号格式不正确", "danger");
		return false;
	}
	
	if(!utils.checkFormat(password,'password')){
		utils.alert("#sys_msg", "密码格式不正确", "danger");
		return false;
	}
	
	if(!utils.checkFormat(email,'email')){
		utils.alert("#sys_msg", "邮箱格式不正确", "danger");
		return false;
	}
	
	if(tel != null && tel != "" && !utils.checkFormat(tel,'mobile') && !utils.checkFormat(tel,'tel')){
		utils.alert("#sys_msg", "电话格式不正确", "danger");
		return false;
	}
	
	$.ajax({
        type: "POST",
        url: Context.PATH + '/admin/user/add.html?t='+utils.random(),
        data : {
			im: im,
			tel: tel,
            account: account,
			password: password,
			email: email,
			status : status
        },
        dataType:"json",
        success: function(response, textStatus, xhr) {
        	if(response.success){
        		utils.alert("#sys_msg", "添加成功", "danger");
        	} else {
        		var msg = utils.isEmpty(response.data)?"添加失败":response.data;
				utils.alert("#sys_msg", msg, "danger");
			}
        },
        error: function(xhr, textStatus, errorThrown) {
			utils.alert("#sys_msg", "服务器连接错误", "danger");
        }
    });
}