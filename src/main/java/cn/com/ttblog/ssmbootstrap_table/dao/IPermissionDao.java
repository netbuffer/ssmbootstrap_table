package cn.com.ttblog.ssmbootstrap_table.dao;

import java.util.List;

import cn.com.ttblog.ssmbootstrap_table.model.Permission;

public interface IPermissionDao {
	public void addPermission(Permission p);
	public List<Permission> listPermissions();
}