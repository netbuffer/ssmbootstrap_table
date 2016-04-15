package cn.com.ttblog.ssmbootstrap_table.dao;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;
import cn.com.ttblog.ssmbootstrap_table.model.User;

public interface IUserDao extends Mapper<User> {

	long getUserListCount();

	int getNewData();

	List<Map<String, Object>> getDataSum();
}