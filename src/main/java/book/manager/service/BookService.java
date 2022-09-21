package book.manager.service;

import book.manager.entity.Book;
import book.manager.entity.Borrow;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BookService {

    List<Book> getAllBook();

    void deleteBook(int bid);

    int addBook(Book book);

    void addBorrow(int bid,int sid);

    List<Book> getBooksWithoutBorrow();

    List<Book> getAllBorrowBookByUid(int sid);

    void returnBook(int bid,int uid);
}
