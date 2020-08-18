package cn.netbuffer.ssmbootstrap_table;

import org.junit.Test;

import cn.netbuffer.ssmbootstrap_table.enumeration.Config;

public class TestEnum {

	public enum Color {
		RED, GREEN, BLANK, YELLOW
	}
	
	public enum Color2 {
		RED("1"), GREEN("2"), BLANK("3"), YELLOW("4");
		private String value;
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		Color2(String value){
			this.value=value;
		}
	}
	
	@Test
	public void test() {
		System.out.println(Color.RED);
		System.out.println(Color.RED.equals("0"));
		//拿到枚举序列值ordinal
		System.out.println(Color.RED.equals(0));
		System.out.println(Color.RED.ordinal()==0);
		System.out.println("==========================");
		System.out.println(Color2.RED);
		//直接equals是不会相等的
		System.out.println(Color2.RED.equals("1"));
		System.out.println(Color2.RED.getValue().equals("1"));
		System.out.println("==========================");
		System.out.println("Config.KEY.getValue:"+Config.KEY.getValue());
	}
}
