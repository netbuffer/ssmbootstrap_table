package cn.com.ttblog.ssmbootstrap_table;

import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;

/**
 * PageUtils是基于Druid SQL Parser实现的分页SQL自动生成类。目前支持MySql、Oracle、DB2、SQL Server四种数据库类型
 * https://github.com/alibaba/druid/wiki/Use_PageUtils
 */
public class TestDruidPageUtils {

	@Test
	@Ignore
	public void testCount() {
		String sql = "select id, name from user order by id";
		// 这里的dbType是MySql，可以更换为Oracle、DB2和SQL_Server
		System.out.println("PagerUtils.count(sql, JdbcConstants.MYSQL):"
				+ PagerUtils.count(sql, JdbcConstants.MYSQL));
		System.out.println("PagerUtils.count(sql, JdbcConstants.ORACLE):"
				+ PagerUtils.count(sql, JdbcConstants.ORACLE));
		System.out.println("PagerUtils.count(sql, JdbcConstants.SQL_SERVER):"
				+ PagerUtils.count(sql, JdbcConstants.SQL_SERVER));
	}

	@Test
	public void testLimit() {
		String sql = "select * from user";
		System.out.println("PagerUtils.limit(sql, JdbcConstants.ORACLE, 20, 10):"+PagerUtils.limit(sql, JdbcConstants.ORACLE, 20, 10));
		System.out.println("PagerUtils.limit(sql, JdbcConstants.MYSQL, 20, 10):"+PagerUtils.limit(sql, JdbcConstants.MYSQL, 20, 10));
		System.out.println("PagerUtils.limit(sql, JdbcConstants.SQL_SERVER, 20, 10):"+PagerUtils.limit(sql, JdbcConstants.SQL_SERVER, 20, 10));
	}
}
