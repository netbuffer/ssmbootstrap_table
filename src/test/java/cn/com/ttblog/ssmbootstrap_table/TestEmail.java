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

	@Test
//	http://commons.apache.org/proper/commons-email/userguide.html
	public void testSimpleTxt() throws EmailException {
		Email email = new SimpleEmail();
		email.setDebug(true);
		email.setHostName("host");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator("test", "test"));
		email.setSSLOnConnect(true);
		email.setFrom("test");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("test");
		email.send();
	}
}
