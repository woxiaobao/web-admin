class AdminWebUrlMappings {

	static mappings = {
        
        "/auth/$action?/$id?(.$format)?" (controller: 'auth')
        "/user/$action?/$id?(.$format)?" (controller: 'user')
        "/dashboard/$action?/$id?(.$format)?" (controller: 'dashboard')
        "/"(controller:"dashboard", action:"index")
        "500"(view:'/admin/error')
	}
}
