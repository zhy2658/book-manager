package book.manager.controller.api;

import book.manager.entity.Book;
import book.manager.mapper.BookMapper;
import book.manager.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/admin")
public class AdminApiController {

    @Resource
    BookService bookService;

    @RequestMapping(value = "/del-book",method = RequestMethod.GET)
    public String deleteBook(@RequestParam("bid") int bid){
        bookService.deleteBook(bid);
        return "redirect:/page/admin/book";
    }

    @RequestMapping(value = "/add-book",method = RequestMethod.POST)
    public String addBook( Book book){
        bookService.addBook(book);
        return "redirect:/page/admin/book";
    }

}
