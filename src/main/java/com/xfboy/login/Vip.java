package com.xfboy.login;

/**
 * Vip
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/8
 */
public class Vip {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
