package cn.netbuffer.ssmbootstrap_table;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Ignore;
import org.junit.Test;

import cn.netbuffer.ssmbootstrap_table.model.ExtendUser;
import cn.netbuffer.ssmbootstrap_table.model.User;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;

/**
 * http://java-object-diff.readthedocs.io/en/latest/user-guide/
 */
public class TestCompareObject {
	
	@Test
	@Ignore
	public void test() {
		Map<String, String> working = Collections.singletonMap("item", "foo");
		Map<String, String> base = Collections.singletonMap("item", "bar");
		DiffNode diff = ObjectDifferBuilder.buildDefault().compare(working, base);
	}
	
	@Test
	public void testCopyProperties(){
		User u=new User("m", "男", 22, "", "s", (int)(System.currentTimeMillis()/1000), "comments",null, null);
		ExtendUser eu=new ExtendUser();
		try {
			long c=System.currentTimeMillis();
			BeanUtils.copyProperties(eu,u);
			long e=System.currentTimeMillis();
			System.out.println("1execute:"+(e-c)+"  ExtendUser1:"+eu.toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		long c=System.currentTimeMillis();
		org.springframework.beans.BeanUtils.copyProperties(u, eu);
		long e=System.currentTimeMillis();
		System.out.println("2execute:"+(e-c)+"  ExtendUser2:"+eu.toString());
	}
}
