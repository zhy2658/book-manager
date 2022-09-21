package book.manager.service.impl;

import book.manager.entity.Book;
import book.manager.entity.Borrow;
import book.manager.mapper.BookMapper;
import book.manager.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper bookMapper;

    @Override
    public List<Book> getAllBook() {
        List<Book> list=bookMapper.getAllBook();
        return list;
    }

    @Override
    public void deleteBook(int bid) {
        bookMapper.deleteBook(bid);
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public void addBorrow(int bid, int uid) {
        bookMapper.addBorrow(bid,uid);
    }

    @Override
    public List<Book> getBooksWithoutBorrow() {
        List<Book> books=bookMapper.getAllBook();
        List<Integer> borrows = bookMapper.getAllBorrow()
                .stream().map(Borrow::getBid)
                .collect(Collectors.toList());
        return books
                .stream()
                .filter(book -> !borrows.contains(book.getBid()))
                .collect(Collectors.toList());


    }

    @Override
    public List<Book> getAllBorrowBookByUid(int uid) {
        List<Integer> borrows = bookMapper.getAllBorrowByUid(uid)
                .stream().map(Borrow::getBid)
                .collect(Collectors.toList());

        List<Book> books=getAllBook();
        return books
                .stream()
                .filter(book -> borrows.contains(book.getBid()))
                .collect(Collectors.toList());
    }

    @Override
    public void returnBook(int bid, int uid) {
        bookMapper.returnBook(bid,uid);
    }
}
