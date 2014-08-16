function del(id){
	var msg = "您真的确定要删除吗？";
	if (confirm(msg)==false){
		return false;
	} 
	
	$.ajax({
        type: "POST",
        url: Context.PATH + '/admin/user/delete.html',
        data : {
            id: id
        },
        dataType:"json",
        success: function(response, textStatus, xhr) {
        	if(response.success){
        		$('#list_tr_gid_'+id).remove();
        	}
        },
        error: function(xhr, textStatus, errorThrown) {
        	return false;
        }
    });
}