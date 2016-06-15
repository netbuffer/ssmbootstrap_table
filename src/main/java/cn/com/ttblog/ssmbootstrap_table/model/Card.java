package cn.com.ttblog.ssmbootstrap_table.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 使用lombok简化代码 https://projectlombok.org/features/index.html
 * @package cn.com.ttblog.ssmbootstrap_table.model
 * @author netbuffer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable{
	private Long userId;
	private String cardNo;
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}