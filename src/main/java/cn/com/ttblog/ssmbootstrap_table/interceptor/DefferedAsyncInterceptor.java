package cn.com.ttblog.ssmbootstrap_table.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

public class DefferedAsyncInterceptor implements DeferredResultProcessingInterceptor {

	private static final Logger LOGGER=LoggerFactory.getLogger(DefferedAsyncInterceptor.class);

	@Override
	public <T> void beforeConcurrentHandling(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {
		LOGGER.info("before deffered request :{},deferredResult:{}",nativeWebRequest,deferredResult);
	}

	@Override
	public <T> void preProcess(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {
		LOGGER.info("preProcess deffered request :{},deferredResult:{}",nativeWebRequest,deferredResult);
	}

	@Override
	public <T> void postProcess(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult, Object o) throws Exception {
		LOGGER.info("postProcess deffered request :{},deferredResult:{}",nativeWebRequest,deferredResult);
	}

	@Override
	public <T> boolean handleTimeout(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {
		LOGGER.info("handleTimeout deffered request :{},deferredResult:{}",nativeWebRequest,deferredResult);
		return true;
	}

	@Override
	public <T> void afterCompletion(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {
		LOGGER.info("after deffered request :{},deferredResult:{}",nativeWebRequest,deferredResult);
	}
}