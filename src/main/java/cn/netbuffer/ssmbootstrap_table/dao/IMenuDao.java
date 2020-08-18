package cn.netbuffer.ssmbootstrap_table.dao;

import cn.netbuffer.ssmbootstrap_table.model.Menu;

public interface IMenuDao {
    Menu getMenuById(Long l);
    void insert(Menu m);
}