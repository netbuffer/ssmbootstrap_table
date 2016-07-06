package cn.com.ttblog.ssmbootstrap_table.dao;

import cn.com.ttblog.ssmbootstrap_table.model.Menu;

public interface IMenuDao {
    Menu getMenuById(Long l);
    void insert(Menu m);
}