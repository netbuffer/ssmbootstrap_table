package cn.com.ttblog.ssmbootstrap_table.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
/**
 * 如果我们需要在Spring容器完成Bean的实例化、配置和其他的初始化前后添加一些自己的逻辑处理需要用到
 * @package cn.com.ttblog.ssmbootstrap_table.util
 * @Description: TODO() 
 */
public class TestBeanPostProcessor implements BeanPostProcessor {
	private static final Logger log = LoggerFactory
			.getLogger(TestBeanPostProcessor.class);

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		log.info("postProcess-[After]-init:{}",beanName);
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		log.info("postProcess-[Before]-init:{}",beanName);
		List<Class<?>> clazzes = getAllClasses(bean);

		for (Class<?> clazz : clazzes) {
			initializeLog(bean, clazz);
		}

		return bean;
	}

	/**
	 * 取得指定bean的class以及所有父类的列表, 该列表排列顺序为从父类到当前类
	 * 
	 * @param bean
	 * @return
	 */
	private List<Class<?>> getAllClasses(Object bean) {
//		log.info("getAllClasses");
		Class<? extends Object> clazz = bean.getClass();
		List<Class<?>> clazzes = new ArrayList<Class<?>>();
		while (clazz != null) {
			clazzes.add(clazz);
			clazz = clazz.getSuperclass();
		}
		Collections.reverse(clazzes);
		return clazzes;
	}

	/**
	 * 对logger变量进行初始化
	 * 
	 * @param bean
	 * @param clazz
	 */
	private void initializeLog(Object bean, Class<? extends Object> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		// for (Field field : fields) {
		// if (field.getAnnotation(Logger.class) == null) {
		// continue;
		// }
		//
		// if (!field.getType().isAssignableFrom(Log.class)) {
		// continue;
		// }
		//
		// field.setAccessible(true);
		// try {
		// field.set(bean, LogFactory.getLog(clazz));
		// } catch (Exception e) {
		// throw new BeanInitializationException(String.format(
		// "初始化logger失败!bean=%s;field=%s", bean, field));
		// }
		//
		// }
	}

}