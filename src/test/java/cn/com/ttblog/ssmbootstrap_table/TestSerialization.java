package cn.com.ttblog.ssmbootstrap_table;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.com.ttblog.ssmbootstrap_table.model.Address;
import cn.com.ttblog.ssmbootstrap_table.model.Card;
import cn.com.ttblog.ssmbootstrap_table.model.User;

/**
 * 序列化 transient使用
 * http://it.deepinmind.com/examples/java/2014/09/24/transient-variables-in-java.html
 * 
 * @package cn.com.ttblog.ssmbootstrap_table
 * @author netbuffer
 */
public class TestSerialization {

	/**
	 * jdk序列化
	 * @throws ClassNotFoundException
	 */
	@Ignore
	@Test
	public void test() throws ClassNotFoundException {
		User u = new User("name", "男", 22, "13829838382", "收货地址",0, "mark",
				new ArrayList<Address>(), new Card());
		System.out.println("Before serialization:\n\t" + u.toString());
		System.out.println("Before serialization:u.getcomment:"+u.getComments());
		// Serialization of the object.
		try {
			FileOutputStream file = new FileOutputStream("User.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(u);

			System.out.printf("\nUser serialized and saved.\n\n");

			out.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Deserialization of the object.
		try {
			FileInputStream file = new FileInputStream("User.txt");
			ObjectInputStream in = new ObjectInputStream(file);
			User st = (User) in.readObject();

			System.out.println("After serialization:\n\t" + st.toString());
			System.out.println("After serialization:u.getcomment:"+st.getComments());
			in.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * json序列化
	 * @throws IOException
	 */
	@Test
	public void testSerJson() throws IOException {
		User u = new User("name", "男", 22, "13829838382", "收货地址",0, "mark",
				new ArrayList<Address>(), new Card());
		File file = new File("User.json");
		String jsonstr=JSON.toJSONString(u);
		BufferedWriter bf=new BufferedWriter(new FileWriter(file));
		bf.write(jsonstr);
		bf.flush();
		bf.close();
		System.out.println("serialization:\n\t" + jsonstr);
		BufferedReader bfr=new BufferedReader(new FileReader(file));
		String line=null;
		StringBuilder sbjsonstr=new StringBuilder();
		while ((line=bfr.readLine())!=null) {
			sbjsonstr.append(line);
		}
		System.out.println("After serialization:\n\t" + sbjsonstr);
		User u2=JSON.parseObject(sbjsonstr.toString(), User.class);
		System.out.println("After serialization user:\n\t" + u2);
	}
}
