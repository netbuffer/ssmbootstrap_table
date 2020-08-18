package cn.netbuffer.ssmbootstrap_table;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;

public class TestJvmArguments {
	@Test
	@Ignore
	public void testSystemProperties() {
		Properties p = System.getProperties();
		Iterator<Entry<Object, Object>> it = p.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> item = it.next();
			System.out.print(item.getKey() + "===");
			System.out.println(item.getValue());
		}
	}
	/**
	 * 发现在eclipse.ini中配置了jvm内存参数,又通过window->preferences->installed jres来设置jvm参数，
	 * eclipse.ini的配置就不会生效，而是以instlled jres配置为准
	 */
	@Test
	public void testMemparam(){
		DecimalFormat df = new DecimalFormat("0.00") ;
        //Display the total amount of memory in the Java virtual machine.
        long totalMem = Runtime.getRuntime().totalMemory()/1024/1024;
        System.out.println(df.format(totalMem) + " MB");
        //Display the maximum amount of memory that the Java virtual machine will attempt to use.
        long maxMem = Runtime.getRuntime().maxMemory()/1024/1024;
        System.out.println(df.format(maxMem) + " MB");
        //Display the amount of free memory in the Java Virtual Machine.
        long freeMem = Runtime.getRuntime().freeMemory()/1024/1024;
        System.out.println(df.format(freeMem) + " MB");
	}
}
