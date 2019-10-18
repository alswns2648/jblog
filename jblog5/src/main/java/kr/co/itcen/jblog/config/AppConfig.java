package kr.co.itcen.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import kr.co.itcen.config.app.DBConfig;
import kr.co.itcen.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy //applicationContext의 <aop:aspectj-autoproxy /> 대체

//applicationContext의 <context:component-scan/>의 패키지 대체 
@ComponentScan({"kr.co.itcen.jblog.service", "kr.co.itcen.jblog.repository", "kr.co.itcen.jblog.aspect"})
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {
	
}
