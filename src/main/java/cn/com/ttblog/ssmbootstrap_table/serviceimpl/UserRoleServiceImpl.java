package cn.com.ttblog.ssmbootstrap_table.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.ttblog.ssmbootstrap_table.dao.IUserRoleDao;
import cn.com.ttblog.ssmbootstrap_table.model.UserRole;
import cn.com.ttblog.ssmbootstrap_table.service.IUserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService {
	private static final Logger LOG = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	
	@Autowired
	private IUserRoleDao userRoleDao;
	
	@Override
	public void addUserRole(UserRole ur) {
		userRoleDao.addUserRole(ur);
	}

}