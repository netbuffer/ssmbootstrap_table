package cn.com.ttblog.ssmbootstrap_table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtil {
	static int hasHead = 0;
	static int exeNum = 0;

	public static void main(String[] args) throws IOException {
		new HtmlUtil().execute();
		System.out.println("hasHead文件数量:" + hasHead);
		System.out.println("exeNum文件数量:" + exeNum);
	}

	public void execute() throws IOException {
		File files = new File("path");
		list(files);
	}

	public void list(File files) throws IOException {
		if (files.isDirectory()) {
			File[] filest = files.listFiles();
			for (File f : filest) {
				list(f);
			}
		} else {
			System.out.println("文件路劲:" + files.getAbsolutePath());
			Document doc = Jsoup.parse(files, "UTF-8");
			Element head = doc.head();
			if (head != null && head.children().size() > 0) {
				System.out.println("有head标签,head中元素数量:" + head.children().size());
				hasHead++;
				Elements metas = doc.getElementsByTag("meta");
				boolean exe=true;
				for (Element e : metas) {
					String heqs_content = e.attr("http-equiv");
					if (heqs_content != null && heqs_content.length() > 0) {
						System.out.println("http-equiv content:" + heqs_content);
						exe=false;
						break;
					}
					System.out.println("无http-equiv");
				}
				if(exe){
					//执行
					doc.head().child(0).before("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
					exeNum++;
					BufferedWriter bf=new BufferedWriter(new FileWriter(files));
					bf.write(doc.html());
					bf.flush();
					bf.close();
				}
			}
		}
	}
}
