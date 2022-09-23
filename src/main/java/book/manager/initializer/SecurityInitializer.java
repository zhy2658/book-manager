package book.manager.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletContext;

//初始化SpringSecurity
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    // 解决请求参数中文乱码问题
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        servletContext.addFilter("characterEncodingFilter",new CharacterEncodingFilter("UTF-8",true)).addMappingForUrlPatterns(null,false,"/*");
        super.beforeSpringSecurityFilterChain(servletContext);
    }
}
