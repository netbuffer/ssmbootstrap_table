package cn.com.ttblog.ssmbootstrap_table.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.com.ttblog.ssmbootstrap_table.Constant.TABLENAME;
import cn.com.ttblog.ssmbootstrap_table.dao.IPermissionDao;
import cn.com.ttblog.ssmbootstrap_table.model.Permission;
import cn.com.ttblog.ssmbootstrap_table.model.User;

@Repository("permissionDao")
public class PermissionDaoImpl implements IPermissionDao{
	
	@Autowired
	private JdbcTemplate jdbcTemple;
	
	public void addPermission(Permission p){
		jdbcTemple.update("insert into "+TABLENAME.PERMISSION.getValue()+"(permission_name,permission_url,permission_code,permission_parentcode) "
				+ "values(?,?,?,?)",new Object[]{p.getName(),p.getUrl(),p.getPermission(),p.getParentPermission()});
	}

	@Override
	public List<Permission> listPermissions() {
		return jdbcTemple.query(
				"select * from "+TABLENAME.PERMISSION.getValue(),
				new RowMapper<Permission>() {
					@Override
					public Permission mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Permission p=new Permission();
						p.setId(rs.getInt("id"));
						p.setName(rs.getString("permission_name"));
						p.setParentPermission(rs.getString("permission_parentcode"));
						p.setPermission(rs.getString("permission_code"));
						p.setUrl(rs.getString("permission_url"));
						return p;
					}
				});
	}
}