package com.fusi24.springcoursemate.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignUpForm {

    @NotEmpty
    @Size(min = 5, max = 30)
    private String username;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String studentnumber;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String firstname;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String lastname;

    @Email
    @NotEmpty
    @Size(min = 5, max = 30)
    private String email;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String department;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String password;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String passwordCheck;

    @NotEmpty
    private String role = "USER";

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getStudentnumber(){
        return studentnumber;
    }

    public void setStudentnumber(String studentnumber){
        this.studentnumber = studentnumber;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department = department;
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

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

}
