package cn.com.ttblog.ssmbootstrap_table.model;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Range;

@XmlRootElement
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4193866401992779318L;
	/**
	 * 用户id
	 */
	private Long id;
	@Size(min = 2, max = 6, message = "{用户名长度必须在2到6个字符之间}")
	private String name;

	private String sex;

	@NotNull(message = "年龄不能为空")
	@Range(min = 1, max = 150)
	private Integer age;

	private String phone;

	private String deliveryaddress;

	private Integer adddate;

	private transient String comments;
	
	private String[] img;
	
	public String[] getImg() {
		return img;
	}

	public void setImg(String[] img) {
		this.img = img;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	// 用户使用的地址
	List<Address> addresses;

	private Card card;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
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
		this.phone = phone == null ? null : phone.trim();
	}

	public String getDeliveryaddress() {
		return deliveryaddress;
	}

	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress == null ? null : deliveryaddress.trim();
	}

	public Integer getAdddate() {
		return adddate;
	}

	public void setAdddate(Integer adddate) {
		this.adddate = adddate;
	}

	public User(String name, String sex, Integer age, String phone, String deliveryaddress, Integer adddate,
			String comments, List<Address> addresses, Card card) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.deliveryaddress = deliveryaddress;
		this.adddate = adddate;
		this.comments = comments;
		this.addresses = addresses;
		this.card = card;
	}

	public User() {

	}
	
	public void init(){
		System.out.println("user init!");
	}
	
//	public synchronized void syn(){
//		System.out.println("syn!");
//		try {
//			TimeUnit.SECONDS.sleep(20);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void syn(){
		synchronized(this){
			System.out.println("syn!");
			try {
				TimeUnit.SECONDS.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void syn2() {
		System.out.println("syn2!");
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		//允许打印transient成员
		return ToStringBuilder.reflectionToString(this,null,true);
	}

}