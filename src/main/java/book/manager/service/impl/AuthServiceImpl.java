package book.manager.service.impl;

import book.manager.entity.AuthUser;
import book.manager.entity.Student;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    UserMapper userMapper;

    @Resource
    AuthService authService;

    @Transactional
    @Override
    public boolean register(String name, String sex, String grade, String password,int age) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AuthUser user = new AuthUser(0,name,encoder.encode(password),"user");
        if( userMapper.registerUser(user) <=0){
            throw new RuntimeException("用户基本信息添加失败！");
        }
        if(userMapper.addStudentInfo(name,sex,grade,age,user.getUid()) <=0){
            throw new RuntimeException("学生详细信息添加失败！");
        }

        return true;
    }

    @Transactional
    @Override
    public Student getStudentInfo(int uid) {
        Student student = userMapper.getStudentInfo(uid);
        return student;
    }
    public AuthUser findUser(HttpSession session){
        AuthUser user = (AuthUser) session.getAttribute("user");
        if(user == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = userMapper.getPasswordByUserName(authentication.getName());
            session.setAttribute("user",user);
        }
        return user;
    }

}
