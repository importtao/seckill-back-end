package me.importtao.seckillbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("me.importtao.seckillbackend.dao")
public class SeckillBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeckillBackEndApplication.class, args);
	}
}
