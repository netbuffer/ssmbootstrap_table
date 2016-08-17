package cn.com.ttblog.ssmbootstrap_table;

import java.math.BigInteger;
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
}