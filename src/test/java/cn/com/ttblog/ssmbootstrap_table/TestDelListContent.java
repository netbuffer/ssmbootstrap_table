package cn.com.ttblog.ssmbootstrap_table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDelListContent {
	
	private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);

	@Test
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
}
