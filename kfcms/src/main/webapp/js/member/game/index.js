function doDelete(id){
	var msg = "您真的确定要删除吗？";
	if (confirm(msg)==true){
		del(id);
		return true;
	}else{
		return false;
	} 
}

function del(id){
	$.ajax({
        type: "POST",
        url: Context.PATH + '/member/game/delete.html',
        data : {
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