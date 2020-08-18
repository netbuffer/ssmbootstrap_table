//package cn.netbuffer.ssmbootstrap_table.util;
//
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.AsyncConfigurer;
//import org.springframework.scheduling.annotation.EnableAsync;
//import java.lang.reflect.Method;
//import java.util.concurrent.Executor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadFactory;
//
///**
// * 启用异步方法
// * 发现在xml中配置<task:annotation-driven></task:annotation-driven>不生效,
// * 基于java config是生效的
// * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/annotation/EnableAsync.html
// * http://docs.spring.io/spring/docs/4.2.9.RELEASE/spring-framework-reference/htmlsingle/#scheduling-annotation-support-async
// */
//@Configuration
//@EnableAsync
//public class Config implements AsyncConfigurer {
//
//    /**
//     * 自定义实现Executor
//     * @return
//     */
//    @Override
//    public Executor getAsyncExecutor() {
//        ExecutorService executorService=Executors.newSingleThreadExecutor(new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r,"task");
//            }
//        });
//        return executorService;
//    }
//
//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return new AsyncUncaughtExceptionHandler() {
//            @Override
//            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
//                System.out.printf("throwable:%s,method:%s,objects:%s",throwable,method,objects);
//            }
//        };
//    }
//}
