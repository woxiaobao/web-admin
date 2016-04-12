package com.qiyestore.grails.plugin.admin_web

class TModule {
	
	String name;
	String permissions;
	Long  parentId;
	Integer sortTop=0;
	
	static constraints = {
		name nullable :true
		permissions nullable :true
		parentId nullable :true
		sortTop nullable :true
	}
	
	static mapping = {
		version false
		cache true
	}
	
}
