define(function(require, exports, module) {
	
	require(['echarts', 'echarts/chart/pie', 'echarts/component/tooltip', 'theme/macarons'], function(echarts, theme) {
		var chart1 = echarts.init(document.getElementById('dashboard_chart_1'), theme);
		chart1.showLoading();
		// 指定图表的配置项和数据
		chart1.setOption({
	        tooltip: {
	        	trigger: 'item',
	            formatter: "{b} <br/>{a} : {c} ({d}%)"
	        }
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
	        }
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
	})
	
});