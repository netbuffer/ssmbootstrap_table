package cn.com.ttblog.ssmbootstrap_table.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import cn.com.ttblog.ssmbootstrap_table.Constant.TABLENAME;
import cn.com.ttblog.ssmbootstrap_table.dao.IPermissionDao;
import cn.com.ttblog.ssmbootstrap_table.model.Permission;

@Repository("permissionDao")
public class PermissionDaoImpl implements IPermissionDao{
	
	@Autowired
	private JdbcTemplate jdbcTemple;
	
	public void addPermission(Permission p){
		jdbcTemple.update("insert into "+TABLENAME.PERMISSION.getValue()+"(permission_name,permission_url,permission_code,permission_parentcode) "
				+ "values(?,?,?,?)",new Object[]{p.getName(),p.getUrl(),p.getPermission(),p.getParentPermission()});
	}
}