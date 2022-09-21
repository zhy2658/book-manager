package book.manager.controller.page;

import book.manager.entity.AuthUser;
import book.manager.entity.Book;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import book.manager.service.BookService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/page/admin")
public class AdminPageController {

    @Resource
    AuthService authService;

    @Resource
    UserMapper userMapper;

    @Resource
    BookService bookService;

    @RequestMapping({"/","/index"})
    public String index(HttpSession session, Model model){
        AuthUser user=authService.findUser(session);
        model.addAttribute("student",authService.getStudentInfo(user.getUid()));
        model.addAttribute("user",user);
        return "/admin/index";
    }
    @RequestMapping({"/book",})
    public String book(HttpSession session, Model model){
        AuthUser user=authService.findUser(session);
        model.addAttribute("student",authService.getStudentInfo(user.getUid()));
        model.addAttribute("user",user);
        model.addAttribute("bookList",bookService.getAllBook());

        return "/admin/book";
    }
    @RequestMapping({"/add-book",})
    public String addBook(HttpSession session, Model model){
        AuthUser user=authService.findUser(session);
        model.addAttribute("student",authService.getStudentInfo(user.getUid()));
        model.addAttribute("user",user);
//        model.addAttribute("bookList",bookService.getAllBook());

        return "/admin/add-book";
    }
}
