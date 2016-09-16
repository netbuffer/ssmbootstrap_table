package cn.com.ttblog.ssmbootstrap_table.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.com.ttblog.ssmbootstrap_table.Constant.TABLENAME;
import cn.com.ttblog.ssmbootstrap_table.dao.IUserRoleDao;
import cn.com.ttblog.ssmbootstrap_table.model.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl implements IUserRoleDao{
	
	@Autowired
	private JdbcTemplate jdbcTemple;

	@Override
	public void addUserRole(UserRole ur) {
		jdbcTemple.update("insert into "+TABLENAME.USERROLE.getValue()+"(user_id,role_id) values(?,?)",
				new Object[]{ur.getUserId(),ur.getRoleId()});
	}
}