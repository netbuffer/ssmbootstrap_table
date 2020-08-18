package cn.netbuffer.ssmbootstrap_table;

import cn.netbuffer.ssmbootstrap_table.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TestDelListContent {

	private static Logger logger = LoggerFactory.getLogger(TestDelListContent.class);

	@Test
	@Ignore
	public void testSystemProperties() {
		List<String> arr=new ArrayList<String>();
		arr.add("1");//删除第0个
		arr.add("2");
		arr.add("3");
		arr.add("4");//删除第3个
		arr.add("5");
		arr.add("6");
		Iterator<String> arri=arr.iterator();
		int count=0;
		Set<Integer> delids=new HashSet<Integer>();
		delids.add(0);
		delids.add(3);
		while(arri.hasNext()){
			arri.next();//必须先调用next方法再调用remove
//			System.out.println("元素:"+arri.next());
//			System.out.println("count:"+count);
			for(int i:delids){
				if(count==i){
					arri.remove();
				}
			}
			count++;
		}
		System.out.println(arr.toString());
	}

	@Test
	public void testAddToIndex() {
		List<String> arr=new ArrayList<String>();
		arr.add("1");
		arr.add("2");
		arr.add("3");
		arr.add("4");
		arr.add("5");
		arr.add("6");

		System.out.println("arr->tostring:"+arr.toString());
		System.out.println("arr->deeptostring:"+Arrays.deepToString(arr.toArray()));

		List<User> listuser=new ArrayList<User>();

		listuser.add(new User("1", "男", 22, "", "",23, "", null,null));
		listuser.add(new User("2", "男", 22, "", "",23, "", null,null));
		listuser.add(new User("3", "男", 22, "", "",23, "", null,null));

		System.out.println("listuser->tostring:"+listuser.toString());
		System.out.println("listuser->deeptostring:"+Arrays.deepToString(listuser.toArray()));

		List<String> arr2=new ArrayList<String>();
		arr2.add("7");
		arr2.add("8");
		//插入到第3个索引位上，从0开始
		System.out.println("add?:"+arr.addAll(3,arr2));
		System.out.println(arr.toString());
		arr.add(5, "test");
		System.out.println(arr.toString());
	}
}
