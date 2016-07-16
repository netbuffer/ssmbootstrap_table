package cn.com.ttblog.ssmbootstrap_table.directive;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

@DefaultKey("util")
@ValidScope(Scope.APPLICATION)
public class UtilTag {
	
	public String test(String str){
		System.out.println("UtilTag:"+str);
		return "hello:"+str;
	}
	
}