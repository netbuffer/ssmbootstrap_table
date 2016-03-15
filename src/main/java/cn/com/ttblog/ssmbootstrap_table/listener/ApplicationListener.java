package cn.com.ttblog.ssmbootstrap_table.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

/**
 * 设置webroot路径
 * @author netbuffer
 */
public class ApplicationListener extends ContextLoaderListener {    
    
    public void contextDestroyed(ServletContextEvent sce) {    
    }    
    
    public void contextInitialized(ServletContextEvent sce) {    
        String webAppRootKey = sce.getServletContext().getRealPath("/"); 
        String param=sce.getServletContext().getInitParameter("webAppRootKey");
        System.setProperty(param , webAppRootKey);    
    }    
    
} 