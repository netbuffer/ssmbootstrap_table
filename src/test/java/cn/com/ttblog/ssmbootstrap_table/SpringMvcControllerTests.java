package cn.com.ttblog.ssmbootstrap_table;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import cn.com.ttblog.ssmbootstrap_table.controller.RegisterController;
import cn.com.ttblog.ssmbootstrap_table.model.Card;

@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/spring-mvc.xml","classpath:spring/spring-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringMvcControllerTests {
	
	@Autowired
	protected WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(new RegisterController())
				.alwaysExpect(status().isMovedTemporarily()).build();
	}

	/**
	 * 测试文件上传写法
	 * @throws Exception
	 */
	@Test
	public void readString() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "orig.txt", "txt", "test info ... llalalalallsa".getBytes());
		webAppContextSetup(this.wac).build()
				.perform(fileUpload("/fileupload").file(file))
				.andExpect(model().attribute("message", "File 'orig' uploaded successfully"));
	}
	
	/**
	 * 测试重定向写法
	 * @throws Exception
	 */
	@Test
	public void uriTemplate() throws Exception {
		this.mockMvc.perform(get("/register/testredirect"))
				.andExpect(redirectedUrl("/register-success.html?id=t"));
	}


}