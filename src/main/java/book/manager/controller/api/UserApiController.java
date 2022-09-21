package book.manager.controller.api;

import book.manager.entity.Book;
import book.manager.mapper.BookMapper;
import book.manager.service.AuthService;
import book.manager.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/user")
public class UserApiController {


    @Resource
    BookService bookService;
    @Resource
    AuthService authService;

    @RequestMapping(value = "/borrow-book")
    public String borrowBook(HttpSession httpSession,@RequestParam("bid") int bid){
        bookService.addBorrow(bid,authService.findUser(httpSession).getUid());
        return "redirect:/page/user/index";
    }

    @RequestMapping("/return-book")
    public String returnBook(HttpSession httpSession,@RequestParam("bid") int bid){
        bookService.returnBook(bid,authService.findUser(httpSession).getUid());
        return "redirect:/page/user/book";
    }

}
