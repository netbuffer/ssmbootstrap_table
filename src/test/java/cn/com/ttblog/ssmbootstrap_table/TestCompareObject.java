package cn.com.ttblog.ssmbootstrap_table;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;

/**
 * http://java-object-diff.readthedocs.io/en/latest/user-guide/
 */
public class TestCompareObject {
	
	@Test
	public void test() {
		Map<String, String> working = Collections.singletonMap("item", "foo");
		Map<String, String> base = Collections.singletonMap("item", "bar");
		DiffNode diff = ObjectDifferBuilder.buildDefault().compare(working, base);
	}
}
