package cn.com.ttblog.ssmbootstrap_table.mbean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * http://blog.csdn.net/shirdrn/article/details/6358688
 * http://blog.csdn.net/yaerfeng/article/details/28232435
 * 
 * @author netbuffer 将类标记为JMX受控资源
 */
@ManagedResource(objectName = "annojmx:myjao=BeanMonitor", description = "测试mbean使用")
public class BeanMonitor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private long startTime;
	private long endTime;
	private long duration;

	/**
	 * 将getter或者setter标识为部分jmx属性
	 * 
	 * @return
	 */
	@ManagedAttribute
	public long getStartTime() {
		return startTime;
	}

	@ManagedAttribute
	public void setStartTime(long startTime) {
		logger.debug("设置starttime:{}", startTime);
		this.startTime = startTime;
	}

	@ManagedAttribute
	public long getEndTime() {
		logger.debug("获取getEndTime:{}", endTime);
		return endTime;
	}

	@ManagedAttribute
	public void setEndTime(long endTime) {
		logger.debug("设置setEndTime:{}", endTime);
		this.endTime = endTime;
	}

	@ManagedAttribute
	public long getDuration() {
		logger.debug("获取getDuration:{}", duration);
		return duration;
	}

	@ManagedAttribute
	public void setDuration(long duration) {
		logger.debug("设置setDuration:{}", duration);
		this.duration = duration;
	}

	/**
	 * jmx操作方法
	 * 
	 * @return
	 */
	@ManagedOperation
	public String show() {
		logger.debug("调用toString:{}", this);
		return toString();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}