package kr.co.itcen.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/*
 * 1.View Resolver
 * 2.Default Servlet Handler
 * 3.Message Converter
 * 
 */

@Configuration
@EnableWebMvc
public class MVCConfig extends WebMvcConfigurerAdapter {
	
	
	
	//1.View Resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);
		
		return viewResolver;
	}
	
	//2.Default Servlet Handler
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	//3.Message Converter
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackSon2HttpMessageConverter() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
												.indentOutput(true)
												.dateFormat(new SimpleDateFormat("yyyy-mm-dd"))
												.modulesToInstall(new ParameterNamesModule());
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
		
		converter.setSupportedMediaTypes(
				Arrays.asList(
						new MediaType("application", "json", Charset.forName("UTF-8"))
						)
				);
		
		return converter;
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		
		converter.setSupportedMediaTypes(
				Arrays.asList(
						new MediaType("application", "json", Charset.forName("UTF-8"))
						)
				);
		
		return converter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackSon2HttpMessageConverter());
		converters.add(stringHttpMessageConverter());
		super.configureMessageConverters(converters);
	}	
}
