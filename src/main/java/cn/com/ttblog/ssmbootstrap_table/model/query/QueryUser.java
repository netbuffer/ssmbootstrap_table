package cn.com.ttblog.ssmbootstrap_table.model.query;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 查询User实体
 * @author netbuffer
 */
public class QueryUser {
	
	private Long id;
	private String name;
	private String sex;
	private Integer age;
	private String phone;
	private String deliveryaddress;
	private Integer beginDate;
	private Integer endDate;
	
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
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDeliveryaddress() {
		return deliveryaddress;
	}
	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}
	public Integer getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Integer beginDate) {
		this.beginDate = beginDate;
	}
	public Integer getEndDate() {
		return endDate;
	}
	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
