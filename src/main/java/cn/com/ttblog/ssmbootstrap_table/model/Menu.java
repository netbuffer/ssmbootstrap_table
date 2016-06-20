package cn.com.ttblog.ssmbootstrap_table.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Menu {

	private Long id;

	private String name;

	private Long parentId;

	List<Menu> menus;

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}