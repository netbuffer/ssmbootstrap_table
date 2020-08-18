package cn.netbuffer.ssmbootstrap_table.serviceimpl;

import javax.annotation.Resource;

import cn.netbuffer.ssmbootstrap_table.dao.IMenuDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.netbuffer.ssmbootstrap_table.model.Menu;
import cn.netbuffer.ssmbootstrap_table.service.IMenuService;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Resource
	private IMenuDao menuDao;
	@Override
	public void addMenu(Menu m) {
		menuDao.insert(m);
	}

}