package cn.com.ttblog.ssmbootstrap_table;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class TestHash {
	
	@Test
	public void testMd5Hash(){
		String str="hello";
		String salt="a";
		System.out.println("md51:"+new Md5Hash(str).toString());
		System.out.println("md52:"+new Md5Hash(str, salt, 2).toString());
		String simpleHash1 = new SimpleHash("SHA-1", str).toString();
		String simpleHash2 = new SimpleHash("SHA-1", str, salt).toString();
		System.out.println("simpleHash1:"+simpleHash1);
		System.out.println("simpleHash2:"+simpleHash2);
	}
}
 