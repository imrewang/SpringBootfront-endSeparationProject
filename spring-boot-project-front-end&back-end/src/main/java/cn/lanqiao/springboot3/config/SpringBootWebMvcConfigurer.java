package cn.lanqiao.springboot3.config;

import cn.lanqiao.springboot3.common.Constants;
import cn.lanqiao.springboot3.config.handler.TokenToUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//bean管理是指（1）spring创建对象 （2）spring注入属性。
// 当我们在将一个类上标注@Service或者@Controller或@Component或@Repository注解之后，spring的组件扫描就会自动发现它，
// 并且会将其初始化为spring应用上下文中的bean。


@Configuration
public class SpringBootWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private TokenToUserMethodArgumentResolver tokenUserMethodArgumentResolver;

    /**
     * TokenToUser 注解处理方法
     *
     * @param argumentResolvers
     */
    //addArgumentResolvers方法用于向Spring MVC中添加自定义的方法参数解析器，这里将TokenToUserMethodArgumentResolver添加到解析器列表中。
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tokenUserMethodArgumentResolver);
    }

    //addResourceHandlers方法用于添加静态资源处理器，这里将/files/**映射到本地文件系统中的文件上传路径。
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/files/**").addResourceLocations("file:"+ Constants.FILE_UPLOAD_PATH);

    }
}
