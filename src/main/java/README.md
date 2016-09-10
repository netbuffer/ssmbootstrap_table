### shiro默认的过滤器
过滤器名 | 过滤器类
---|---
anon |org.apache.shiro.web.filter.authc.AnonymousFilter
authc |org.apache.shiro.web.filter.authc.FormAuthenticationFilter
authcBasic |org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
perms |org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
port |org.apache.shiro.web.filter.authz.PortFilter
rest |org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
roles |org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
ssl |org.apache.shiro.web.filter.authz.SslFilter
user |org.apache.shiro.web.filter.authc.UserFilter
logout |org.apache.shiro.web.filter.authc.LogoutFilter