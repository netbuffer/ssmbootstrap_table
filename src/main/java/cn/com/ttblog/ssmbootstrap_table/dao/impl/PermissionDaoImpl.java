package cn.com.ttblog.ssmbootstrap_table.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	@Override
	public List<Permission> listPermissionsByRoleId(Integer roleId) {
		return jdbcTemple.query(
				"select * from "+TABLENAME.PERMISSION.getValue()+
				" WHERE id IN ( SELECT rp.permission_id FROM role_permission rp INNER JOIN role WHERE rp.role_id = ?)",new Object[]{roleId},
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

	@Override
	public List<Permission> listPermissionsByUserId(Integer userId) {
		List<Permission> permissions=null;
		try {
			permissions=jdbcTemple.query("select * from permission where id in("
				+"select rp.permission_id from role_permission rp INNER JOIN role where rp.role_id in"
				+"(select r.id from role r,user_role ur where ur.role_id=r.id and ur.user_id=?))", new Object[]{userId}, new RowMapper<Permission>() {
					@Override
					public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
						Permission r=new Permission();
						r.setId(rs.getInt("id"));
						r.setName(rs.getString("permission_name"));
						r.setUrl(rs.getString("permission_url"));
						r.setPermission(rs.getString("permission_code"));
						r.setParentPermission(rs.getString("permission_parentcode"));
						return r;
					}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return permissions;
	}
}