$(document).ready(function(){
	var $nav = $('.head_nav');
	var $search_li = $('.search_nav_top_ul li');
	var $search_li_index = 0;
	//搜索栏选项效果
	$search_li.click(function(){
		$search_li_index = $(this).index();
		$(this).addClass('activeLi').siblings().removeClass('activeLi');
		if($search_li_index == 0){
			$('.search_nav_inp').attr('placeholder','请输入企业名称');
		}
		else if($search_li_index == 1){
				$('.search_nav_inp').attr('placeholder','请输入专利名或专利号');
		}
		else if($search_li_index == 2){
				$('.search_nav_inp').attr('placeholder','请输入商标名称或商标号');
		}
		else if($search_li_index == 3){
				$('.search_nav_inp').attr('placeholder','请输入软件著作权名称');
		}
		else{
				$('.search_nav_inp').attr('placeholder','请输入作品著作权名称');
		}
	})
	$('.search_nav_inp').focus(function(){
		$(this).css({
			"border":"1px solid #78AEFF",
			"border-right":"1px solid #ccc"
		})
	})
	$('.search_nav_inp').blur(function(){
		$(this).css('border','1px solid #ccc');
	});
	//热词搜索
	$('.headBanner_banner_center_search_hot ul li').click(function(){
		var $val = $(this).text();
			location.href = "html/search.html?" + "vals=" + encodeURI($val);
	})
	//省份
	var regProvince = /^北京市$|^天津市$|^上海市$|^重庆市$|^河北省$|^山西省$|^辽宁省$|^吉林省$|^黑龙江省$|^江苏省$|^浙江省$|^安徽省$|^福建省$|^江西省$|^山东省$|^湖北省$|^广东省$|^湖南省$|^海南省$|^四川省$|^贵州省$|^云南省$|^陕西省$|^甘肃省$|^青海省$|^台湾省$|^内蒙古自治区$|^广西壮族自治区$|^西藏自治区$|^宁夏回族自治区$|^新疆维吾尔自治区$|^香港特别行政区$|^澳门特别行政区$|^北京$|^天津$|^上海$|^重庆$|^河北$|^山西$|^辽宁$|^吉林$|^黑龙江$|^江苏$|^浙江$|^安徽$|^福建$|^江西$|^山东$|^湖北$|^广东$|^湖南$|^海南$|^四川$|^贵州$|^云南$|^陕西$|^甘肃$|^青海$|^台湾$|^内蒙古$|^广西$|^西藏$|^宁夏$|^新疆$|^香港$|^澳门$|^有限$|^科技$|^责任$|^股份$/;
	//热门城市
	var regHotCity = /^南京市$|^天津市$|^苏州市$|^西安市$|^长沙市$|^沈阳市$|^青岛市$|^郑州市$|^大连市$|^东莞市$|^宁波市$|^济南市$|^无锡市$|^武汉市$|^深圳市$|^广州市$|^佛山市$|^南通市$|^哈尔滨市$|^福州市$|^长春市$|^石家庄市$|^烟台市$|^合肥市$|^唐山市$|^太原市$|^常州市$|^昆明市$|^合肥市$|^泉州市$|^温州市$|^南昌市$|^绍兴市$|^潍坊市$|^嘉兴市$|^厦门市$|^大庆市$|^南宁市$|^金华市$|^徐州市$|^鄂尔多斯市$|^扬州市$|^乌鲁木齐市$|^包头市$|^贵阳市$|^东营市$|^威海市$|^中山市$|^镇江市$|^呼和浩特市$|^盐城市$|^泰州市$|^保定市$|^济宁市$|^洛阳市$|^沧州市$|^兰州市$|^鞍山市$|^泰安市$|^廊坊市$|^邯郸市$|^榆林市$|^惠州市$|^桂林市$|^吉林市$|^江门市$|^襄阳市$|^湖州市$|^芜湖市$|^株洲市$|^$聊城市|^德州市$|^衡阳市$|^遵义市$|^淮安市$|^滨州市$|^常德市$|^咸阳市$|^柳州市$|^湛江市$|^新乡市$|^南阳市$|^菏泽市$|^岳阳市$|^茂名市$|^许昌市$|^枣庄市$|^连云港市$|^周口市$|^宿迁市$|^通辽市$|^通化市$|^辽源市$|^齐齐哈尔市$|^南京$|^天津$|^苏州$|^西安$|^长沙$|^沈阳$|^青岛$|^郑州$|^大连$|^东莞$|^宁波$|^济南$|^无锡$|^武汉$|^深圳$|^广州$|^佛山$|^南通$|^哈尔滨$|^福州$|^长春$|^石家庄$|^烟台$|^合肥$|^唐山$|^太原$|^常州$|^昆明$|^合肥$|^泉州$|^温州$|^南昌$|^绍兴$|^潍坊$|^嘉兴$|^厦门$|^大庆$|^南宁$|^金华$|^徐州$|^鄂尔多斯$|^扬州$|^乌鲁木齐$|^包头$|^贵阳$|^东营$|^威海$|^中山$|^镇江$|^呼和浩特$|^盐城$|^泰州$|^保定$|^济宁$|^洛阳$|^沧州$|^兰州$|^鞍山$|^泰安$|^廊坊$|^邯郸$|^榆林$|^惠州$|^桂林$|^吉林$|^江门$|^襄阳$|^湖州$|^芜湖$|^株洲$|^$聊城|^德州$|^衡阳$|^遵义$|^淮安$|^滨州$|^常德$|^咸阳$|^柳州$|^湛江$|^新乡$|^南阳$|^菏泽$|^岳阳$|^茂名$|^许昌$|^枣庄$|^连云港$|^周口$|^宿迁$|^通辽$|^通化$|^辽源$|^齐齐哈尔$/;
	//搜索栏选项效果
	$('#btn_cha').click(function(){
		if (window.sessionStorage) {
			sessionStorage.removeItem('pageIndexs');
		}
		var $val = $.trim($('.search_nav_inp').val());
			if($val == '') {
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('请输入',{
						 time: 2000, 
						 offset:'40px',
						 anim: 6
					   });  
				});
				return;
			}else if($val.length == 1){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('搜索词太广泛啦',{icon: 5,
						 time: 2000, 
						 offset:'40px',
					   });  
				});
				return;
			};
			if(regProvince.test($val) || regHotCity.test($val)){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('搜索词太广泛啦',{icon: 5,
						 time: 2000, 
						 offset:'40px',
					   });  
				});
				return;
			};
			var pattern = new RegExp("[`~!@#$^&*=|{}':;’；'‘“',\\[\\].<>/?~！@#￥……&*（）+ ——|{}【】‘；：”“'。，、？]")
			if(pattern.test($val)){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('请输入正确的关键字',{icon: 5,
						 time: 2000, 
						 offset:'40px',
					   });  
				});
				return;
			};
			
			if($search_li_index == 0){
				location.href = "html/search.html?" + "vals=" + encodeURI($val)+'='+$search_li_index;
			}
			else if($search_li_index ==1){
				location.href = "html/patent.html?" + "vals=" + encodeURI($val)+'='+$search_li_index;
			}
			else if($search_li_index ==2){
				location.href = "html/trademark.html?" + "vals=" + encodeURI($val)+'='+$search_li_index;
			}
			else if($search_li_index ==3){
				location.href = "html/software_copyright.html?" + "vals=" + encodeURI($val)+'='+$search_li_index;
			}
			else if($search_li_index ==4){
				
			}
	})
	$(document).keydown(function(e) {
			var evt = window.event || e;
			var $val = $.trim($('.search_nav_inp').val());
			if (window.sessionStorage) {
				sessionStorage.removeItem('pageIndexs');
			}
			if(evt.keyCode == 13) {
				evt.preventDefault();
				if($val == '') {
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('请输入',{
							 time: 2000, 
							 offset:'40px',
							 anim: 6
						   });  
					});
					return;
				}else if($val.length == 1){
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('搜索词太广泛啦',{icon: 5,
							 time: 2000, 
							 offset:'40px',
						   });  
					});
					return;
				};
				if(regProvince.test($val) || regHotCity.test($val)){
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('搜索词太广泛啦',{icon: 5,
							 time: 2000, 
							 offset:'40px',
						   });  
					});
					return;
				};
				var pattern = new RegExp("[`~!@#$^&*=|{}':;’；'‘“',\\[\\].<>/?~！@#￥……&*（）+ ——|{}【】‘；：”“'。，、？]")
				if(pattern.test($val)){
					layui.use('layer', function(){
						  var layer = layui.layer;
						  layer.msg('请输入正确的关键字',{icon: 5,
							 time: 2000, 
							 offset:'40px',
						   });  
					});
					return;
				};
				if($search_li_index == 0){
					location.href = "html/search.html?" + "vals=" + encodeURI($val)+'='+$search_li_index;
				}
				else if($search_li_index ==1){
					location.href = "html/patent.html?" + "vals=" + encodeURI($val)+'='+$search_li_index;
				}
				else if($search_li_index ==2){
					location.href = "html/trademark.html?" + "vals=" + encodeURI($val)+'='+$search_li_index;
				}
				else if($search_li_index ==3){
					location.href = "html/software_copyright.html?" + "vals=" + encodeURI($val)+'='+$search_li_index;
				}
				else if($search_li_index ==4){
					
				}
			}
		});
	
	var keyWords = ""
	var models = {};
	var pageSizes = 4,pageIndexs = 0;
	$('.search_nav_inp').bind('input propertychange', function() {  
		keyWords = $.trim($(this).val());
	}); 
	    $(".search_nav_inp").autocomplete({ 
               source: function( request, response ) { 
   				models.applicant = keyWords;
   				models.count = pageSizes;
   				models.index = pageIndexs;
                   $.ajax({  
                	    url: "enterprise/route",
                        type : "post",  
                        dataType : "json",  
                        data: {
        					"strJson": JSON.stringify(models)
        				}, 
                       	success: function( obj ) { 
                       		 availableTags = [];
                             for(var i = 0;i<obj.data.length;i++){
                            	 availableTags.push(obj.data[i].applicant)
                             }
                             response(availableTags);  
                       }  
                  });  
               }, 
            }); 
	    $('body .ui-autocomplete').delegate("li","click",function(){
			var $val  = $(this).text();
			location.href = "html/search.html?" + "vals=" + encodeURI($val);
		});
})
