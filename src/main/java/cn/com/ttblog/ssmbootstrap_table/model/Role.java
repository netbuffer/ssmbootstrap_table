package cn.com.ttblog.ssmbootstrap_table.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Role {
	private Integer id;
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}