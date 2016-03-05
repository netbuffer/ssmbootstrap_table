package cn.com.ttblog.ssmbootstrap_table.dao;

import cn.com.ttblog.ssmbootstrap_table.model.User;

public interface IUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	long getUserListCount();

	int getNewData();
}