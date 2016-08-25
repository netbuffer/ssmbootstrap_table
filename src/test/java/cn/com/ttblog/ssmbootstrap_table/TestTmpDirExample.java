package cn.com.ttblog.ssmbootstrap_table;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 临时文件使用
 * http://it.deepinmind.com/examples/io/2014/09/21/java-io-tmpdir-example.html
 * 
 * @package cn.com.ttblog.ssmbootstrap_table
 * @author netbuffer
 */
public class TestTmpDirExample {
	
	Logger log=Logger.getLogger("TestTmpDirExample");
	
	@Test
	@Ignore
	public void main() {
		String tmpdir = System.getProperty("java.io.tmpdir");
		System.out.println("The default value of the java.io.tmpdir system property is: \"" + tmpdir + "\"\n");

		// Specify some temporary files.
		String prefix = "file";
		String suffix = ".txt";
		File tempFile = null;
		File tempFile2 = null;
		File tempFile3 = null;
		File directory = new File("D:/");
		try {
			// Create two temporary files.
			tempFile = File.createTempFile(prefix, suffix);
			tempFile2 = File.createTempFile(prefix, null);
			tempFile3 = File.createTempFile(prefix, suffix, directory);
		} catch (IOException ex) {
			System.err.println("An IOException was caught: " + ex.getMessage());
			ex.printStackTrace();
		}

		// Printing the name of every file.
		System.out.println(
				"A new file called \"" + tempFile.getName() + "\" was created in the directory: \"" + tmpdir + "\"");
		System.out.println(
				"A new file called \"" + tempFile2.getName() + "\" was created in the directory: \"" + tmpdir + "\"\n");
		System.out.println("A new file called \"" + tempFile3.getName() + "\" was created in the directory: \""
				+ directory.getName() + "\"\n");
		// Printing the parent directories of every file.
		System.out.println(
				"The parent directory of the file \"" + tempFile.getName() + "\" is: \"" + tempFile.getParent() + "\"");
		System.out.println("The parent directory of the file \"" + tempFile2.getName() + "\" is: \""
				+ tempFile2.getParent() + "\"");
		System.out.println("The parent directory of the file \"" + tempFile3.getName() + "\" is: \""
				+ tempFile3.getParent() + "\"\n");
		// Delete the temporary files.
		if (tempFile.delete())
			System.out.println("Successfully deleted the file with name: \"" + tempFile.getName() + "\"");
		else
			System.out.println("Couldn't delete the file with name: \"" + tmpdir + "\"");
		if (tempFile2.delete())
			System.out.println("Successfully deleted the file with name: \"" + tempFile2.getName() + "\"");
		else
			System.out.println("Couldn't delete the file with name: \"" + tmpdir + "\"");
		if (tempFile3.delete())
			System.out.println("Successfully deleted the file with name: \"" + tempFile3.getName() + "\"");
		else
			System.out.println("Couldn't delete the file with name: \"" + tmpdir + "\"");
	}
	
	@Test
	public void test() throws IOException{
		log.log(Level.INFO,"写文件测试");
		for(int i=0;i<10;i++){
			log.log(Level.WARNING,"循环"+i);
			FileUtils.writeStringToFile(new File("d:/text.txt"),"第"+i+"次写入:"+ RandomStringUtils.randomAscii(10)+"\r\n", true);
		}
	}
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("输入:");
		String str=scan.nextLine();
		System.out.println("输出:"+str);
	}
}
