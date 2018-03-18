$(document).ready(function(){
	var $li = $('.head_nav ul li');
	var $liS = $('.li_span1');
	//头部导航效果
	$li.hover(function(){
			var index = $(this).index();
			$liS.eq(index).stop(true).animate({
				top:'25',
				opacity:'1',
			},250);
			var thisWidth= $(this).width();
			var thisLeft = $(this).position().left;
			
		},function(){
			$liS.animate({
				top:'5',
				opacity:'0',
			},200);
		})
	//游览器地址
	var params = window.location.href;
    var substr = "kickout";
	function isContains(str, substr) {
	    return str.indexOf(substr) >= 0;
	}
	if(isContains(params, substr)){
		if (window.sessionStorage) {
			sessionStorage.removeItem('username');
		}
		else{
			delCookie('username');
		}
	}
	//登录
//	$('#login').click(function() {
//		userId = $('#userId').val();
//		$.cookie('username', userId, { path: '/' });
//					location.href = lastUrl==Url?lastUrl="../index.html":lastUrl;
//	})
	//查询带过来的数据
	var params = window.location.search;
	params = params.substring(params.indexOf() + 1, params.length);
	var param = decodeURI(params.split("&=")[1]);
	$('.mobileReturna').click(function(){
		location.href = "search.html?vals="+encodeURI(param);
	})
	//主页退出登录
	$('.li_leavelogin_index').click(function(){
		$.ajax({
			type: "post",
			dataType: "json",
			url: "/signOut",
			data: {
				
			},
			success: function(obj) {
				$('.login_parent').show();
				$('#username').hide();
				$.cookie('phone', null,{ path: '/' });
				location.reload();
			},
			error: function(obj) {
				console.log(obj);
			}
		});
	})
	//退出登录
	$('.li_leavelogin').click(function(){
		$.ajax({
			type: "post",
			dataType: "json",
			url: "../userController/logout",
			data: {
				
			},
			success: function(obj) {
				$('.login_parent').show();
				$('#username').hide();
				$.cookie('gzwusername', null,{ path: '/' });
				$.cookie('phone', null,{ path: '/' });
				location.href="../index.html";
			},
			error: function() {
				console.log("请求异常！");
			}
		});
	})
	//获取cookie  
	function getCookie(cname) {  
	    var name = cname + "=";  
	    var ca = document.cookie.split(';');  
	    for(var i=0; i<ca.length; i++) {  
	        var c = ca[i];  
	        while (c.charAt(0)==' ') c = c.substring(1);  
	        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);  
	    }  
	    return "";  
	}
	//删除cookies  
	function delCookie(name)  
	{  
	    var exp = new Date();  
	    exp.setTime(exp.getTime() - 1);  
	    var cval=getCookie(name);  
	    if(cval!=null){  
	        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
	    }
	}
	//进入我的账户
	$('.li_userAccount_index').click(function(){
		location.href = "html/AccountManagement.html";
	})
	//进入我的账户
	$('.li_userAccount').click(function(){
		location.href = "../html/AccountManagement.html";
	})
	
	//进入后台
	$('.li_userCenter_index').click(function() {
		location.href = "adminManage/adminIndex.html?";
	})
	$('.li_userCenter').click(function() {
		location.href = "../adminManage/adminIndex.html?";
	})
	//进入登录
	$('#login_page').click(function(){
		location.href = "../html/login.html";
	})
	$('#res_page').click(function(){
		location.href = "../html/login.html";
	})
	$('#login_page_index').click(function(){
		location.href = "html/login.html";
	})
	$('#res_page_index').click(function(){
		location.href = "html/register.html";
	})
	//回到顶部
	$(".rocket").click(function () {
        var speed=200;//滑动的速度
        $('body,html').animate({ scrollTop: 0 }, speed);
        return false;
 	});
	$(document).scroll(function(){
		var domScroll = $(document).scrollTop(); 
		if(domScroll >= 20){
			$(".rocket").show();
		}
		else{
			
			$(".rocket").hide();
		}
	});
	
	//移动版登录
	$('.mobileMainLoginWrapUserId').focus(function(){
		$(this).addClass('mobileOnfousinp');
		$('.mobileMainLoginWrapMessageInp').removeClass('mobileOnfousinp');
	})
	$('.mobileMainLoginWrapMessageInp').focus(function(){
		$(this).addClass('mobileOnfousinp');
		$('.mobileMainLoginWrapUserId').removeClass('mobileOnfousinp');
	})
	$('.mobileMainLoginWrapUserId').blur(function(){
		$(this).removeClass('mobileOnfousinp');
	});
	$('.mobileMainLoginWrapMessageInp').blur(function(){
		$(this).removeClass('mobileOnfousinp');
	});
	var passWordcVal,userId;
	//登录
	$('#login').click(function() {
		passWordcVal = $.trim($('#passWord').val());//密码
		userId = $.trim($('#userId').val());//手机号
		var reg = /^.{6,}$/;
		if(!(/^1[3|4|5|7|9|8][0-9]\d{8}$/.test(sMobile))){ 
			layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg('请输入正确的手机号',{icon: 2,
					 time: 2000, 
					 offset:'40px',
					 shift: 6
				   });  
			});
			  return; 
		};
		if(shorMessageNum == ""){
			layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg('请先填写短信验证码',{icon: 2,
					 time: 2000, 
					 offset:'40px',
					 shift: 6
				   });  
			});
			return;
		};
		mobile();
	});
	function mobile(){
		var isCheck = $("input[name='remember-me']").is(":checked");
		$.ajax({
			type: "post",
			dataType: "json",
			url: "../../authentication/mobile/login",
			data: {
				"mobile": $('#userId').val(),"smsCode":$('#shorMessageNum').val()
			},
			success: function(obj) {
				$.cookie('phone', userId, { path: '/' });
				location.href ="../index.html";
			},
			error: function(obj) {
				console.log(obj)
				var jsonMessage = eval('(' + obj.responseText + ')');
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg(''+jsonMessage.message+'',{icon: 5,
						 time: 2000, 
						 offset:'40px',
						 shift: 6
					   }); 
				});
				return;
			}
		});
	}
	//短信验证码
	$('#mobileGetShorMessageNum').click(function(){
		 $.ajax({
	            url: "../../code/sms",
	            type: "get",
	            async: true,
	            data: {
	                "phone": userId,
	            },
	            dataType: "text",
	            contentType: "application/x-www-form-urlencoded",
	            success: function (data) {
	            	console.log(data)
	            	if(data == "OK"){
						var num =60;
						$('#mobileGetShorMessageNumlater').show();
						$('#mobileGetShorMessageNum').hide();
						var t = setInterval(function(){
							num-=1;
							if(num == 0){
								$('#mobileGetShorMessageNumlater').hide();
								$('#mobileGetShorMessageNum').show();
								num = 60;
								clearInterval(t);
							}
							$('#mobileGetShorMessageNumlater').text('倒计时:'+num+'s');
						},1000)
						layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('发送成功',{
							 time: 2000, 
							 offset:'40px'
						   });  
						});
	            	}else{
	            		layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('发送短信太频繁,请稍后再试',{
							 time: 2000, 
							 offset:'40px'
						   });  
						});
						return;
	            	}
	            },
	            error: function (XMLHttpRequest, textStatus, errorThrown) {
	                alert('获取短信异常');
	            }

	        });
	});
})
