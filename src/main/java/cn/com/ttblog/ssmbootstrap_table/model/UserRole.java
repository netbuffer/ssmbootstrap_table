package cn.com.ttblog.ssmbootstrap_table.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UserRole {
	private Integer id;
	private Integer roleId;
	private Integer userId;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
 