package com.hbaseinaction.two.model;

/**
 * Created by Administrator on 2017/6/14.
 */
public abstract class User {
    public String user;
    public String name;
    public String email;
    public String password;

    @Override
    public String toString() {
        return String.format("<User: %s, %s, %s>",user,name,email);
    }

}
