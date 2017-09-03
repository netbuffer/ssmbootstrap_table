package org.exampledriven.cxfexample.webservice;

import cn.com.ttblog.ssmbootstrap_table.model.User;

import javax.jws.WebService;

@WebService(targetNamespace = "http://ttblog.com/ssmsoap")
public interface UserServiceWebService {
	User findById(Long id);
	User updateUser(User user);
}
