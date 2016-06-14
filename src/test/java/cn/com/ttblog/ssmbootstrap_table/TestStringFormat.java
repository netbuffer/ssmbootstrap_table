package cn.com.ttblog.ssmbootstrap_table;

import org.junit.Test;

/**
 * http://blog.csdn.net/lonely_fireworks/article/details/7962171
 * 
 * @author netbuffer
 *
 */
public class TestStringFormat {
	@Test
	public void test() {
		System.out.println(String.format("Hi,%s", "王力"));
		System.out.println(String.format("Hi,%s:%s.%s", "王南", "王力", "王张"));
		System.out.printf("字母a的大写是：%c %n", 'A');//字符类型
		System.out.println(String.format("字母a的大写是：%c", 'A'));
		System.out.printf("3>7的结果是：%b %n", 3 > 7);//布尔类型
		System.out.printf("100的一半是：%d %n", 100 / 2);//整数类型（十进制）
		System.out.printf("100的16进制数是：%x %n", 100);//整数类型（十六进制）
		System.out.printf("100的8进制数是：%o %n", 100);//整数类型（八进制）
		System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
		System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
		System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
		System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
		System.out.printf("上面的折扣是%d%% %n", 85);
		System.out.printf("字母A的散列码是：%h %n", 'A');
	}
}
