package cn.com.ttblog.ssmbootstrap_table.model;

import java.math.BigDecimal;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机
     */
    private String phone;

    /**
     * 收货地址
     */
    private String deliveryaddress;

    /**
     * 添加时间
     */
    private Integer adddate;

//    private BigDecimal balance;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取手机
     *
     * @return phone - 手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机
     *
     * @param phone 手机
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取收货地址
     *
     * @return deliveryaddress - 收货地址
     */
    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    /**
     * 设置收货地址
     *
     * @param deliveryaddress 收货地址
     */
    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }

    /**
     * 获取添加时间
     *
     * @return adddate - 添加时间
     */
    public Integer getAdddate() {
        return adddate;
    }

    /**
     * 设置添加时间
     *
     * @param adddate 添加时间
     */
    public void setAdddate(Integer adddate) {
        this.adddate = adddate;
    }

    /**
     * @return balance
     */
//    public BigDecimal getBalance() {
//        return balance;
//    }

    /**
     * @param balance
     */
//    public void setBalance(BigDecimal balance) {
//        this.balance = balance;
//    }
    public String toString(){
    	return ToStringBuilder.reflectionToString(this);
    }
}