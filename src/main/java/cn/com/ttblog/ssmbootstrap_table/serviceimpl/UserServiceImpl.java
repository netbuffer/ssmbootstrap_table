package cn.com.ttblog.ssmbootstrap_table.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.ttblog.ssmbootstrap_table.dao.IUserDao;
import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
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
		if(null!=user.getPassword()){
			SecureRandomNumberGenerator randomNumberGenerator =  new SecureRandomNumberGenerator();
			String salt=randomNumberGenerator.nextBytes().toHex()+new String(Hex.encodeHex(user.getName().getBytes(), true));
			SimpleHash hash = new SimpleHash("md5",user.getPassword(),salt,1); 
			user.setPassword(hash.toHex());
			user.setSalt(salt);
		}
		LOG.debug("service层保存用户信息:{}",user);
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
	public List<cn.com.ttblog.ssmbootstrap_table.model.Resource> listAllResource(
			Long uid) {
		return userDao.listAllResource(uid);
	}

	@Override
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public List<String> listRolesByUser(Long uid) {
		return userDao.listRolesByUser(uid);
	}

}