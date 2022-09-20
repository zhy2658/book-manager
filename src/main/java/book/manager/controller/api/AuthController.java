package book.manager.controller.api;

import book.manager.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    AuthService authService;


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("sex") String sex,
                           @RequestParam("grade") String grade,
                           @RequestParam("age") int age){

//      String name, String sex, String grade, String password,String age
        authService.register(username,sex,grade,password,age);

//        return "login";
        return "redirect:/login";   //重定向
    }
}
