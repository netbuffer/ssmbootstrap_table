package cn.com.ttblog.ssmbootstrap_table;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * test commons-email
 */
public class TestEmail {
	
	private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);
	private static final String user="user";
	//使用授权码
	private static final String pwd="pwd";
	@Test
//	http://commons.apache.org/proper/commons-email/userguide.html
//	http://help.mail.163.com/faqDetail.do?code=d7a5dc8471cd0c0e8b4b8f4f8e49998b374173cfe9171305fa1ce630d7f67ac22dc0e9af8168582a
	public void testSimpleTxt() throws EmailException {
		Email email = new SimpleEmail();
		email.setDebug(true);
		email.setHostName("smtp.yeah.net");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator(user,pwd ));
		email.setSSLOnConnect(false);
		email.setFrom(user);
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo(user);
		email.send();
	}
}
