package cn.gzticc.bgmanagertemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//扫描 mybatis dao 包路径
@MapperScan(basePackages = {"cn.gzticc.bgmanagertemplate.*.dao"})
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"cn.gzticc"})
@EnableTransactionManagement
//开启定时任务
//@EnableScheduling
//开启异步调用方法
//@EnableAsync
//@Configuration
public class BgManagerTemplateApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BgManagerTemplateApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BgManagerTemplateApplication.class, args);
	}
}
