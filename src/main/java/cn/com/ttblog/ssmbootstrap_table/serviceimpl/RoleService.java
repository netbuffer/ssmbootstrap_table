package cn.com.ttblog.ssmbootstrap_table.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ttblog.ssmbootstrap_table.dao.IRoleDao;
import cn.com.ttblog.ssmbootstrap_table.model.Role;
import cn.com.ttblog.ssmbootstrap_table.service.IRoleService;

@Service("roleService")
public class RoleService implements IRoleService{
	@Autowired
	private IRoleDao roleDao;
	@Override
	public void addRole(Role r) {
		roleDao.addRole(r);
	}
	@Override
	public List<Role> listRoles() {
		return roleDao.listRoles();
	}

}