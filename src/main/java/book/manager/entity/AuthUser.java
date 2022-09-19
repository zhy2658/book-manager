package book.manager.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthUser {

    private int uid;
    private String username;
    private String password;
    private String role;

    public AuthUser(){}
//    public AuthUser(int uid,String username,String password,String role){
//        this.uid =uid;
//        this.username=username;
//        this.password=password;
//        this.role=role;
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
}
