define(function(require, exports, module) {
	
	require(['echarts', 
	         'echarts/chart/map', 
	         'echarts/chart/pie',
             'echarts/component/visualMap',
             'echarts/component/markPoint', 
	         'echarts/component/tooltip', 
	         'theme/macarons', 
	         'map/js/china'], 
	function(echarts, theme) {
		var chart1 = echarts.init(document.getElementById('dashboard_chart_1'), theme);
		chart1.showLoading();
		// 指定图表的配置项和数据
		chart1.setOption({
	        tooltip: {
	        	trigger: 'item',
	            formatter: "{b} <br/>{a} : {c} ({d}%)"
	        },
	        series: []
	    });
		
		$.ajax({
			url: GLOBAL_PATH + '/dashboard/getBigDataRequirementDivisionByIndustry',
			dataType: 'json',
			type: 'GET',
			success: function(rp, status, xhr) {
				chart1.hideLoading();
		    	chart1.setOption({
		            series: [$.extend({radius: '65%', center: ['50%', '50%']}, rp.message.series[0])]
		        });
			},
			error: function(xhr, errorType, error) {
				
			}
		});
		
		
		var chart2 = echarts.init(document.getElementById('dashboard_chart_2'), theme);
		chart2.showLoading();
		// 指定图表的配置项和数据
		chart2.setOption({
	        tooltip: {
	        	trigger: 'item',
	            formatter: "{b} <br/>{a} : {c} ({d}%)"
	        },
	        series: []
	    });
		
		$.ajax({
			url: GLOBAL_PATH + '/dashboard/BigDataRequirementDivisionByCompanySize',
			dataType: 'json',
			type: 'GET',
			success: function(rp, status, xhr) {
				chart2.hideLoading();
				chart2.setOption({
		            series: [$.extend({radius: '65%', center: ['50%', '50%']}, rp.message.series[0])]
		        });
			},
			error: function(xhr, errorType, error) {
				
			}
		});
		
		var chart3 = echarts.init(document.getElementById('dashboard_chart_3'), theme);
		chart3.showLoading();
		// 指定图表的配置项和数据
		chart3.setOption({
	        tooltip: {
	        	trigger: 'item',
	            formatter: "{b} <br/>{a} : {c}"
	        },
	        series: []
	    });
		
		$.ajax({
			url: GLOBAL_PATH + '/dashboard/BigDataRequirementDivisionByCity',
			dataType: 'json',
			type: 'GET',
			success: function(rp, status, xhr) {
				var itemStyle = {
                        normal:{
                            borderColor: 'rgba(0, 0, 0, 0.2)'
                        },
                        emphasis:{
                            shadowOffsetX: 0,
                            shadowOffsetY: 0,
                            shadowBlur: 20,
                            borderWidth: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    };
				var series = {
						itemStyle: itemStyle,
						showLegendSymbol: true,
                        roam: false,
                        label: {
                            normal: {
                                show: true
                            },
                            emphasis: {
                                show: true
                            }
                        }
					}
				chart3.hideLoading();
				chart3.setOption({
			        visualMap: {
			            min: 0,
			            max: 2000,
			            left: 'left',
			            top: 'bottom',
			            text: ['高','低'],           // 文本，默认为数值文本
			            calculable: true
			        },
		            series: [$.extend(series, rp.message.series[0])]
		        });
			},
			error: function(xhr, errorType, error) {
				
			}
		});
		
	})
});