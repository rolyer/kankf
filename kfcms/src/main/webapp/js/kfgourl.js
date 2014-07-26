var i=0;

$(function(){
	var by=parseInt($(window).height());
	$("#game_iframe").height(by-32);
	
	//
	/*if(location.search=="?=header"){
		$(".skip").html("").css({"padding-top":"0px"});
		$(".skip").html('<iframe scrolling="no" frameborder="0" width="'+$("body").width()+'" height="'+(parseInt($(window).height()))+'" src="'+kfUrl+'"></iframe>');
	}else{
		start();
	}*/
	if(location.search.indexOf("?=channl")<0){
		start();
	}
	//start();
	//
	/*
	if($.cookie("look_history")){
		var cookie_json=JSON.parse($.cookie('look_history'));//eval('('+$.cookie("look_history")+')');
		var c_l=cookie_json.length,nowKey=-1;
		for(var i=0;i<c_l;i++){
			var id=cookie_json[i].fu_game_kflist_id;
			if(look_history.fu_game_kflist_id==id)nowKey=parseInt(i);
		}
		if(nowKey>=0){
			cookie_json.splice(nowKey,1);
			cookie_json.unshift(look_history);
			look_history=cookie_json;
		}else{
			cookie_json.unshift(look_history);
			if(cookie_json.length>6)cookie_json.pop();
			look_history=cookie_json;
		}
	}else{
		var look_history_arr=[];
		look_history_arr[0]=look_history;
		look_history=look_history_arr;
	}
	$.cookie('look_history',JSON.stringify(look_history),{expires:360000*24,path:"/",domain:'5336.com'});
	*/
});


function start(){
	 ba=setInterval("begin()",5);
}

function begin(){
	i++;
	if(i<=100) {
		$("#in").css("width",i+"%");
		$("#in").html(i+"%");
	}else{
		clearInterval(ba);
		if(location.search!="?=header"){
			 window.location.href=kfUrl;
		}
	}
}
