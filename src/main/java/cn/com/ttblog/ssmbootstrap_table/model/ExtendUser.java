package cn.com.ttblog.ssmbootstrap_table.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExtendUser extends User implements Serializable {
	
	private String[] photo;
	
	public String[] getPhoto() {
		return photo;
	}
	public void setPhoto(String[] photo) {
		this.photo = photo;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,null,true);
	}
}