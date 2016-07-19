package cn.com.ttblog.ssmbootstrap_table.interceptor;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

public class AsyncInterceptor implements CallableProcessingInterceptor {
	private static final Logger log=LoggerFactory.getLogger(AsyncInterceptor.class);
	@Override
	public <T> void beforeConcurrentHandling(NativeWebRequest request,
			Callable<T> task) throws Exception {
		log.info("beforeConcurrentHandling:{}-{}",request,task);
	}

	@Override
	public <T> void preProcess(NativeWebRequest request, Callable<T> task)
			throws Exception {
		log.info("preProcess:{}-{}",request,task);
	}

	@Override
	public <T> void postProcess(NativeWebRequest request, Callable<T> task,
			Object concurrentResult) throws Exception {
		log.info("postProcess:{}-{}-{}",request,task,concurrentResult);
	}

	@Override
	public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task)
			throws Exception {
		log.info("handleTimeout:{}-{}",request,task);
		return "timeout";
	}

	@Override
	public <T> void afterCompletion(NativeWebRequest request, Callable<T> task)
			throws Exception {
		log.info("afterCompletion:{}-{}",request,task);
	}

}