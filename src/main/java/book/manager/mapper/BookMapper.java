package book.manager.mapper;

import book.manager.entity.Book;
import book.manager.entity.Borrow;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    List<Book> getAllBook();

    @Delete("delete from book where bid = #{bid}")
    void deleteBook(@Param("bid") int bid);

    @Options(useGeneratedKeys = true, keyColumn = "bid", keyProperty = "bid")
    @Insert("insert into book(title,description,price,author) values(#{title},#{description},#{price},#{author})")
    int addBook(Book book);

    @Insert("insert into borrow(bid,uid,time) values(#{bid},#{uid},NOW())")
    int addBorrow(@Param("bid") int bid,
                  @Param("uid") int uid);

    @Select("select * from borrow")
    List<Borrow> getAllBorrow();

    @Select("select * from borrow where uid = #{uid}")
    List<Borrow> getAllBorrowByUid(int uid);

    @Delete("delete from borrow where uid=#{uid} and bid=#{bid}")
    void returnBook(@Param("bid")int bid, @Param("uid")int uid);

}
