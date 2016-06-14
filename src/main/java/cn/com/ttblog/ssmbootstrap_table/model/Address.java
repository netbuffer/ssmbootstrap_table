package cn.com.ttblog.ssmbootstrap_table.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Address {

	private Long userId;

	private String province;

	private String city;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}