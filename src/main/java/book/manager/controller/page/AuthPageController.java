package book.manager.controller.page;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import book.manager.service.impl.SimpleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/auth")
public class AuthPageController {
    @Resource
    AuthService authService;

    @Resource
    UserMapper userMapper;

//    主页
//    @PreAuthorize("hasAnyRole('user','admin')"

//    登录页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

//    注册页面
    @RequestMapping("/register")
    public String register(){
        return "register";
    }



}
