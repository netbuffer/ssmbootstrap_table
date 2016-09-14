package cn.com.ttblog.ssmbootstrap_table.Constant; 

public enum TABLENAME {
	USER("user"),USERROLE("user_role"),ROLE("role"),PERMISSION("permission"),
	ROLEPERMISSION("role_permission");
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private TABLENAME(String name){
		this.value=name;
	}
}