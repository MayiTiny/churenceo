// JavaScript Document
seajs.config({
	base:'/assets/zhaopin/js/',
	paths: {
	    'app': 'app',
	    'tpl': 'app/tpl',
	    'arale':'https://a.alipayobjects.com/arale'
	},
	v:'201406032042',
 	alias:{
 		'$':"lib/jquery",
		'jquery': 'lib/jquery',
		'jquery.json':'lib/jquery.json',
		'dialog':'lib/dialog',
		'autosearch':'lib/autosearch',
		'backbone':'lib/backbone',
		'backbone.server':'lib/backbone.server',
		'underscore':'lib/underscore',
		'datepicker':'lib/datepicker',
		'jquery.pagination':"lib/jquery.pagination",
		'ajax.save':"lib/ajax.save",
		'validator':'lib/validator',
		'bootstrap':'lib/bootstrap',
		'dressplug':'lib/dressplug',
		'seajs.text':'lib/seajs.text',
		'dateformat':'utils/common',
		'share':'lib/share',
		'carouse':'lib/carouse',
		'ajaxplug':'lib/public.ajax',
		'inputtopic':'lib/inputtopic',
		'lavalamp' : 'lib/lavalamp',
		'bas64':'utils/bas64',
		'upload':'lib/upload',
		'ajaxupload':'lib/upload/ajaxupload-min',
		'fileupload':'lib/upload/fileupload'
		
	},
    preload:['jquery','seajs.text']	
});