package org.lwj.MySpringBoot.config.swagger;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo());
	}
	
	/**
	 * 配置页面信息
	 * @return
	 */
	private ApiInfo getApiInfo() {
		//作者信息
		Contact contact = new Contact("吕文进", "", "972950811@qq.com");
		
		return new ApiInfo(
				"lwj的API文档", 
				"历尽千帆，归来仍是少年", 
				"v1.0", 
				"lwj",  //网站url
				contact, 
				"Apache 2.0", 
				"http://www.apache.org/licenses/LICENSE-2.0", 
				new ArrayList<VendorExtension>()
				);
	}
	
	
	
}
