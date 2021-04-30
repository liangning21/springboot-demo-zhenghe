package cn.lihuan.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@MapperScan("cn.lihuan.springboot.controller.mapper")
public class SpringbootDuoshujuyuanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDuoshujuyuanApplication.class, args);
	}

}
