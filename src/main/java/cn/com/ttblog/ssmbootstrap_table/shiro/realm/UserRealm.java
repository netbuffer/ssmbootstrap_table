package cn.com.ttblog.ssmbootstrap_table.shiro.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ttblog.ssmbootstrap_table.model.Resource;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

public class UserRealm extends AuthorizingRealm {
	private static final Logger LOG=LoggerFactory.getLogger(UserRealm.class);
	@Autowired
	private IUserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		User user = ((User)principals.getPrimaryPrincipal());
		Long uid = user.getId();
		List<String> roles = userService.listRoleSnByUser(uid);
		List<Resource> reses = userService.listAllResource(uid);
		List<String> permissions = new ArrayList<String>();
		for(Resource r:reses) {
			permissions.add(r.getUrl());
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(new HashSet<String>(roles));
		info.setStringPermissions(new HashSet<String>(permissions));
		return info;
	}
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		LOG.debug("认证信息:{}",usernamePasswordToken);
		String username = token.getPrincipal().toString();
		if(StringUtils.isEmpty(username)){
			throw new AccountException("用户名不能为空");
		}
		//清空上次权限缓存
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				token.getPrincipal(), getName());
		clearCachedAuthorizationInfo(principals);
		String password = new String((char[])token.getCredentials());
		User user = userService.login(username, password);
//		throw new UnknownAccountException("用户不存在");
//		throw new DisabledAccountException("该账户被禁用");
//		throw new LockedAccountException("账户被锁定")
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,password, this.getName());
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getName()));
		LOG.debug("认证完成:{}",info);
		return info;
	}

}
