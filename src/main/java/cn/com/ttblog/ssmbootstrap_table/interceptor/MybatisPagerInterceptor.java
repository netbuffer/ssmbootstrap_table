package cn.com.ttblog.ssmbootstrap_table.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mybatis拦截器
 * @author netbuffer
 *
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class MybatisPagerInterceptor implements Interceptor {
	private Logger log=LoggerFactory.getLogger(getClass());
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.warn("执行到mybatis拦截器了");
		StatementHandler stmt=(StatementHandler)invocation.getTarget();
		MetaObject metastmt=MetaObject.forObject(stmt,SystemMetaObject.DEFAULT_OBJECT_FACTORY,SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,null);
		MappedStatement mapstmt=(MappedStatement)metastmt.getValue("delegate");
		log.debug("stmt->sqlid:{}",mapstmt.getId());
		log.debug("stmt->sql:{}",stmt.getBoundSql().getSql());
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
