package book.manager.service;

import book.manager.entity.AuthUser;

import javax.servlet.http.HttpSession;

public interface AuthService {

    boolean register(String name,String sex,String grade,String password,int age);

    String getStudentInfo(int uid);

    AuthUser findUser(HttpSession session);
}
