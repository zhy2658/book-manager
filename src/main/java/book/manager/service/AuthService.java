package book.manager.service;

import book.manager.entity.AuthUser;
import book.manager.entity.Student;

import javax.servlet.http.HttpSession;

public interface AuthService {

    boolean register(String name,String sex,String grade,String password,int age);

    Student getStudentInfo(int uid);

    AuthUser findUser(HttpSession session);
}
