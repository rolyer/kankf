function del(id, account){
	var msg = "您真的确定要删除吗？";
	if (confirm(msg)==false){
		return false;
	} 
	
	$.ajax({
        type: "POST",
        url: Context.PATH + '/admin/game/delete.html',
        data : {
        	account: account,
            id: id
        },
        dataType:"json",
        success: function(response, textStatus, xhr) {
        	if(response.success){
        		$('#game_list_tr_gid_'+id).remove();
        	}
        },
        error: function(xhr, textStatus, errorThrown) {
        	return false;
        }
    });
}