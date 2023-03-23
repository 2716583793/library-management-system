package com.wang.bean;

/**
 * 管理员实体类
 */
public class Admin {
    private String username; //管理员账号
    private String password; //管理员密码

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
