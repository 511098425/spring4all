$(document).ready(function(){
	var userId;
	$('#userIdc').val('');
	$('#passWordc').val('');
	$('#passWordmorec').val('');
	$('#verificationCodec').val('');
	$('#shorMessageNumc').val('');
	$(document).keydown(function(e) {
		//debugger;
		var evt = window.event || e;
		if(evt.keyCode == 13) {
			evt.preventDefault();
			var passWordcVal = $('#passWordc').val();//第一次密码
			var passWordmorecVal = $('#passWordmorec').val();//第二次密码
			var verificationCodecVal = $('#verificationCodec').val();//验证码
			var sMobile = $('#userIdc').val();
			var sMobilePassword = $('#passWordc').val();
			 var reg = /^.{6,}$/;
			if(!(/^1[3|4|5|8][0-9]\d{8}$/.test(sMobile))){ 
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
			if(!reg.test(sMobilePassword)){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('请输入至少6位密码',{icon: 2,
						 time: 2000, 
						 offset:'40px',
						 shift: 6
					   });  
				});
			    return;
			}
			if(passWordcVal != passWordmorecVal){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('两次密码不一样',{icon: 2,
						 time: 2000, 
						 offset:'40px',
						 shift: 6
					   });  
				});
				return;
			}else{
				getVerificationCode();
			}
		}
	});
	var verificationCodecVal,passWordmorecVal;
	//立即注册登录
	$('#registerClick').click(function(){
		var passWordcVal = $('#passWordc').val();//第一次密码
		passWordmorecVal = $('#passWordmorec').val();//第二次密码
		verificationCodecVal = $('#verificationCodec').val();//验证码
		userId = $('#userIdc').val();
		var sMobile = $('#userIdc').val();
		var sMobilePassword = $('#passWordc').val();
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
		} 
		if(!reg.test(sMobilePassword)){
			layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg('请输入至少6位密码',{icon: 2,
					 time: 2000, 
					 offset:'40px',
					 shift: 6
				   });  
			});
		    return;
		}
		if(passWordcVal != passWordmorecVal){
			layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg('两次密码不一样',{icon: 2,
					 time: 2000, 
					 offset:'40px',
					 shift: 6
				   });  
			});
			return;
		}else{
			getVerificationCode();
		}
	});
	function getVerificationCode(){
		$.ajax({
			type: "post",
			dataType: "text",
			url: "../../authentication/mobile/register",
			data: {
				'mobile':userId,'smsCode':verificationCodecVal,'password':passWordmorecVal,
			},
			success: function(obj) {
				if(obj =="errorCode"){
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('短信验证码错误',{icon: 2,
							 time: 2000, 
							 offset:'40px',
							 shift: 6
						   }); 
					});
					$('#shorMessageNumc').val('');
					return;
				}else if(obj =="1"){
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('服务器异常',{icon: 2,
							 time: 2000, 
							 offset:'40px',
							 shift: 6
						   });  
					});
					setTimeout(function(){
						location.reload();
					},2000)
					return;
				}else if(obj =="0"){
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('注册成功',{
							 time: 1000, 
							 offset:'40px',
							 shift: 6
						   });  
						});
					$.cookie('phone', userId, { path: '/' });
					location.href ="../../";
				}else if(obj =="regestered"){
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('该账号已注册',{icon: 5,
							 time: 2000, 
							 offset:'40px',
							 shift: 6
						   }); 
					});
					setTimeout(function(){
						location.reload();
					},2000)
					return;
				}else if(obj =="cancellation"){
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('该账号已被注销，请去激活',{icon: 5,
							 time: 3000, 
							 offset:'40px',
							 shift: 6
						   }); 
					});
					setTimeout(function(){
						location.reload();
					},2000)
					return;
				}
				else if(obj =="frozen"){
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('该账号已被冻结,请联系客服',{icon: 5,
							 time: 2000, 
							 offset:'40px',
							 shift: 6
						   });  
					});
					setTimeout(function(){
						location.reload();
					},2000)
					return;
				}
			},
			error: function() {
				console.log("请求异常！");
			}
		});
	}
	//获取短信验证码
	$('#getShorMessageNumc').click(function(){
		var passWordcVal = $.trim($('#passWordc').val());//第一次密码
		var passWordmorecVal = $.trim($('#passWordmorec').val());//第二次密码
		userId = $.trim($('#userIdc').val());
		var sMobile = $.trim($('#userIdc').val());
		var sMobilePassword = $.trim($('#passWordc').val());
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
		} 
		if(userId ==""||passWordcVal==""||passWordmorecVal==""){
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
		if(!reg.test(sMobilePassword)){
			layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg('请输入至少6位密码',{icon: 2,
					 time: 2000, 
					 offset:'40px',
					 shift: 6
				   });  
			});
		    return;
		}
		if(passWordcVal != passWordmorecVal){
			layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg('两次密码不一样',{icon: 2,
					 time: 2000, 
					 offset:'40px',
					 shift: 6
				   });  
			});
			return;
		}else{
//			$.ajax({
//				type: "post",
//				dataType: "text",
//				url: "../vrify/vrifyCode",
//				data: {
//					'vrifyCode':$('#verificationCodec').val(),
//				},
//				success: function(obj) {
//					if(obj=="vrifyfail"){
//						layui.use('layer', function(){
//							  var layer = layui.layer;
//							  layer.msg('验证码错误',{icon: 5,
//								 time: 2000, 
//								 offset:'40px',
//								 shift: 6
//							   }); 
//						});
//						$('#verificationCodec').val('');
//						$('#thisImg').click();
//						return;
//					}else if(obj="vrifysuccess"){
//						vrifySMS();
//					}
//					
//				},
//				error: function() {
//					console.log("请求异常！");
//				}
//			});
			vrifySMS();
		}
	})
	function vrifySMS(){
		/*$.ajax({
			type: "post",
			dataType: "text",
			url: "../vrify/vrifySMS",
			data: {
				'phone':$('#userIdc').val(),
			},
			success: function(obj) {
				if(obj != 'OK'){
				layui.use('layer', function(){
				  var layer = layui.layer;
				  layer.msg('您的账号出现异常,请稍后再试',{icon: 5,
					 time: 2000, 
					 offset:'40px',
					 shift: 6
				   }); 
				});
				setTimeout(function(){
					location.reload();
				},2000)
				return;
				}else{
					var num =60;
					$('.verificationCode_main').show();
					$('#getShorMessageNumlater').show();
					$('#getShorMessageNumc').hide();
					var t = setInterval(function(){
						num-=1;
						if(num == 0){
							$('#getShorMessageNumlater').hide();
							$('#getShorMessageNumc').show();
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
				}
	
				
			},
			error: function() {
				console.log("请求异常！");
			}
		});*/
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
	            	if(data == "OK"){
	            		var num =60;
						$('#getShorMessageNumlater').show();
						$('#getShorMessageNumc').hide();
						var t = setInterval(function(){
							num-=1;
							if(num == 0){
								$('#getShorMessageNumlater').hide();
								$('#getShorMessageNumc').show();
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
	}
	//已有账号，立即登录
	$('#registerc').click(function(){
		location.href="../html/login.html";
	});
	$('.form_text_ipt input').focus(function(){
		$(this).addClass('inputFocus').siblings().removeClass('inputFocus');
	});
	$('.form_text_ipt input').blur(function(){
		$(this).removeClass('inputFocus');
	});
})