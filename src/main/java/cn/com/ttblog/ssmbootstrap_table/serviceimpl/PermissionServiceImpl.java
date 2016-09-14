package cn.com.ttblog.ssmbootstrap_table.serviceimpl;

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
}