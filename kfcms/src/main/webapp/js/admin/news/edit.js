$(document).ready(function(){
	$('#profile-tab a').click(function(e) {
		$(this).tab('show');
	});
	$('#btn_save').click(function(){
		save();
	});
});

function reset(){
	$('input[type="text"]').each(function( index ) {
		$(this).val('');
	});
	
	$('#content').val('');
}

function save() {
	var title = $.trim($('#title').val());
	var content = $.trim($('#content').val());
	var account = $.trim($('#account').val());
	var category = $.trim($('#category').val());
	var status = $.trim($('#status').val());
	var id = $.trim($('#id').val());
	
	if(utils.isEmpty(title)) {
		utils.alert('#sys_msg', '标题不能为空', 'danger');
		return false;
	}
	if(utils.isEmpty(content)) {
		utils.alert('#sys_msg', '内容不能为空', 'danger');
		return false;
	}
	if(utils.isEmpty(account)) {
		utils.alert('#sys_msg', '作者不能为空', 'danger');
		return false;
	}

	$.ajax({
		type : "POST",
		url : Context.PATH + '/admin/news/save.html?t=' + utils.random(),
		data : {
			title:title,
			content:content,
			account:account,
			category:category,
			status:status,
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
				utils.alert('#sys_msg', "操作成功", 'danger');
			}
		},
		error : function(xhr, textStatus, errorThrown) {
			utils.alert('#sys_msg', Message.SERVER_ERROR, 'danger');
		}
	});
}