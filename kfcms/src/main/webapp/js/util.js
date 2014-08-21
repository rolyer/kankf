/**
 * Utils
 * 
 * @returns {Utils}
 */
Utils = function() {};
$.extend(Utils.prototype, {
	alert : function(e, message, type) {
		var _type = "success";
		var _title = "提示";
		
		if(type=="info") {
			_type = "info";
			_title = '信息';
		}
		if(type=="warning") {
			_type = "warning";
		}
		if(type=="danger") {
			_type = "danger";
		}
		
		$(e).empty();
		$(e).append('<div class="alert alert-'+_type+'" role="alert"><strong>'+_title+'：</strong> '+message+'</div>');
		$(e).show();
		
		setTimeout( function(){$(e).fadeOut();}, 5*1000 ); 
	},
	showMsg: function (msg,type){
        if(!msg){
            msg="";
        }
        var divWidth=300;
        var divLeft=(document.body.clientWidth - divWidth) / 2;
        var color="#777";
        if(type=="info") {
        	color="#428bca";
        } else if(type=="error") {
            color="#d9534f";
        } else if(type=="warning"){
        	color="#f0ad4e";
        } else {
        	color="#777"
        }

        var panel=$('<div>'+
            '<strong>提示：</strong>'+
            '<p>'+msg+'</p>'+
            '</div>')
            .css({"padding":"10px"})
            .css({width:divWidth+"px",height:"35px",position: "fixed",left:divLeft+"px","z-index":1200,"background-color":color,color:'#FFFFFF'})
            .css({"-webkit-box-shadow":"1px #FFFFFF inset","box-shadow":"1px #FFFFFF inset",opacity:0})
            .css({"border":"1px #FFFFFF solid"})
            .css({"-webkit-border-radius":"3px 3px 10px 10px","border-radius":"3px 3px 10px 10px"});
        panel.animate({
            top:+0,
            opacity: +.8
        },500);
        var hideFuc=function(){
            panel.animate({
                opacity:-1
            },{duration:800,
            complete:function(){
                if(panel){
                    panel.remove();
                }
            }});
        }
        panel.click(hideFuc);
        setInterval(hideFuc,2000);
        $("body").append(panel);
    },
	/**
	 * get timestamp
	 * @returns
	 */
	random : function() {
		var timestamp = Date.parse(new Date());
		return timestamp;
	},
	/**
     * 
     * @param mixedVariable
     * @returns {Boolean}
     */
	isEmpty: function(mixedVariable) {
        if(mixedVariable === ""
            || mixedVariable === 0
            || mixedVariable === "0"
            || mixedVariable === null
            || mixedVariable === false
            || mixedVariable === undefined
            ) {
            return true;
        }
        if(typeof mixedVariable == 'object') {
        	var key = null;
            for(key in mixedVariable) {
                if(typeof mixedVariable[key] !== 'function') {
                    return false;
                }
            }
            return true;
        }
        return false;
    },
    
    /**
     * 
     * @param regex
     * @param s
     * @returns (Boolean)
     */
    isValid: function(regex, s) {
    	var re = new RegExp(regex);
    	return re.test(s);
    },
	/**
	 * @param str
	 * @param ftype
	 * @returns {Boolean} 若符合对应的格式，返回true，否则返回false
	 */
	checkFormat : function(str, ftype) {
		var nReg = Regexs[ftype];
		if (str == null || str == "")
			return false; // 输入为空，认为是验证通过
		if (ftype == 'num') {
			if (nReg.test(str) && chkChinese(str)) {// 10.23 tenfy 必须为数字且不能有中文
				return true;
			} else {
				return false;
			}
		}

		if (nReg.test(str)) {
			return true;
		} else {
			return false;

		}
	}
});

utils = new Utils()

/**
 * 定义验证各种格式类型的正则表达式对象
 */
var Regexs = {
	email : (/^[0-9a-z][0-9a-z\-\_\.]+@([0-9a-z][0-9a-z\-]*\.)+[a-z]{2,}$/i),// 邮箱
	tel : (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/),// 座机号码
	mobile : (/^(((13[0-9]{1})|15[0-9]{1}|18[0-9]{1}|)+\d{8})$/),// 手机号码
	url : (/^http:\/\/([0-9a-z][0-9a-z\-]*\.)+[a-z]{2,}(:\d+)?\/[0-9a-z%\-_\/\.]+/i),// 网址
	num : (/[^0-9]/),// 数字
	account : (/^[A-Za-z0-9]\w{5,15}$/),
	password : (/^[A-Za-z0-9]\w{5,15}$/),
	photo : (/\.jpg$|\.jpeg$|\.gif$/i),// 图片格式
	row : (/\n/ig)
};

/**
 * 
 * @param s
 * @returns {Boolean}
 */
function chkChinese(s) {
	for ( var i = 0; i < s.length; i++) {
		if (s.charCodeAt(i) > 255)
			return true;
	}
	return false;
}