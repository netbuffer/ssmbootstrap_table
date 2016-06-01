package cn.com.ttblog.ssmbootstrap_table.webservice;

import javax.annotation.Resource;
import javax.jws.HandlerChain;

import org.springframework.stereotype.Service;

import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

@Service("userServiceWebservice")
@HandlerChain(file="cxf-handler-chain.xml")
public class UserServiceEndpoint implements UserServiceWebservice {
	
	@Resource
	private IUserService userService;

	@Override
	public User getUser(Long id) {
//		return new User();
		return userService.getUserById(id);
	}

}
