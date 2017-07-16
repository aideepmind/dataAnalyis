/**
 * 打包描述
 * 命令：node r.js -o build.js
 */
({
	appDir: './',
	dir: './production',
	baseUrl: './',
	modules: [{// 模块
			name: 'search/main'
		}, {
			name: 'delivery/main'
		}, {
			name: 'myInviteCode/main'
		}, {
			name: 'notice/main'
		}, {
			name: 'modifyPwd/main'
		}, {
			name: 'accountBind/main'
		}, {
			name: 'myResume/main'
		}, {
			name: 'myIntention/main'
		}, {
			name: 'import/main'
		}, {
			name: 'myRecommend/main'
		}, {
			name: 'mySubscribe/main'
		}, {
			name: 'createSubscribe/main'
		}, {
			name: 'modifySubscribe/main'
		}, {
			name: 'invite/main'
		}, {
			name: 'privacy/main'
		}, {
			name: 'intvEval/main'
		}, {
			name: 'intvEval_show/main'
		}, {
			name: 'info/main'
		}, {
			name: 'myPosition/main'
		}, {
			name: 'myCorp/main'
		}, {
			name: 'myActivity/main'
		}, {
			name: 'activityDetail/main'
		}, {
			name: 'dashBoard/main'
		}, {
			name: 'question/main'
		}, {
			name: 'activity/main'
		}, {
			name: 'positionRec/main'
		}, {
			name: 'firstRec/main'
		}
	],
	fileExclusionRegExp: /^(r|build)\.js|.*\.scss|svn$/, // 过滤
	optimizeCss: 'standard',
	removeCombined: true,// 合并
	paths: {
		'common': 'js/common',
		'search': 'js/search',
		'delivery': 'js/delivery',
		'myInviteCode': 'js/myInviteCode',
		'notice': 'js/notice',
		'modifyPwd': 'js/modifyPwd',
		'accountBind': 'js/accountBind',
		'myResume': 'js/resume/myResume',
		'myIntention': 'js/myIntention',
		'import': 'js/import',
		'myRecommend': 'js/myRecommend',
		'createSubscribe': 'js/subscribe/create',
		'modifySubscribe': 'js/subscribe/modify',
		'mySubscribe': 'js/subscribe/mySubscribe',
		'invite': 'js/invite',
		'privacy': 'js/privacy',
		'intvEval': 'js/intvEval/evalCreate',
		'intvEval_show': 'js/intvEval/evalShow',
		'info': 'js/info',
		'myPosition': 'js/personal/myPosition',
		'myCorp': 'js/personal/myCorp',
		'myActivity': 'js/personal/myActivity',
		'activityDetail': 'js/activity/detail',
		'dashBoard': 'js/dashBoard',
		'question': 'js/dashBoard/question',
		'activity': 'js/activity/list',
		'positionRec': 'js/recommend',
		'firstRec': 'js/firstRec'
	}
})