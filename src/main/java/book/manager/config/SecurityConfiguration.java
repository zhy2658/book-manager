package book.manager.config;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import book.manager.service.impl.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    UserAuthService service;

    @Resource
    PersistentTokenRepository repository;

    @Resource
    UserMapper userMapper;

    //设置存储策略 -> 可继承(作用：SecurityContextHolder在子线程也可以获取用户信息)
    @PostConstruct
    public void init(){
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    //把记住我的token存到数据库里面
    @Bean
    public PersistentTokenRepository jdbcRepository(@Autowired DataSource dataSource ){
        JdbcTokenRepositoryImpl  repository = new JdbcTokenRepositoryImpl(); //使用基于jdbc实现
        repository.setDataSource(dataSource);  //配置数据源
//        repository.setCreateTableOnStartup(true);  //启动时自动创建用于存储token的表（建议第一次使用后删除改行）
        return repository;
    }

    //配置自定义登录界面
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                角色配置
                .authorizeRequests() //首先配置哪些请求会被拦截，哪些请求必须需要什么角色才能访问
                .antMatchers("/static/**","/page/auth/**","/api/auth/**").permitAll()  //静态资源，需要permitall来允许所有人访问
                .antMatchers("/page/user/**","/api/user/**").hasRole("user")
                .antMatchers("/page/admin/**","/api/admin/**").hasRole("admin")
                .anyRequest().hasAnyRole("user","admin")
//                .antMatchers("/index").hasAnyAuthority("page:index")   //普通权限
//                .anyRequest().hasAnyAuthority("page:admin")      //管理权限

                //登录功能配置
                .and()
                .formLogin()
                .loginPage("/page/auth/login")
                .loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .permitAll()

                //注销功能配置
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessUrl("/login")
                .and()
                .csrf().disable()   //关闭csrf

                //记住我配置
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(60 * 60 * 24 * 7 )
                .tokenRepository(repository);

    }
    //登陆后存储user信息
    private void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        AuthUser user= userMapper.getPasswordByUserName(authentication.getName());
        session.setAttribute("user",user);
        if(user.getRole().equals("admin")){
            response.sendRedirect("/mvc/page/admin/index");
        }
        else if(user.getRole().equals("user")){
            response.sendRedirect("/mvc/page/user/index");
        }


    }

    //    配置登录账号密码
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
