# web-admin
grails web管理后台

# 项目说明
本项目作为管理系统
当前最新版本: `0.1`
## 主要功能
- 完整的后台管理系统UI(采用`AdminLTE`，支持Bootstrap样式风格)
- 完整用户权限管理(用户、角色、权限)(目前采用`Shiro`) [配置方法](#初始化权限数据)
- 支持自定义左侧菜单，方便扩展 [详细用法](#自定义左侧菜单)
- 支持自定义系统Title
- 登录后首页模块化(测试中)

## 待完成功能
- 可以自定义网站title/description/keyword等内容
- 系统通知发布
- 首页布局模块化，支持自定义模块

# 使用方法
依赖如下插件`可以选择使用插件最新版本`:
```java
compile "org.grails.plugins:shiro:1.2.1"
compile ":asset-pipeline:1.9.9"
runtime ":hibernate4:4.3.6.1"
runtime ":jquery:1.11.1"
```

## 初始化权限数据
你可以在`controller`,`service`中调用如下代码，帮助你初始化数据库
```java
// 引用声明
def userSecurityService

// 调用初始化方法
userSecurityService.initDB()
```
或者在`conf/Bootstrap.groovy`中增加启动代码：
```java
def userSecurityService

def init = { servletContext ->
	if (grails.util.Environment.current == grails.util.Environment.DEVELOPMENT) {
		// 仅在开发环境下初始化代码
        userSecurityService.initDB()
    }
}
```
NOTE: 默认初始化权限后，管理员账号为`admin`,密码`111111`

## 自定义系统Title
自定义系统标题通过i18n实现，通过messages.properties自定义
- `admin-web.title` - 系统标题(登录界面、登陆后首页等位置)

## 自定义左侧菜单
增加自定义左侧菜单`views/leftmenu/customMenu.gsp`, 内容例如：
```html
<li class="treeview">
  <a href="#">
    <i class="fa fa-users"></i> <span>自定义菜单</span><i class="fa fa-angle-left pull-right"></i>
  </a>
  <ul style="display:none;" class="treeview-menu">
    <li class="">
      <a style="margin-left: 10px;" href="${request.contextPath}/custom/menu1"><i class="fa fa-user-plus"></i> 菜单1</a>
    </li>
    <li class="">
      <a style="margin-left: 10px;" href="${request.contextPath}/custom/menu2"><i class="fa fa-folder-o"></i> 菜单2</a>
    </li>
    <li class="">
      <a style="margin-left: 10px;" href="${request.contextPath}/custom/menu3"><i class="fa fa-male"></i> 菜单3</a>
    </li>
  </ul>
</li>
```

## 自定义Admin模板application.js引用
admin模板引用位置如下：
```html
        <!-- Bootstrap -->
        <script src="http://cdn.bootcss.com/bootstrap/3.3.1/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <asset:javascript src="admin/admin.js" />
        <asset:javascript src="admin/AdminLTE/app.js" />
        <asset:javascript src="admin/AdminLTE/holder.min.js" />
        <!-- 加载自定义application.js -->
        <asset:javascript src="application.js" />
    </body>
</html>
```
