package cn.com.ttblog.ssmbootstrap_table.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import cn.com.ttblog.ssmbootstrap_table.annotation.Token;

/**
 * token form
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);
	public static final String TOKENFLAGNAME = "tokenflag";
	public static final String TOKEN = "token";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation = method.getAnnotation(Token.class);
			if (annotation != null) {
				boolean needSaveSession = annotation.save();
				if (needSaveSession) {
					String token = UUID.randomUUID().toString();
					String tokenmd5=Md5Crypt.md5Crypt(token.getBytes());
					log.warn("生成表单token:{}", token);
					request.getSession(false).setAttribute(tokenmd5, token);
					request.setAttribute(TOKENFLAGNAME, tokenmd5);
					request.setAttribute(TOKEN, token);
				}
				boolean needRemoveSession = annotation.remove();
				if (needRemoveSession) {
					if (isRepeatSubmit(request)) {
						log.debug("重复提交表单提示");
						request.getRequestDispatcher("/user/error.jsp").forward(request, response);
						return false;
					}
					String key=request.getParameter(TOKENFLAGNAME).toString();
					request.getSession(false).removeAttribute(key);
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String tokenname = request.getParameter(TOKENFLAGNAME);
		String serverToken = (String) request.getSession(false).getAttribute(tokenname);
		if (serverToken == null) {
			log.warn("发现重复表单提交请求serverToken==null");
			return true;
		}
		String clinetToken = request.getParameter(tokenname);
		if (clinetToken == null) {
			log.warn("发现非法请求clinetToken==null");
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			log.warn("发现非法表单提交请求serverToken!=clinetToken");
			return true;
		}
		return false;
	}
}
