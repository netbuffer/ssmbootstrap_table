package cn.com.ttblog.ssmbootstrap_table.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.common.base.Predicate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
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
		return new ApiInfo("My REST API",
				"This REST API allows the user to manage domain objects", "1e",
				"", "support@mysite.com", null, null);
	}
}