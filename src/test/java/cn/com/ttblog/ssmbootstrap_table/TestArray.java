package cn.com.ttblog.ssmbootstrap_table;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Test;

public class TestArray {
	
	@Test
	public void testArray(){
		String[] sarr = {"a","b","c"};
		Array.set(sarr, 0, "test");
		System.out.println(Arrays.deepToString(sarr));
		Integer[] iarr=(Integer[]) Array.newInstance(Integer.class, 3);
		Array.set(iarr, 0, 1);
		Array.set(iarr, 1, 2);
		System.out.println("iarr:"+Arrays.deepToString(iarr));
	}
}
 