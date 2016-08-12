package cn.com.ttblog.ssmbootstrap_table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;

public class TestAntPath {
	AntPathMatcher ap = new AntPathMatcher();

	@Test
	public void String() {
		System.out.println("NumberUtils.isNumber('aaa'):"+NumberUtils.isNumber("aaa")+"  NumberUtils.isNumber('24234234142314'):"+NumberUtils.isNumber("24234234142314"));
		System.out.println("ap.isPattern('/static/**'):" + ap.isPattern("/static/**"));
		System.out.println("ap.isPattern('/static/*'):" + ap.isPattern("/static/*"));
		System.out.println("ap.isPattern('/static*'):" + ap.isPattern("/static*"));
		System.out.println("ap.isPattern('/static?'):" + ap.isPattern("/static?"));
		System.out.println("ap.isPattern('/static'):" + ap.isPattern("/static"));
		System.out.println("/*/index.html:"+ap.match("/*/index.html", "/ssmbootstrap_table/a/index.html"));
		System.out.println("/*/index.html:"+ap.match("/*/index.html", "/ssmbootstrap_table/index.html"));
		System.out.println("/*/index.html*:"+ap.match("/*/index.html*", "/ssmbootstrap_table/index.html?id=sss"));
		System.out.println("/*/image/**:"+ap.match("/*/image/**", "/ssmbootstrap_table/image/backimg.jpg"));
	}

	@Test
	public void match() {
		// ...
		assertFalse(ap.match("/x/x/**/bla", "/x/x/x/"));
		assertTrue(ap.match("/static/**", "/static/a.js"));
		assertTrue(ap.match("/static/**", "/static/a.css"));
		assertTrue(ap.match("/static/**", "/static/a/a/b/a.css"));
		// ...
	}

	@Test
	public void withMatchStart() {
		// ...
		assertTrue(ap.matchStart("/x/x/**/bla", "/x/x/x/"));
		// ...
	}

	@Test
	public void extractPathWithinPattern() throws Exception {
		// ...
		assertEquals("", ap.extractPathWithinPattern("/docs/commit.html", "/docs/commit.html"));
		assertEquals("cvs/commit", ap.extractPathWithinPattern("/docs/*", "/docs/cvs/commit"));
		assertEquals("docs/cvs/commit", ap.extractPathWithinPattern("/d?cs/*", "/docs/cvs/commit"));
		// ...
	}

	@Test
	public void extractUriTemplateVariables() throws Exception {
		Map<String, String> result = ap.extractUriTemplateVariables("/hotels/{hotel}", "/hotels/1");
		assertEquals(Collections.singletonMap("hotel", "1"), result);
		// ...
		result = ap.extractUriTemplateVariables("/{page}.*", "/42.html");
		assertEquals(Collections.singletonMap("page", "42"), result);
		// ...
	}

	/**
	 * SPR-7787
	 */
	@Test
	public void extractUriTemplateVarsRegexQualifiers() {
		Map<String, String> result = ap.extractUriTemplateVariables(
				"{symbolicName:[\\p{L}\\.]+}-sources-{version:[\\p{N}\\.]+}.jar", "com.example-sources-1.0.0.jar");
		assertEquals("com.example", result.get("symbolicName"));
		assertEquals("1.0.0", result.get("version"));
		// ...
	}

	@Test
	public void combine() {
		// ...
		assertEquals("/hotels", ap.combine("/hotels", null));
		assertEquals("/hotels/booking", ap.combine("/hotels/*", "/booking"));
		assertEquals("/hotels/**/booking", ap.combine("/hotels/**", "booking"));
		assertEquals("/hotels/**/booking", ap.combine("/hotels/**", "/booking"));
		assertEquals("/hotels/booking", ap.combine("/hotels", "/booking"));
		assertEquals("/hotels/{hotel}", ap.combine("/hotels/*", "{hotel}"));
		assertEquals("/hotels/**/{hotel}", ap.combine("/hotels/**", "{hotel}"));
		assertEquals("/hotels/*/booking/{booking}", ap.combine("/hotels/*/booking", "{booking}"));
	}
}
