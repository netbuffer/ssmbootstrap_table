package cn.com.ttblog.ssmbootstrap_table.service;

import java.util.List;
import java.util.Map;

import cn.com.ttblog.ssmbootstrap_table.model.User;

public interface IUserService {
	public User getUserById(long userId);
	public void addUser(User user);
	public List<User> getUserList(String order, int page, int rows);
	public long getUserListCount();
	public int getNewData();
	public List<Map<String, Object>> getDataSum();
}