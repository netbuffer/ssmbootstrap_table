package cn.com.ttblog.ssmbootstrap_table.dao;

import java.util.List;
import java.util.Map;

import cn.com.ttblog.ssmbootstrap_table.model.User;

public interface IUserDao {

	int insert(User record);

	long getUserListCount();

	int getNewData();

	List<Map<String, Object>> getDataSum();

	List<User> getUsersByIds(List<Long> ids);

	List<User> getUserList(Map<String, Object> params);

	User getUserById(long userId);

	User findByUserName(String username);

	void addUser(User user);

	List<String> listRolesByUser(Long uid);

	List<cn.com.ttblog.ssmbootstrap_table.model.Resource> listAllResource(
			Long uid);

}