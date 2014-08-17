$(document).ready(function(){
	$('#btn_save').click(function(){
		save();
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

function save() {
	var name = $.trim($('#name').val());
	var statTime = $.trim($('#statTime').val());
	var serverName = $.trim($('#serverName').val());
	var url = $.trim($('#url').val());
	var category = $.trim($('#category').val());
	var platform = $.trim($('#platform').val());
	var account = $.trim($('#account').val());
	var id = $.trim($('#id').val());
	
	if(utils.isEmpty(name)) {
		utils.alert('#sys_msg', '游戏名称不能为空', 'danger');
		return false;
	}
	if(utils.isEmpty(statTime)) {
		utils.alert('#sys_msg', '开始时间不能为空', 'danger');
		return false;
	}
	if(utils.isEmpty(serverName)) {
		utils.alert('#sys_msg', '服务器名不能为空', 'danger');
		return false;
	}
	if(utils.isEmpty(url)) {
		utils.alert('#sys_msg', 'URL不能为空', 'danger');
		return false;
	}
	if(utils.isEmpty(category)) {
		utils.alert('#sys_msg', '游戏类型不能为空', 'danger');
		return false;
	}
	if(utils.isEmpty(platform)) {
		utils.alert('#sys_msg', '运营商不能为空', 'danger');
		return false;
	}
	if(utils.isEmpty(account)) {
		utils.alert('#sys_msg', '账号获取失败', 'danger');
		return false;
	}

	$.ajax({
		type : "POST",
		url : Context.PATH + '/admin/game/save.html?t=' + utils.random(),
		data : {
			name:name,
			statTime:statTime,
			serverName:serverName,
			url:url,
			category:category,
			platform:platform,
			account:account,
			id:id
		},
		dataType : "json",
		success : function(response, textStatus, xhr) {
			if (response.success) {
				utils.alert('#sys_msg', '保存成功', null);
				if(utils.isEmpty(id)) {
					reset();
				}
			} else {
				utils.alert('#sys_msg', response.data, 'danger');
			}
		},
		error : function(xhr, textStatus, errorThrown) {
			utils.alert('#sys_msg', Message.SERVER_ERROR, 'danger');
		}
	});
}

function reg() {
	
	var account = $.trim($('#account').val());
	var password = $.trim($('#password').val());
	var email = $.trim($('#email').val());
	var tel = $.trim($('#tel').val());
	var im = $.trim($('#im').val());

	if(!utils.checkFormat(account,'account')){
		utils.alert("#sys_msg", "账号格式不正确", "danger");
		return false;
	}
	
	if(!utils.checkFormat(password,'password')){
		utils.alert("#sys_msg", "密码格式不正确", "danger");
		return false;
	}
	
//	if($('#password').val()!=$('#password1').val()){
//		utils.alert("#sys_msg", "两次输入的密码不一致", "danger");
//		return false;
//	}
	
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
        url: Context.PATH + '/reg.html?t='+utils.random(),
        data : {
			im: im,
			tel: tel,
            account: account,
			password: password,
			email: email
        },
        dataType:"json",
        success: function(response, textStatus, xhr) {
        	if(response.success){
        		//window.location.href = "/web/index.html";
        	} else {
				utils.alert("#sys_msg", response.data, "danger");
			}
        },
        error: function(xhr, textStatus, errorThrown) {
			utils.alert("#sys_msg", "服务器连接错误", "danger");
        }
    });
}