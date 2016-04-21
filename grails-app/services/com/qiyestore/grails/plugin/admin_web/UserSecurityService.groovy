package com.qiyestore.grails.plugin.admin_web

class UserSecurityService {

	def shiroSecurityService

	/**
	 * 加密密码
	 * @param pwd
	 * @return
	 */
	def encodePassword(String pwd){
		return shiroSecurityService.encodePassword(pwd);
	}


	def initDB() {
		def adminUser = TUser.createCriteria().get {
        	eq('username', 'admin')
        }
        if(adminUser) {
        	log.debug 'Username "admin" exists!'
        } else {
            print "initDB"
        	adminUser = new TUser(username:'admin', name:'系统管理员', password:this.encodePassword('111111'), email:'admin@qiyestore.com', nickname:'Admin', status:TUser.STATUS_USE, comment:'GENERATED at BOOTSTRAP').save(flush:true)
        	def baseModule = new TModule(name:'基础权限',permissions:'user:userEdit,userSave||dashboard:*').save(flush:true)
        	def baseRole = new TRole(authority:'ROLE_USER').save(flush:true)

        	new TRoleTModule(role:baseRole, module: baseModule).save(flush:true)

            def adminRole = new TRole(authority:'ROLE_ADMIN').save(flush:true)
            new TUserTRole(role:adminRole, user: adminUser).save(flush:true)

            def adminModule = new TModule(name:'权限管理').save(flush:true)
            new TRoleTModule(role:adminRole, module: adminModule).save(flush:true)
            new TRoleTModule(role:adminRole, module: baseModule).save(flush:true)


            def moduleModule = new TModule(name:'模块管理', permissions:'user:*', parentId: adminModule.id).save(flush:true)
            new TRoleTModule(role:adminRole, module: moduleModule).save(flush:true)
            def roleModule = new TModule(name:'角色管理', permissions:'user:*', parentId: adminModule.id).save(flush:true)
            new TRoleTModule(role:adminRole, module: roleModule).save(flush:true)
            def userModule = new TModule(name:'用户管理', permissions:'user:*', parentId: adminModule.id).save(flush:true)
            new TRoleTModule(role:adminRole, module: userModule).save(flush:true)

            log.debug 'DB init OK!'

        }

        def dbBoxes = TDashboardBox.count()
        if(dbBoxes==0) {
            log.debug 'DashboardBox is empty. Init It!'

            new TDashboardBox(title:'用户数', description:'显示用户总数', type:TDashboardBox.TYPE_COUNTBOX, controllerName:'dashboard', actionName:'countAllUser').save(flush:true)
            new TDashboardBox(title:'系统通知', description:'最新的5条系统通知列表', type:TDashboardBox.TYPE_LISTBOX, controllerName:'dashboard', actionName:'listSystemMessage').save(flush:true)
            new TDashboardBox(title:'用户操作记录', description:'最新的6条用户操作记录', type:TDashboardBox.TYPE_LISTBOX, controllerName:'dashboard', actionName:'listUserLog').save(flush:true)
            log.debug 'DashboardBox init OK!'

        }
	}
}
