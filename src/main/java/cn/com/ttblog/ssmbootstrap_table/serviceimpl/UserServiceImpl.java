package cn.com.ttblog.ssmbootstrap_table.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.ttblog.ssmbootstrap_table.dao.IUserDao;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	/**
	 * @resource 是按照name注入，@autowired是按照type注入
	 */
	@Resource
	private IUserDao userDao;

	@Override
	public User getUserById(long userId) {
		return userDao.getUserById(userId);
	}

	@Transactional
	@Override
	public void addUser(User user) {
//		Random r = new Random();
		userDao.addUser(user);
	}

	@Override
	public List<User> getUsersByIds(List<Long> ids) {
		return userDao.getUsersByIds(ids);
	}

	@Override
	public List<User> getUserList(String search, String order, int limit,
			int offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order", order);
		params.put("limit", limit);
		params.put("offset", offset);
		params.put("search", search);
		return userDao.getUserList(params);
	}

	@Override
	public long getUserListCount() {
		return userDao.getUserListCount();
	}

	@Override
	public int getNewData() {
		return userDao.getNewData();
	}

	@Override
	public List<Map<String, Object>> getDataSum() {
		return userDao.getDataSum();
	}

	@Override
	public List<User> getUserList(String order, int limit, int offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order", order);
		params.put("limit", limit);
		params.put("offset", offset);
		return userDao.getUserList(params);
	}

	@Override
	public List<String> listRoleSnByUser(Long uid) {
		return null;
	}

	@Override
	public List<cn.com.ttblog.ssmbootstrap_table.model.Resource> listAllResource(
			Long uid) {
		return null;
	}

	@Override
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

}