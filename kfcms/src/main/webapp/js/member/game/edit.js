$(document).ready(function(){
	$('#btn_save').click(function(){
		save();
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

	$.ajax({
		type : "POST",
		url : Context.PATH + '/member/game/save.html?t=' + utils.random(),
		data : {
			name:name,
			statTime:statTime,
			serverName:serverName,
			url:url,
			category:category,
			platform:platform,
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