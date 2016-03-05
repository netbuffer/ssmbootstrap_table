package cn.com.ttblog.ssmbootstrap_table.service;

import java.util.List;

import cn.com.ttblog.ssmbootstrap_table.model.User;

public interface IUserService {
	public User getUserById(long userId);
	public void addUser(User user);
	public List<User> getUserList(String order, int limit, int offset);
	public long getUserListCount();
	public int getNewData();
}