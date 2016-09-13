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
	
	/**
	 * 授权流程
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		User user = ((User)principals.getPrimaryPrincipal());
		Long uid = user.getId();
		List<String> roles = userService.listRolesByUser(uid);
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
	
	/**
	 * 认证流程
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		LOG.debug("认证信息:{}",usernamePasswordToken);
		if(usernamePasswordToken==null||usernamePasswordToken.getPrincipal()==null){
			LOG.error("用户名或密码不能为空");
			throw new AccountException("用户名或密码不能为空");
		}
		String username = token.getPrincipal().toString();
		if(StringUtils.isEmpty(username)){
			LOG.error("用户名不能为空");
			throw new AccountException("用户名不能为空");
		}
		if(token.getCredentials()==null||StringUtils.isEmpty(token.getCredentials().toString())){
			LOG.error("密码不能为空");
			throw new AccountException("密码不能为空");
		}
		//清空上次权限缓存
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				token.getPrincipal(), getName());
		clearCachedAuthorizationInfo(principals);
		String password = new String((char[])token.getCredentials());
		User user = null;
		try{
			user=userService.findByUserName(username);
		}catch(Exception e){
			LOG.error("查询用户:{},发生错误:",username,e);
			throw new AccountException("请重试~");
		}
		if(user==null){
			LOG.error("用户不存在");
			throw new UnknownAccountException("用户不存在");
		}
		if(user.getIsLock()==1){
			LOG.error("用户被锁定");
			throw new LockedAccountException("账户被锁定");
//			throw new DisabledAccountException("该账户被禁用");
		}
		LOG.debug("拿到用户信息:{},表单密码:{}",user,password);
		//set的是数据库中加密后的密码
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(), this.getName());
		//设置加密盐
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
		LOG.debug("认证完成:{}",info);
		return info;
	}

}
