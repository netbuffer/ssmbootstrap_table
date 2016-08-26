package cn.com.ttblog.ssmbootstrap_table;

import org.junit.Test;

public class TestAssert {
	//run config中启用断言 加上-ea参数 http://bbs.csdn.net/topics/390459827
	@Test
	public void testAdd() {
		boolean isEnable = false;
		assert isEnable = true;
		if (isEnable == false) {
			throw new RuntimeException("Assertion shoule be enable!");
		}
		String name = null;
		assert (name != null) : "变量name为空null";
		System.out.println(name);
	}
}
