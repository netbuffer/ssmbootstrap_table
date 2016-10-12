package cn.com.ttblog.ssmbootstrap_table.service;

import java.util.List;

import cn.com.ttblog.ssmbootstrap_table.model.Role;

public interface IRoleService {
	public void addRole(Role r);
	public List<Role> listRoles();
	public Role findRoleById(Integer roleId);
}