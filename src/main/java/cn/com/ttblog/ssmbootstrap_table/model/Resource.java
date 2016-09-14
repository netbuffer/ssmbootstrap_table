package cn.com.ttblog.ssmbootstrap_table.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Resource {
	private int id;
	private String name;
	private String permission;
	private String url;
	private String parentPermission;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getParentPermission() {
		return parentPermission;
	}
	public void setParentPermission(String parentPermission) {
		this.parentPermission = parentPermission;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
