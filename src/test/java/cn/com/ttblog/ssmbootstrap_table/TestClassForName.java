package cn.com.ttblog.ssmbootstrap_table;

import java.util.Scanner;

import org.junit.Test;

public class TestClassForName {
	
	@Test
	public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class t =Class.forName("java.lang.Thread");
		System.out.println(t);
		Scanner sc=new Scanner(System.in);
		String className=sc.nextLine();
		Class.forName(className).newInstance().getClass();
		System.out.println("className:"+className);
	}
}
 