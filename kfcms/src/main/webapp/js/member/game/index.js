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
		delAll();
	});
});

function delAll(){
	var msg = "您确定要删除吗？";
	if (!confirm(msg)){
		return false;
	}
	
	$("td").find('input[type="checkbox"]').each(function(){
		if($(this).prop('checked')){
			del($(this).val())
		}
	});
}

function doDelete(id){
	var msg = "您确定要删除吗？";
	if (confirm(msg)==true){
		del(id);
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