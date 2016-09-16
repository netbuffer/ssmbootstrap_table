package cn.com.ttblog.ssmbootstrap_table.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ttblog.ssmbootstrap_table.dao.IRolePermissionDao;
import cn.com.ttblog.ssmbootstrap_table.model.RolePermission;
import cn.com.ttblog.ssmbootstrap_table.service.IRolePermissionService;

@Service("rolePermissionService")
public class RolePermissionServiceImpl implements IRolePermissionService{
	@Autowired
	private IRolePermissionDao rolePermissionDao;
	@Override
	public void addRolePermission(RolePermission rp) {
		rolePermissionDao.addRolePermission(rp);
	}
}