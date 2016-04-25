package com.qiyestore.grails.plugin.admin_web

import java.util.Date;

class TRole {

	
	String authority
	String comment
	boolean enabled = true
	Date dateCreated
	Date lastUpdated
	Long createdById
	
	static hasMany = [ permissions: TPermission]

	static mapping = { 
		version false
		cache true 
	}

	static constraints = {
		createdById nullable:true
		authority blank: false, unique: true
		comment nullable:true
	}
	
	String toString() {
		authority
	}
	
	/**
	* Judge if the role can access the feature
	* @param feature
	* @return
	*/
   def hasFeature(def feature) {
	   def result = 'false'
	   if(feature.configAttribute.contains(authority)) {
		   result = 'true'
	   }
	   return result
   }
}
