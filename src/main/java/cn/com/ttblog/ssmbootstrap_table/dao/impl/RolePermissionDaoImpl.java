package cn.com.ttblog.ssmbootstrap_table.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.com.ttblog.ssmbootstrap_table.Constant.TABLENAME;
import cn.com.ttblog.ssmbootstrap_table.dao.IRolePermissionDao;
import cn.com.ttblog.ssmbootstrap_table.model.RolePermission;

@Repository("rolePermissionDao")
public class RolePermissionDaoImpl implements IRolePermissionDao{
	
	@Autowired
	private JdbcTemplate jdbcTemple;
	
	@Override
	public void addRolePermission(RolePermission rp) {
		jdbcTemple.update("insert into "+TABLENAME.ROLEPERMISSION.getValue()+"(role_id,permission_id) values(?,?)",
				new Object[]{rp.getRoleId(),rp.getPermissionId()});
	}
}