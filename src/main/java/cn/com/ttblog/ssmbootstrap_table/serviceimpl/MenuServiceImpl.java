package cn.com.ttblog.ssmbootstrap_table.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.ttblog.ssmbootstrap_table.dao.IMenuDao;
import cn.com.ttblog.ssmbootstrap_table.model.Menu;
import cn.com.ttblog.ssmbootstrap_table.service.IMenuService;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {
	@Resource
	private IMenuDao menuDao;
	@Override
	public void addMenu(Menu m) {
		menuDao.insert(m);
	}

}