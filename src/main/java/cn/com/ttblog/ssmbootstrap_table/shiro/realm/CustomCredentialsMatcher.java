package cn.com.ttblog.ssmbootstrap_table.shiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		Object tokenHashedCredentials = hashProvidedCredentials(token, info);
		Object accountCredentials = getCredentials(info);//拿到SimpleAuthenticationInfo中的credentials凭证
		LOG.debug("自定义密码校验,用户输入账号密码:{},数据库账号密码:{}",
				tokenHashedCredentials,accountCredentials);
		// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
		return equals(tokenHashedCredentials, accountCredentials);

	}
}