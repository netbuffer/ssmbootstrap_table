package cn.com.ttblog.ssmbootstrap_table.service;


import java.util.List;

import cn.com.ttblog.ssmbootstrap_table.model.Permission;

public interface IPermissionService {
	public void addPermission(Permission p);
	public List<Permission> listPermissions();
	public List<Permission> listPermissionsByRoleId(Integer roleId);
}