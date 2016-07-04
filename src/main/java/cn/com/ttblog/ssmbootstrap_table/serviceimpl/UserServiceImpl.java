package cn.com.ttblog.ssmbootstrap_table.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
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
	@Resource
	private SqlSessionTemplate sqlSession;

	@Override
	public User getUserById(long userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public void addUser(User user) {
		Random r = new Random();
		sqlSession.insert(IUserDao.class.getName() + ".insert", user);
		// 事务测试
//		 int i=1/0;
	}

	@Override
	public List<User> getUserList(String order, int limit, int offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order", order);
		params.put("limit", limit);
		params.put("offset", offset);
		return sqlSession.selectList(IUserDao.class.getName() + ".selectList",
				params);
	}

	@Override
	public List<User> getUserList(String search, String order, int limit,
			int offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order", order);
		params.put("limit", limit);
		params.put("offset", offset);
		params.put("search", search);
		return sqlSession.selectList(IUserDao.class.getName() + ".selectListWithQuery",
				params);
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

}