package cn.com.ttblog.ssmbootstrap_table.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.common.base.Predicate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration 在线文档:http://springfox.github.io/springfox/docs/current/
//前端访问http://localhost:8080/ssmbootstrap_table/swagger-ui.html
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { "cn.com.ttblog.ssmbootstrap_table" })
public class SwaggerConfig {
	/**

	 * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc

	 * framework - allowing for multiple swagger groups i.e. same code base

	 * multiple swagger resource listings.

	 *

	 * @return SwaggerSpringMvcPlugin

	 */
	@Bean
	public Docket customImplementation() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo())
				.select().paths(paths()).build().pathMapping("/");
	}

	private Predicate<String> paths() {
		return PathSelectors.any();
	}

	/**

	 * A method that returns the API Info

	 * 

	 * @return ApiInfo The Information including description

	 */
	public ApiInfo getApiInfo() {
		return new ApiInfo("ssmbootstrap_table-api",
				"api接口列表", "版本号",
				"https://github.com/netbuffer/", new Contact("netbuffer", "https://github.com/netbuffer/", "javawiki@163.com"), null, null);
	}
}

