package cn.com.ttblog.ssmbootstrap_table.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 自定义标签开发
 * 教程:http://www.runoob.com/jsp/jsp-custom-tags.html
 * @author netbuffer
 *
 */
public class LongToDateTag extends SimpleTagSupport {
	private Object value;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	private String format;
	public void doTag() throws JspException, IOException {
		if(getValue()!=null&&getFormat()!=null){
			SimpleDateFormat fmt=new SimpleDateFormat(getFormat());
			getJspContext().getOut().print(fmt.format(new Date(Long.parseLong(getValue().toString()))));
		}
		
	}
}