package cn.com.ttblog.ssmbootstrap_table.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ttblog.ssmbootstrap_table.model.Permission;
import cn.com.ttblog.ssmbootstrap_table.model.Role;
import cn.com.ttblog.ssmbootstrap_table.service.IPermissionService;
import cn.com.ttblog.ssmbootstrap_table.service.IRoleService;

import com.alibaba.fastjson.JSONObject;

/**
 * shiro方法测试 
 */
@RequestMapping("/shiro")
@Controller
public class ShiroController {

	private static final Logger LOG = LoggerFactory.getLogger(ShiroController.class);
	@Autowired
	private EnterpriseCacheSessionDAO sessionDao;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping(value={"","/","/index"},method=RequestMethod.GET)
	public String getIndex(){
		LOG.debug("get to shiro-index,{}",new Object());
		return "shiro/index";
	}
	
	@RequestMapping(value="/index",method=RequestMethod.POST)
	public String postIndex(@RequestParam(value="perms",required=true) String perms,
			@RequestParam(value="roles",required=true) String roles,Model model){
		LOG.debug("post to shiro-index");
		Subject subject=SecurityUtils.getSubject();
		model.addAttribute("permskey",perms);
		model.addAttribute("permsval",subject.isPermitted(perms));
		model.addAttribute("roleskey",roles);
		model.addAttribute("rolesval",subject.hasRole(roles));
		return "shiro/index";
	}
	
	@RequestMapping(value="/isauth",method=RequestMethod.GET)
	@ResponseBody
	public Object isauth(){
		Subject subject=SecurityUtils.getSubject();
		LOG.debug("subject.isAuthenticated():{}",subject.isAuthenticated());
		return subject.isAuthenticated();
	}
	
	@RequestMapping(value="/getPrincipal",method=RequestMethod.GET)
	@ResponseBody
	public Object getPrincipal(){
		Subject subject=SecurityUtils.getSubject();
		Object p=subject.getPrincipal();
		LOG.debug("getPrincipal:{}",p);
		return p;
	}
	
	@RequestMapping(value="/hasRole/{role}",method=RequestMethod.GET)
	@ResponseBody
	public Object hasRole(@PathVariable("role") String role){
		Subject subject=SecurityUtils.getSubject();
		LOG.debug("subject.hasRole({}):{}",role,subject.hasRole(role));
		return subject.hasRole(role);
	}
	

	@RequestMapping(value="/ispermit/{resource}",method=RequestMethod.GET)
	@ResponseBody
	public Object hasPermit(@PathVariable("resource") String resource){
		Subject subject=SecurityUtils.getSubject();
		LOG.debug("subject.isPermitted({}):{}",resource,subject.isPermitted(resource));
		return subject.isPermitted(resource);
	}
	
	@RequestMapping(value="/getSession",method=RequestMethod.GET)
	@ResponseBody
	public Object getSession(){
		Subject subject=SecurityUtils.getSubject();
		LOG.debug("subject.getSession():{}",subject.getSession());
		return subject.getSession();
	}
	
	@RequestMapping(value="/getSession/{attr}",method=RequestMethod.GET)
	@ResponseBody
	public Object getSessionAttr(@PathVariable("attr") String attr){
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		LOG.debug("session.getAttribute({}):{}",attr,session.getAttribute(attr));
		return session.getAttribute(attr);
	}
	
	@RequestMapping(value = "/session/{key}")
	@ResponseBody
	public JSONObject getSessionAttr(@PathVariable("key") String key,HttpServletRequest request) {
		JSONObject j=new JSONObject();
		j.put("shiro-"+key, SecurityUtils.getSubject().getSession().getAttribute(key));
		j.put("shiro-id", SecurityUtils.getSubject().getSession().getId());
		j.put("httpsession-"+key, request.getSession().getAttribute(key));
		j.put("httpsession-id", request.getSession().getId());
		//request拿到的session和shiro拿到的是一个，request被shiro处理过了
		return j;
	}
	
	@RequestMapping(value = "/session/{key}/{val}")
	@ResponseBody
	public boolean getSessionAttr(@PathVariable("key") String key,@PathVariable("val")String val,HttpServletRequest request) {
		request.getSession().setAttribute(key, val);
		return true;
	}
	
	/**
	 * 查询活跃的session会话
	 */
	@RequestMapping(value="/session/all",method=RequestMethod.GET)
	@ResponseBody
	public Object getActiveSessions(){
		return sessionDao.getActiveSessions();
	}
	
	@RequiresRoles(value={"admin"})
	@RequestMapping(value="/role/list",method=RequestMethod.GET)
	public String listRoles(Model m){
		LOG.debug("查询角色列表");
		m.addAttribute("roles", roleService.listRoles());
		return "shiro/roles";
	}
	
	@RequiresRoles(value={"admin"})
	@RequestMapping(value="/role/add",method=RequestMethod.GET)
	public String addRole(){
		LOG.debug("添加角色");
		return "shiro/addrole";
	}
	
	@RequiresRoles(value={"admin"})
	@RequestMapping(value="/role/add",method=RequestMethod.POST)
	public String addRole(Role r){
		LOG.debug("保存角色:{}",r);
		roleService.addRole(r);
		return "redirect:/shiro/role/list";
	}
	
	@RequiresRoles(value={"admin"})
	@RequestMapping(value="/permission/list/{roleId}",method=RequestMethod.GET)
	public String listPermissions(Model m,@PathVariable("roleId") Integer roleId){
		if(roleId!=null&&roleId>0){
			LOG.debug("查询角色:{}对应的权限列表",roleId);
			Role role=roleService.findRoleById(roleId);
			if(role==null||role.getName()==null||role.getName().length()==0){
				throw new IllegalArgumentException("未找到对应的角色");
			}
			m.addAttribute("role", role);
			m.addAttribute("permissions", permissionService.listPermissionsByRoleId(roleId));
		}else{
			LOG.debug("查询权限列表");
			m.addAttribute("permissions", permissionService.listPermissions());
		}
		return "shiro/permissions";
	}
	
	@RequiresRoles(value={"admin"})
	@RequestMapping(value="/permission/list",method=RequestMethod.GET)
	public String listPermissions(Model m){
		LOG.debug("查询权限列表");
		m.addAttribute("permissions", permissionService.listPermissions());
		return "shiro/permissions";
	}
	
	@RequiresRoles(value={"admin"})
	@RequestMapping(value="/permission/add",method=RequestMethod.GET)
	public String addPermissions(){
		LOG.debug("添加权限");
		return "shiro/addpermissions";
	}
	
	@RequiresRoles(value={"admin"})
	@RequestMapping(value="/permission/add",method=RequestMethod.POST)
	public String addPermissions(Permission p){
		LOG.debug("保存权限:{}",p);
		permissionService.addPermission(p);
		return "redirect:/shiro/permission/list";
	}
	
}