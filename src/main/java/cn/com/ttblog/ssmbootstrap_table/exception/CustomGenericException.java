package cn.com.ttblog.ssmbootstrap_table.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 自定义异常
 * @author netbuffer
 */
public class CustomGenericException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CustomGenericException(){
		
	}
	public CustomGenericException(int errCode,String errMsg){
		setErrCode(errCode);
		setErrMsg(errMsg);
	}
	
	private int errCode;

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	private String errMsg;

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
