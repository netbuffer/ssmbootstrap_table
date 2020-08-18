package cn.netbuffer.ssmbootstrap_table;

import java.math.BigInteger;
import java.text.DecimalFormat;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Ignore;
import org.junit.Test;

public class TestInt {
	
	/**
	 * 整形溢出问题
	 */
	@Test
	@Ignore
	public void testint() {
		int a = Integer.MAX_VALUE;
		System.out.println(a);
		int b = a + 1;
		System.out.println(b);
	}
	
	@Test
	@Ignore
	public void testBigInteger() {
		BigInteger a=BigInteger.valueOf(Integer.MAX_VALUE);
		System.out.println(a);
		BigInteger b = a.add(new BigInteger("1"));
		System.out.println(b);
	}
	
	@Test
	public void testRandom(){
		for (int i = 0; i <100; i++) {
			System.out.println("RandomUtils.nextInt(2, 6):" + RandomUtils.nextInt(2, 6));
		}
	}
	
	@Test
	public void int2float(){
//		float i=233.4f;
		int i=2334;
		System.out.println(i/100);
		System.out.println((float)i/100);
		//构造方法的字符格式这里如果小数不足2位,会以0补足.
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		System.out.println(decimalFormat.format((float)i/100));
	}
	
	@Test
	public void testCompare(){
		Integer i=null;
		//这样会引发空指针，比较需要判空条件
		System.out.println(i==1);
	}
}