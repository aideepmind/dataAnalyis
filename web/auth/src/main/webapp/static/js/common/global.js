(function($, window) {
	var $topSearchList = $('#top-search-list'), $topKwInput = $('#top-kw-input'), navbar_right = $('#navbar-right');
	
	//激活导航栏目
	if (typeof active != 'undefined') {
		$('#' + active).addClass('active');
	}
	//快速登录
	function quikLogin() {
		$.ajax({
			type: 'GET',//POST
			dataType: 'json',
			url: GLOBAL_PATH + '/login/findSecondNav/',
			cache: false,
			success: function(rp) {
				if (rp && rp.success) {//登录成功
					navbar_right.html(rp.message);
				}
				navbar_right.show();
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				navbar_right.show();
			}
		});
	};
	quikLogin();
	window.setInterval(quikLogin, 20 * 60 * 1000);
	
	// 设置ajax全局请求监听，一般情况用不着，因为上面做了长轮询的快速登录操作。
	$.ajaxSetup({
		//global: true,
		//timeout: 3000,
		complete: function(XMLHttpRequest, textStatus) {
			try {
				var isTimeOut = XMLHttpRequest.getResponseHeader("isTimeOut");
				//判断是否超时
				if("true" == isTimeOut) {//说明超时
					new Win({
						content: '用户会话不存在或者已经超时，请重新登录。',
						events: {
							yes: function() {
								top.location.href =  GL_AUTH_SSO_PATH + '/login/';
							}
						}
					});
					setTimeout('top.location.href = "' + GL_AUTH_SSO_PATH + '/login/"', 3000);//隔两秒钟之后跳到登陆页面
				}
			} catch(e) {
				console.log(e);
			}
		}
	});
	
	$('.footer a.f-weixin').hover(function(ev) {
		$(this).find('i').show();
	}, function(ev) {
		$(this).find('i').fadeOut(300, 'linear', function() {
			$(this).hide();
		});
	}).find('i').hover(function(ev) {
		$(this).finish().show();
	}, function(ev) {
		$(this).hide();
	});
	
	/*//监听系统退出
	$(document).on('click', '#system-logout', function(ev) {
		//跳转
		window.location.href = GL_AUTH_SSO_PATH + 'logout/';
	});*/
	$topKwInput.bind('input propertychange', function(ev) {
		var val = $topKwInput.val();
		if (!val || !val.trim()) {
			$topSearchList.hide().html('');
			return;
		}
		searchIndex(val);
	}).keyup(function(ev) {
		var kc = ev.keyCode;
		if (kc === 38 || kc === 40) {
			if ($topSearchList.is(':hidden') || $topSearchList.find('a').length === 0) {
				return;
			}
			var $a = $topSearchList.find('.search-list-item-focus');
			if (kc === 38) {
				if ($a.length === 0) {//没聚焦过
					$topSearchList.find('a:last').addClass('search-list-item-focus');
				} else {
					$a.removeClass('search-list-item-focus');
					$a.parent().prev().find('a').addClass('search-list-item-focus');
				}
			} else {
				if ($a.length === 0) {//没聚焦过
					$topSearchList.find('a:first').addClass('search-list-item-focus');
				} else {
					$a.removeClass('search-list-item-focus');
					$a.parent().next().find('a').addClass('search-list-item-focus');
				}
			}
		}
	}).keydown(function(ev) {
		var kc = ev.keyCode;
		if (ev.keyCode === 13) {
			//赋予聚焦上的值
			var $a = $topSearchList.find('.search-list-item-focus:first');
			if ($topSearchList.is(':visible') && $a.length) {
				var reg = new RegExp('(^|&)kw=([^&]*)(&|$)', 'i');
		        var r = $a.attr('href').match(reg);
				$topKwInput.val(r[2]);
			}
		}
	}).focus(function(ev) {
		if ($topSearchList.find('a').length > 0) {
			$topSearchList.find('a').removeClass('search-list-item-focus');
			$topSearchList.show();
		} else {
			var val = $topKwInput.val();
			if (val && val.trim()) {
				searchIndex(val);
			}
		}
	});
	
	$(document).click(function(ev) {//如果使用input的blur代替，那么需要延迟隐藏时间，否则无法触发a的点击事件
		if (!$topKwInput.is(':focus')) {
			$topSearchList.hide();
		}
	});
	
	//搜索
	function searchIndex(val) {
		//判断搜索是公司端的还是学生端的
		var url, accType = $topKwInput.attr('accType');
		if (accType === '4') {
			url = GL_WWW_SEARCH_PATH + '/search/comp/findKeywordByJsonp/?kw=' + val.trim();
		} else {
			url = GL_WWW_SEARCH_PATH + '/search/stu/findKeywordByJsonp/?kw=' + val.trim();
		}
		$.ajax({
			type: 'GET',//POST
			dataType: 'jsonp',
			jsonp: 'callback',
			url: url,
			success: function(data) {
				if (data.success) {
					if (accType === '4') {
						createSearchIndexBoxForComp(data.message);
					} else {
						createSearchIndexBoxForStu(data.message);
					}
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('获取数据失败，请检查网络是否正常。');
			}
		});
	}
	
	//创建索引下拉框
	function createSearchIndexBoxForStu(list) {
		$topSearchList.html('');
		if (list && list.length > 0) {
			var pLis, cLis;
			for (var i = 0; i < list.length; i++) {
				var o = list[i];
				if (o.type == '1') {
					if (!pLis) {
						pLis = '<li class="search-list-sort">职位</li>';
					}
					pLis += '<li class="search-list-item"><a href="' + GL_WWW_SEARCH_PATH + '/search/stu/?fs=true&kw=' + o.name + '"><span class="f-l">' + o.name + '</span><span class="f-r">共<i>' + o.count + '个</i>职位</span></a></li>';
				} else {
					if (!cLis) {
						cLis = '<li class="search-list-sort">公司</li>';
					}
					cLis += '<li class="search-list-item"><a href="' + GL_WWW_SEARCH_PATH + '/search/stu/?fs=true&kw=' + o.name + '"><span class="f-l">' + o.name + '</span><span class="f-r">共<i>' + o.count + '个</i>职位</span></a></li>';
				}
			}
			if (pLis) {
				$topSearchList.append(pLis);
			}
			if (cLis) {
				$topSearchList.append(cLis);
			}
			//设置虚线
			var $sort = $topSearchList.find('.search-list-sort');
			if ($sort.length > 1) {
				$sort.eq(1).next().addClass('top-dashed');
			}
			$topSearchList.show();
		}
	}
	
	//创建索引下拉框
	function createSearchIndexBoxForComp(list) {
		$topSearchList.html('');
		if (list && list.length > 0) {
			var sLis = '';
			for (var i = 0; i < list.length; i++) {
				var o = list[i];
				sLis += '<li class="search-list-item" style="margin-right: 0;"><a href="' + GL_WWW_SEARCH_PATH + '/search/comp/?fs=true&kw=' + o.name + '"><span class="f-l">' + o.name + '</span><span class="f-r">共<i>' + o.count + '个</i>学生</span></a></li>';
			}
			$topSearchList.append(sLis);
			$topSearchList.show();
		}
	}
	
	$topSearchList.on('mouseenter', 'a', function(ev) {
		var $this = $(this);
		$topSearchList.find('a').removeClass('search-list-item-focus');
		$this.addClass('search-list-item-focus');
	}).on('mouseleave ', 'a', function(ev) {
		var $this = $(this);
		$this.removeClass('search-list-item-focus');
	});
	
	$topKwInput.parents('form').submit(function(ev) {
		var val = $topKwInput.val();
		if (!val || !val.trim()) {
			if ($topKwInput.attr('accType') === '4') {
				$topKwInput.val('菁英圈');
			} else {
				$topKwInput.val('京东');
			}
		}
	});
	
	//三秒钟之后查找未读的推荐信息
	setTimeout(function() {
		$.ajax({
			type: 'GET',//POST
			dataType: 'text',
			url: GLOBAL_PATH + '/recommend/findUnRead/',
			success: function(count) {
				if (count != 0) {
					if (count > 99) {
						count = 99;
					}
					$('#nav-recommend').find('i').show().html(count);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('获取数据失败，请检查网络是否正常。');
			}
		});
	}, 3000);
	
	// 对Date的扩展，将 Date 转化为指定格式的String   
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
	// 例子：   
	// (new Date()).Format("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423   
	// (new Date()).Format("yyyy-M-d H:m:s.S")      ==> 2006-7-2 8:9:4.18   
	Date.prototype.format = function(fmt){
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "H+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	} 
})(jQuery, window);

function go2err(xhr) {
	console.log(xhr);
}