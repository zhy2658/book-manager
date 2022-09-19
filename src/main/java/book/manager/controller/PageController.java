package book.manager.controller;

import book.manager.service.SimpleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Controller
public class PageController {

    @Resource
    SimpleService simpleService;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @PreAuthorize("hasAnyRole('user','admin')")
    @RequestMapping({"/","/index"})
    public String index(Model model){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        System.out.println(user.getUsername());
        System.out.println(user.getAuthorities());
        model.addAttribute("uname",user.getUsername());

        return "index";
    }


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/auth")
    @ResponseBody
    public String auth(){
//        SecurityContext context = SecurityContextHolder.getContext();  //获取SecurityContext对象，当前会话没有登录
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("yousa",null, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin")); //手动创建一个UsernamePasswordAuthenticationToken对象,也就是用户的认证信息，角色需要添加ROLE_前缀，权限直接写
//        context.setAuthentication(token);  //手动设定SecurityContext的认证信息
        return "login success";
    }


}
