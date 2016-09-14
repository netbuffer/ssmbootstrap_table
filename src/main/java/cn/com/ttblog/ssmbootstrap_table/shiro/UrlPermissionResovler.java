package cn.com.ttblog.ssmbootstrap_table.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlPermissionResovler implements PermissionResolver {
	
	private static final Logger LOG=LoggerFactory.getLogger(UrlPermissionResovler.class);
	
	@Override
	public Permission resolvePermission(String permissionString) {
		LOG.debug("resolve permission:{}",permissionString);
		if(permissionString.startsWith("/")) {
			LOG.debug("use UrlPermission!");
			return new UrlPermission(permissionString);
		}
		LOG.debug("use WildcardPermission!");
		return new WildcardPermission(permissionString);
	}

}
