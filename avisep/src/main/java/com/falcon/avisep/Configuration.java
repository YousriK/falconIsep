package com.falcon.avisep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@SpringBootApplication
public class Configuration extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(Configuration.class, args);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new Interceptor());
	}
	//	@Bean
	//	public DataSource dataSource(){
	//	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	//	    dataSource.setDriverClassName("org.postgresql.Driver");
	//	    dataSource.setUrl("jdbc:postgresql://localhost:5432/avisepdb");
	//	    dataSource.setUsername("postgres");
	//	    dataSource.setPassword("falconteama2");
	//	    return dataSource;
	//	}
	@Bean
	public ViewResolver viewResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setPrefix("views/");
		templateResolver.setSuffix(".html");

		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(engine);
		return viewResolver;
	}

}
