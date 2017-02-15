package cn.com.ttblog.ssmbootstrap_table.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启用异步方法
 * 发现在xml中配置<task:annotation-driven></task:annotation-driven>不生效,
 * 基于java config是生效的
 * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/annotation/EnableAsync.html
 */
@Configuration
@EnableAsync
public class Config {
}
