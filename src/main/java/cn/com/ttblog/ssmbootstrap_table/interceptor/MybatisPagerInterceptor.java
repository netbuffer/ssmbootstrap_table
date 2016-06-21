package cn.com.ttblog.ssmbootstrap_table.interceptor;

import java.sql.Connection;
import java.util.Properties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.ttblog.ssmbootstrap_table.util.ReflectUtil;

/**
 * mybatis拦截器
 * @author netbuffer
 *
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class MybatisPagerInterceptor implements Interceptor {
	private Logger log=LoggerFactory.getLogger(getClass());
	private Properties properties;
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if(!Boolean.parseBoolean(properties.getProperty("enable"))){
			return invocation.proceed();
		}
		log.warn("===========================执行mybatis拦截器开始===========================");
		StatementHandler stmt=(StatementHandler)invocation.getTarget();
		StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(stmt, "delegate");
		MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");
		log.info("拦截到的 执行的sql-id:{}",mappedStatement.getId());
		log.info("拦截到的 sql:{},param:{}",stmt.getBoundSql().getSql(),ToStringBuilder.reflectionToString(stmt.getBoundSql().getParameterObject()));
		log.warn("===========================执行mybatis拦截器完成===========================");
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		log.info("set "+getClass().getName()+" properties to:{}",properties.getProperty("enable"));
		this.properties=properties;
	}

}
