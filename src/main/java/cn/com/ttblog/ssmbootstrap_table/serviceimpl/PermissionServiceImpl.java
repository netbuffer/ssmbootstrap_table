package cn.com.ttblog.ssmbootstrap_table.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ttblog.ssmbootstrap_table.dao.IPermissionDao;
import cn.com.ttblog.ssmbootstrap_table.model.Permission;
import cn.com.ttblog.ssmbootstrap_table.service.IPermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService{
	@Autowired
	private IPermissionDao permissionDao;
	
	public void addPermission(Permission p){
		permissionDao.addPermission(p);
	}

	@Override
	public List<Permission> listPermissions() {
		return permissionDao.listPermissions();
	}

	@Override
	public List<Permission> listPermissionsByRoleId(Integer roleId) {
		return permissionDao.listPermissionsByRoleId(roleId);
	}

	@Override
	public Object listPermissionsByUserId(Integer userId) {
		return permissionDao.listPermissionsByUserId(userId);
	}
}