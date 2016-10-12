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
import cn.com.ttblog.ssmbootstrap_table.dao.IRoleDao;
import cn.com.ttblog.ssmbootstrap_table.model.Role;

@Repository("roleDao")
public class RoleDaoImpl implements IRoleDao{

	@Autowired
	private JdbcTemplate jdbcTemple;

	@Override
	public void addRole(Role r) {
		jdbcTemple.update("insert into "+TABLENAME.ROLE.getValue()+"(role_name,role_description) values(?,?)",new Object[]{r.getName(),r.getDescription()});
	}

	@Override
	public List<Role> listRoles() {
		return jdbcTemple.query(
				"select * from "+TABLENAME.ROLE.getValue(),
				new RowMapper<Role>() {
					@Override
					public Role mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Role r=new Role();
						r.setId(rs.getInt("id"));
						r.setName(rs.getString("role_name"));
						r.setDescription(rs.getString("role_description"));
						return r;
					}
				});
	}

	@Override
	public Role findRoleById(Integer roleId) {
		Role role=null;
		try {
			role=jdbcTemple.queryForObject("select * from "+TABLENAME.ROLE.getValue()+" where id=?",
					new Object[] { roleId },
					new RowMapper<Role>(){
						@Override
						public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
							Role r=new Role();
							r.setId(rs.getInt("id"));
							r.setName(rs.getString("role_name"));
							r.setDescription(rs.getString("role_description"));
							return r;
						}
				
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return role;
	}
}