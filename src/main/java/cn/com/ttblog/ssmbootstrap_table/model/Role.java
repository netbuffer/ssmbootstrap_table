package cn.com.ttblog.ssmbootstrap_table.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Role {
	private Integer id;
	private Integer name;
	private Integer description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	public Integer getDescription() {
		return description;
	}

	public void setDescription(Integer description) {
		this.description = description;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}