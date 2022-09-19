package book.manager.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Collections;
import java.util.List;

@ComponentScans({
        @ComponentScan("book.manager.controller")
})
@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启用注解管理security
public class MvcConfiguration implements WebMvcConfigurer {

    //1.我们需要 ThymeleafViewResolver 作为视图解析器，并解析我们的html页面
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(@Autowired SpringTemplateEngine springTemplateEngine){
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setOrder(1);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(springTemplateEngine);
        return resolver;
    }

    //    2.配置模板解析器
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setSuffix(".html");
        resolver.setPrefix("/WEB-INF/template/");
        return resolver;
    }

    //    3.配置模板引擎
    @Bean
    public SpringTemplateEngine springTemplateEngine(@Autowired ITemplateResolver resolver){
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        engine.addDialect(new SpringSecurityDialect()); //添加SpringSecurity的方言
        return engine;
    }

    //上传文件配置
    @Bean("multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1024 * 1024 *10);
        resolver.setDefaultEncoding("UTF-8");
        return  resolver;
    }

//    静态资源配置
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

    public void  addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }

    //    拦截器
    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new MainInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/home");
//
//        registry.addInterceptor(new SubInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/home");
    }

    //配置接口响应可以直接返回对象，不用转成JSON格式
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter=new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON)); // 只有服务器接受的是json的数据时才进行转换成对象为json
        converters.add(new FastJsonHttpMessageConverter());
    }

}
