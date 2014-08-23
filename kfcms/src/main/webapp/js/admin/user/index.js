$(document).ready(function(){
	$('#ckbox').click(function(){
		$("td").find('input[type="checkbox"]').each(function(){
			if($('input[type="checkbox"]').prop('checked')) {
				$(this).prop('checked',true);
			} else {
				$(this).prop('checked',false)
			}
		});
	});
	
	$('#delAll').click(function(){
		var msg = "您确定要删除吗？";
		if (!confirm(msg)){
			return false;
		}
		
		$("td").find('input[type="checkbox"]').each(function(){
			if($(this).prop('checked')){
				doDel($(this).val());
			}
		});
	});
});

function del(id){
	var msg = "您确定要删除吗？";
	if (confirm(msg)){
		doDel(id)
	}
}

function doDel(id){
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