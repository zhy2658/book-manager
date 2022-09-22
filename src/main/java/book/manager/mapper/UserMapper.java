package book.manager.mapper;

import book.manager.entity.AuthUser;
import book.manager.entity.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from users where username = #{username}")
    AuthUser getPasswordByUserName(String username);

    @Options(useGeneratedKeys = true, keyColumn = "uid", keyProperty = "uid")
    @Insert("insert into users(username,password ,role) values(#{username},#{password},#{role})")
    int registerUser(AuthUser user);

    @Insert("insert into student(name,sex ,grade,age,uid) values(#{name},#{sex},#{grade},#{age},#{uid})")
    int addStudentInfo(@Param("name") String name,
                   @Param("sex") String sex,
                   @Param("grade") String grade,
                   @Param("age") int age,
                   @Param("uid") int uid);

    @Select("select * from student where uid = #{uid}")
    Student getStudentInfo(@Param("uid") int uid);

    @Select("select count(*) from users")
    int getUserCount();
}
