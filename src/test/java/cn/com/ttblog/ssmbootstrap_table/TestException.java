package cn.com.ttblog.ssmbootstrap_table;

import org.junit.Test;

public class TestException {
	
	@Test
	public void testE(){
//		System.out.println("执行结果1:"+get());
		System.out.println("执行结果2:"+get2());
	}
	private String get(){
		System.out.println("------------------get1");
		String msg="fail";
		try{
			int i=1/0;
		}catch(Exception e){
//			e.printStackTrace();
			throw new RuntimeException("^_^-"+e.getMessage()+",msg:"+msg);//throw 异常后代码不往下执行
		}
		msg="success";
		return msg;
	}
	
	private String get2(){
		System.out.println("------------------get2");
		String msg="fail";
		try{
			int i=1/0;
			return msg;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("^-^:"+e.getMessage()+",msg:"+msg);
		}finally {
			msg="error";
			System.out.println("finally-msg:"+msg);
		}
	}
}
 