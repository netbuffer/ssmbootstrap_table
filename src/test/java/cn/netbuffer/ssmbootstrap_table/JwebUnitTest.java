package cn.netbuffer.ssmbootstrap_table;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertLinkPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTestingEngineKey;

import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.util.TestingEngineRegistry;
/**
 * https://jwebunit.github.io/jwebunit/quickstart.html
 * https://jwebunit.github.io/jwebunit/
 */
public class JwebUnitTest {
	@Before
	public void prepare() {
		setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT); // use
		// setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_WEBDRIVER);
		// // use webdriver
		setBaseUrl("http://www.baidu.com");
	}

	@Test
	public void testIndexLogin() {
		beginAt("index.html"); // start at index.html
		assertTitleEquals("Home"); // the home page should be titled "Home"
		assertLinkPresent("Login"); // there should be a "Login" link
		clickLink("Login"); // click the link
		assertTitleEquals("Login"); // we should now be on the login page
	}
}