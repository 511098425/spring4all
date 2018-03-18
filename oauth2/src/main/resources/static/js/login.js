$(document).ready(function(){
	var SMSAuthenticationCode;
	var userId;
	var urls;
	var passWord;
	var rememPass = false;
	var usernames = $.cookie('usernames');
	var passWord = $.cookie('passWord');
	$('#userId').val('');
	$('#passWord').val('');
	$('#verificationCode').val('');
	$('#shorMessageNum').val('');
	$('#userId').focus();
	//$.cookie('username', null,{ path: '/' });
	if(usernames == '' ){
		
	}
	else if(usernames == undefined){
		
	}
	else if(usernames == 'null'){
		
	}
	else if(usernames == null){
		
	}
	else{
		$('#userId').val(usernames);
		$('#passWord').val(passWord);
	}
	//记住密码
	$('#rememberPassword').click(function(){
		if(rememPass){
			rememPass =!rememPass;
		}else{
			rememPass =!rememPass;
		}
	})
	
	var Url = window.location.href;
	var lastUrl = document.referrer;
	//登录
	$('#login').click(function() {
		var passWordcVal = $.trim($('#passWord').val());//密码
		var verificationCodecVal = $.trim($('#verificationCode').val());//验证码
		userId = $.trim($('#userId').val());
		var sMobile = $.trim($('#userId').val());
		var sMobilePassword = $.trim($('#passWord').val());
		var shorMessageOff = $('#smsLogin').hasClass('hasmsg'); 
		var shorMessageNum = $('#shorMessageNum').val();
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
		if(shorMessageOff){
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
			}
			mobile();
		}else{
			if(!reg.test(sMobilePassword)){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('请输入至少6位密码',{icon: 2,
						 time: 2000, 
						 offset:'40px'
					   });  
				});
			    return;
			}
			if(verificationCodecVal==""){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('请先填写验证码',{icon: 2,
						 time: 2000, 
						 offset:'40px',
						 shift: 6
					   });  
				});
				  return; 
			}
			login();
		}
	})
	$(document).keydown(function(e) {
		var passWordcVal = $.trim($('#passWord').val());//第一次密码
		var verificationCodecVal = $.trim($('#verificationCode').val());//验证码
		userId = $.trim($('#userId').val());
		var sMobile = $.trim($('#userId').val());
		var sMobilePassword = $.trim($('#passWord').val());
		var shorMessageOff = $('#shorMessage').hasClass('hasmsg'); 
		var shorMessageNum = $('#shorMessageNum').val();
		var reg = /^.{6,}$/;
			var evt = window.event || e;
			if(evt.keyCode == 13) {
				evt.preventDefault();
				if(userId ==""){
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('请先完整填写账号密码',{icon: 2,
							 time: 2000, 
							 offset:'40px',
							 shift: 6
						   });  
					});
					  return; 
				}
				
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
				} 

				if(shorMessageOff){
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
					}
					mobile();
				}else{
					if(!reg.test(sMobilePassword)){
						layui.use('layer', function(){
							  var layer = layui.layer;
							  layer.msg('请输入至少6位密码',{icon: 2,
								 time: 2000, 
								 offset:'40px'
							   });  
						});
					    return;
					}
					if(verificationCodecVal==""){
						layui.use('layer', function(){
							  var layer = layui.layer;
							  layer.msg('请先填写验证码',{icon: 2,
								 time: 2000, 
								 offset:'40px',
								 shift: 6
							   });  
						});
						  return; 
					}
					login();
				}
			}
		});
	var scopeList;
	function login(){
	  	userId = $('#userId').val();
	  	password = $('#passWord').val();
	    var isCheck = $("input[name='remember-me']").is(":checked");
	  	$.ajax({
			type: "post",
			url: "../auth/form",
			dataType: "json",
			async: true,
			contentType: "application/x-www-form-urlencoded",
			data: {
				'imageCode':$('#verificationCode').val(),'username':userId,'password':password,"remember-me": isCheck
			},
			success: function(obj) {
                console.log(obj)

						$.cookie('phone', userId, { path: '/' });
						scopeList = obj.scopeList;
						auth();
						//location.href ="../index.html"+ "vals=" +obj.scopeList[0]+"vals="+obj.scopeList[1];

			},
			 error: function (obj) {
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
				$('#verificationCode').val('');
				$('#thisImg').click();
				return;
			}
		});
	}
	function mobile(){
			var isCheck = $("input[name='remember-me']").is(":checked");
			$.ajax({
				type: "post",
				dataType: "json",
				url: "../../auth/mobile/login",
				data: {
					"mobile": $('#userId').val(),"smsCode":$('#shorMessageNum').val(),"remember-me": isCheck
				},
				success: function(obj) {
					//记住密码
					if(isCheck){
						$.cookie('phone', userId, { expires: 999999999, path: '/' });
						location.href ="../index.html";
					}else{
						$.cookie('phone', userId, { path: '/' });
						location.href ="../index.html";
					}
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
	var shorMessageoff  = true;
	//短信登陆
	$('#shorMessage').click(function(){
		if(shorMessageoff){
			$('#passWord').hide();
			$('.shorMessageMain').show();
			$('#userId').attr('placeholder','手机号');
			$('#userId').val('');
			$('#passWord').val('');
			$(this).html('账号登录');
			$(this).addClass('hasmsg');
			$('#verificationCode').val('');
			shorMessageoff = !shorMessageoff;
		}else{
			$('#passWord').show();
			$('.shorMessageMain').hide();
			$('#userId').attr('placeholder','手机号');
			$('#userId').val('');
			$('#passWord').val('');
			$(this).html('短信登录');
			$(this).removeClass('hasmsg');
			$('#verificationCode').val('');
			shorMessageoff = !shorMessageoff;
		}
		
	})
	
	//获取短信验证码
	$('#getShorMessageNum').click(function(){
		var passWordcVal = $.trim($('#passWord').val());//第一次密码
		var verificationCodecVal = $.trim($('#verificationCode').val());//验证码
		userId = $.trim($('#userId').val());
		var sMobile = $.trim($('#userId').val());
		var sMobilePassword = $.trim($('#passWord').val());
		var reg = /^.{6,}$/;
		if(userId ==""){
			layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg('请先完整填写账号密码',{icon: 2,
					 time: 2000, 
					 offset:'40px'
				   });  
			});
			  return; 
		}
		if(!(/^1[3|4|5|7|9|8][0-9]\d{8}$/.test(sMobile))){ 
			layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg('请输入正确的手机号',{icon: 2,
					 time: 2000, 
					 offset:'40px'
				   });  
			});
			  return; 
		}else{
			 vrifySMS();
		}
	})
	function vrifySMS(){
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
					$('#getShorMessageNumlater').show();
					$('#getShorMessageNum').hide();
					var t = setInterval(function(){
						num-=1;
						if(num == 0){
							$('#getShorMessageNumlater').hide();
							$('#getShorMessageNum').show();
							num = 60;
							clearInterval(t);
						}
						$('#getShorMessageNumlater').text('倒计时:'+num+'s');
					},1000)
	            	layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('发送成功',{
						 time: 2000, 
						 offset:'40px'
					   });  
					});
					return;
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
	};
	//选中效果
	$('.form_text_ipt input').focus(function(){
		$(this).addClass('inputFocus').siblings().removeClass('inputFocus');
	});
	$('.form_text_ipt input').blur(function(){
		$(this).removeClass('inputFocus');
	});
	var smsOffon = true;
	//短信登录
	$('#smsLogin').click(function(){
		if(smsOffon){
			$(this).addClass('hasmsg');
			$('.commonLogin').hide();
			$('.smswrap').show();
			$(this).text('账号登录');
			smsOffon=!smsOffon;
		}else{
			$(this).removeClass('hasmsg');
			$('.commonLogin').show();
			$('.smswrap').hide();
			$(this).text('短信登录');
			smsOffon=!smsOffon;
		};
	});
	function auth(){
		var form=$("<form>");//定义一个form表单
        $(document.body).append(form);
		//form.attr("style","display:none");
		//form.attr("target","");
		form.attr("method","post");
		form.attr("action","../oauth/authorize");
		var inputUser=$("<input>");
		inputUser.attr("type","hidden");
		inputUser.attr("name","user_oauth_approval");
		inputUser.attr("value","true");
		for(var i = 0;i<scopeList.length;i++){
			var input = $("<input>");
			input.attr("type","hidden");
			input.attr("name",""+scopeList[i]+"")
			input.attr("value","true");
			form.append(input);
		}
		/*var input2=$("<input>");
		input2.attr("type","hidden");
		input2.attr("name",""+scopeList1+"");
		input2.attr("value","true");
		var input3=$("<input>");
		input3.attr("type","hidden");
		input3.attr("name",""+scopeList2+"");
		input3.attr("value","true");*/
		form.append(inputUser);
		form.submit();//表单提交
        console.log("ok");
	}
})
