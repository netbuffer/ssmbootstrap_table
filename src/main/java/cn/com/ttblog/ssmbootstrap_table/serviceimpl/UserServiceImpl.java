package cn.com.ttblog.ssmbootstrap_table.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.com.ttblog.ssmbootstrap_table.dao.IMenuDao;
import cn.com.ttblog.ssmbootstrap_table.dao.IUserDao;
import cn.com.ttblog.ssmbootstrap_table.model.Menu;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private Logger logger=LoggerFactory.getLogger(getClass());
	/**
	 * @resource 是按照name注入，@autowired是按照type注入
	 */
	@Resource
	private IUserDao userDao;
	@Resource
	private SqlSessionTemplate sqlSession;
	@Resource
	private IMenuDao menuDao;
	
//	@Cacheable(value = { "userCache" })
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
	public void addUM(){
		System.out.println(String.format("tran1:%s  %n tran1detail:%s", TransactionAspectSupport.currentTransactionStatus().toString(),ToStringBuilder.reflectionToString(TransactionAspectSupport.currentTransactionStatus())));
		User u=new User();
		u.setName(RandomStringUtils.randomAlphabetic(4));
		addUser(u);
		Menu m=new Menu();
		m.setName(RandomStringUtils.randomAlphabetic(4));
		menuDao.insert(m);
		System.out.println(String.format("tran2:%s  %n tran2detail:%s", TransactionAspectSupport.currentTransactionStatus().toString(),ToStringBuilder.reflectionToString(TransactionAspectSupport.currentTransactionStatus())));
		throw new RuntimeException("error");
	}
	
//	@Transactional
	@Override
	public void addUMtest() throws IllegalArgumentException {
		User u=new User();
		u.setName(RandomStringUtils.randomAlphabetic(4));
		addUser(u);
		Menu m=new Menu();
		m.setName(RandomStringUtils.randomAlphabetic(4));
		menuDao.insert(m);
		throw new IllegalArgumentException("test");
	}

	/**
	 * getUserList
	 * @param order order by adddate ${order} asc desc
	 */
	@Cacheable(value = { "userCache" })
	@Override
	public List<User> getUserList(String order, int limit, int offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order", order);
		params.put("limit", limit);
		params.put("offset", offset);
		return sqlSession.selectList(IUserDao.class.getName() + ".selectList",
				params);
	}

//	@Cacheable(value = { "userCache" })
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

	@Override
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

}