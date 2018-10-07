package com.fusi24.springcoursemate.request;

public class PasswordCheck {

    private String password;
    private String confirm;

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getConfirm(){
        return confirm;
    }

    public void setConfirm(String confirm){
        this.confirm = confirm;
    }
}
