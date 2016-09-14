package cn.com.ttblog.ssmbootstrap_table.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class RolePermission {
	private Integer id;
	private Integer roleId;
	private Integer permissionId;
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer getPermissionId() {
		return permissionId;
	}
	
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
 