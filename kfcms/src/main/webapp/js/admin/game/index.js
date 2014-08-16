function doDelete(id){
	var msg = "您真的确定要删除吗？";
	if (confirm(msg)==true){
		del(id);
		return true;
	}else{
		return false;
	} 
}

function del(id, account){
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