package com.fusi24.springcoursemate.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignUpTeacherForm {

    @NotEmpty
    @Size(max = 100, min = 4)
    private String username;

    @NotEmpty
    @Size(max = 100, min = 4)
    private String password;

    @NotEmpty
    @Size(max = 100, min = 4)
    private String passwordCheck;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPasswordCheck(){
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck){
        this.passwordCheck = passwordCheck;
    }
}
