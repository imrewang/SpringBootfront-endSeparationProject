package cn.lanqiao.springboot3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 13
 * @qq交流群 784785001
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */
@SpringBootApplication
//用于表示SpringBoot应用中的启动类，相当于@EnableAutoConfiguration、@EnableAutoConfiguration和@ComponentScan三个注解的结合体。
@MapperScan("cn.lanqiao.springboot3.dao")//指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类
public class Application {

    public static void main(String[] args) {
        System.out.println("启动 Spring Boot...");
        System.out.println("Spring Boot前后端分离实战项目");
        SpringApplication.run(Application.class, args);
    }
}