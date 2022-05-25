/**
 * Created On : 17 Aug 2017
 */
package com.lk.project.x;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author virtualpathum
 * The Class SpringBootStudentApp.
 */
//@Import({SysConfig.class})
//@Configuration
//@ComponentScan(basePackages = {
//		"com.lk.project.x.mapper",
//		"com.lk.project.x.service" })

//@EnableJpaRepositories(basePackages = {"com.lk.project.x.repo" })
//@PropertySource({ "classpath:application.properties" })
//@EnableTransactionManagement
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.lk.project")
public class SpringBootMain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.lk.project.x.controller") )
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/auth/").allowedOrigins("*");
			}
		};
	}
}
