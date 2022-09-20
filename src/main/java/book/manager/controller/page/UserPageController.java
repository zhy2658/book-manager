package book.manager.controller.page;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/user")
public class UserPageController {

    @Resource
    AuthService authService;

    @Resource
    UserMapper userMapper;

    @RequestMapping({"/","/index"})
    public String index(HttpSession session, Model model){
        AuthUser user=authService.findUser(session);
        model.addAttribute("uname",authService.getStudentInfo(user.getUid()));
        model.addAttribute("user",user);
        return "/user/index";
    }
}
