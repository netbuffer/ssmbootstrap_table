package cn.com.ttblog.ssmbootstrap_table;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TestNGLearn1 {
	private static final Logger log=LoggerFactory.getLogger(TestNGLearn1.class);
    @BeforeClass
    public void beforeClass() {
        System.out.println("this is before class");
    }
    
    @Test
    public void testMethodsOne() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method One. Thread id is: " + id);
    }
 
    @Test
    public void testMethodsTwo() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method Two. Thread id is: " + id);
    }
    @Test(threadPoolSize = 3, invocationCount = 6, timeOut = 1000)
    public void TestNgLearn() {
    	log.debug("this is TestNG test case {}-{}",this,Thread.currentThread().getName());
    }

    @AfterClass
    public void afterClass() {
        System.out.println("this is after class");
    }
}