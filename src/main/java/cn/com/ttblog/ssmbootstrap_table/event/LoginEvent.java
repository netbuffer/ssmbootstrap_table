package cn.com.ttblog.ssmbootstrap_table.event;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.ApplicationEvent;
import java.util.Map;

public class LoginEvent extends ApplicationEvent {

	private Map data;

	public LoginEvent(Object source, Map<String, String> data) {
		super(source);
		this.data=data;
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
