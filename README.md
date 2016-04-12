# web-admin
grails web管理后台插件

# 项目说明
本项目作为企用管理系统的Grails插件
当前最新版本: `1.0.0`
## 主要功能
- 完整的后台管理系统UI(采用`AdminLTE`，支持Bootstrap样式风格)
- 完整用户权限管理(用户、角色、权限)(目前采用`Shiro`) [配置方法](#初始化权限数据)
- 支持自定义左侧菜单，方便扩展 [详细用法](#自定义左侧菜单)
- 支持自定义系统Title
- 登录后首页模块化(测试中)
- 文件上传默认选择OpenApiUpload [查看OpenApiUpload配置及用法](http://git.qiyestore.com/maven/openapi-client#openupload使用说明)

## 待完成功能
- 可以自定义网站title/description/keyword等内容
- 系统通知发布
- 首页布局模块化，支持自定义模块

# 使用方法
## 安装插件
在`conf/BuildConfig.groovy`中，增加如下配置：
```java
repositories {
	mavenRepo "http://repo.qiyestore.com/repository/m2/"
}

plugins {
	compile "com.qiyestore.grails.plugin:admin-web:1.0.0"
}
```

同时还依赖如下插件`可以选择使用插件最新版本`:
```java
compile ":asset-pipeline:1.9.9"
runtime ":hibernate4:4.3.6.1"
runtime ":jquery:1.11.1"
```

## 删除部分默认配置，解决冲突
### 删除application.js中加载jquery部分

> create-app创建完项目后，默认application.js中加载了jquery，与admin-web模板中jquery重复加载，造成冲突。因此删除掉application.js中加载的jquery

```js
// 删除掉assets/javascript/application.js中这一行
//= require jquery
```

### 删除默认`/`urlmapping配置

> 解决默认地址`/`加载问题登陆页面问题

```java
// 注释掉UrlMappings.groovy中下面这一行
"/"(view:"/index")
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
application.js文件内容demo:
```js
//= require_self

console.log('hahahahaha');
```

# 编译及发布方法
编译项目安装`release`插件
```sh
grails compile
```
修改版本号`AdminWebGrailsPlugin`
```java
def version = "x.xx" // x.xx 为版本号
```
发布插件到Maven Repository
```sh
grails publish-plugin --repository=qiyestoreRepo
```
