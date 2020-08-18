package cn.netbuffer.ssmbootstrap_table.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import cn.netbuffer.ssmbootstrap_table.model.User;

@WebService
public interface UserServiceWebservice {

	@WebMethod
	public User getUser(@WebParam(name = "id") Long id);
}
