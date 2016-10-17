package cn.com.ttblog.ssmbootstrap_table.dao;

import java.util.List;

import cn.com.ttblog.ssmbootstrap_table.model.Role;

public interface IRoleDao {
	public void addRole(Role r);
	public List<Role> listRoles();
	public Role findRoleById(Integer roleId);
	public List<Role> listRolesByUserId(Long userId);
}