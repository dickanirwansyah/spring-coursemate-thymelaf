package com.fusi24.springcoursemate.controller;

import com.fusi24.springcoursemate.entity.Student;
import com.fusi24.springcoursemate.entity.User;
import com.fusi24.springcoursemate.repository.StudentRepository;
import com.fusi24.springcoursemate.repository.UserRepository;
import com.fusi24.springcoursemate.request.PasswordCheck;
import com.fusi24.springcoursemate.request.SignUpForm;
import com.fusi24.springcoursemate.request.SignUpTeacherForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    /** page controller **/
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/login")
    public ModelAndView loginView(){
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        view.addObject("title", "Login System");
        return view;
    }

    @GetMapping(value = "/signup")
    public String signUp(Model model){
        model.addAttribute("signupform", new SignUpForm());
        model.addAttribute("title", "SignUp Form");
        return "content/signup";
    }

    /** hak akses superuser**/
    @PreAuthorize("hasAuthority('SUPERUSER')")
    @GetMapping(value = "/signupteacher")
    public String signUpTeacher(Model model){
        model.addAttribute("teacherForm", new SignUpTeacherForm());
        model.addAttribute("title", "Sign Up Teacher");
        return "content/signup-teacher";
    }

    /**edit profile **/
    @GetMapping(value = "/editProfile")
    public String editProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);
        Student student = studentRepository.findByUser(user);
        /**log**/
        System.out.print("Student Number : "+student.getStudentNumber()
                +" Student ID: "+student.getId());
        model.addAttribute("student", student);
        model.addAttribute("title", "Update Profile");
        model.addAttribute("pwdcheck", new PasswordCheck());
        return "content/edit-profile";
    }

    /** update password **/
    @PostMapping(value = "/changePassword")
    public String changePassword(PasswordCheck passwordCheck){
        String password = passwordCheck.getPassword();
        String confirm = passwordCheck.getConfirm();

        /** test confirm password **/
        System.out.println("Password : "+password+" dan "+" Re-password : "+confirm);

        if (!password.equals(confirm)){
            return "redirect:/editProfile?nomatch";
        }else if(password.length() < 7){
            return "redirect:/editProfile?length";
        }

        BCryptPasswordEncoder pwdEncode = new BCryptPasswordEncoder();
        String passwordHash = pwdEncode.encode(password);

        /** test hasil hash password **/
        System.out.println("password : "+passwordHash);

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        user.setPasswordHash(passwordHash);
        userRepository.save(user);
        return "redirect:/editProfile?pwdchanged";
    }

    /** save profile student **/
    @PostMapping(value = "/saveprofile")
    public String saveProfile(@ModelAttribute  Student student){

        try {
            studentRepository.save(student);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/editProfile";
    }

    /** save user **/
    @PostMapping(value = "/saveuser")
    public String save(@Valid @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) { // validation errors
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                User newUser = new User();
                newUser.setPasswordHash(hashPwd);
                newUser.setActive(true);
                newUser.setUsername(signupForm.getUsername());
                newUser.setRole("USER");

                Student newStudent = new Student(signupForm.getStudentnumber(), signupForm.getFirstname(), signupForm.getLastname(), signupForm.getDepartment(), signupForm.getEmail());
                newStudent.setUser(newUser);

                if (userRepository.findByUsername(signupForm.getUsername()) == null) {
                    userRepository.save(newUser);
                    studentRepository.save(newStudent);
                }
                else {
                    bindingResult.rejectValue("username", "error.userexists", "Username already exists");
                    return "content/signup";
                }
            }
            else {
                bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");
                return "content/signup";
            }
        }
        else {
            return "content/signup";
        }
        return "redirect:/login?signup=true";
    }

    /** signup teacher **/
    @PostMapping(value = "/saveteacher")
    public String saveTeacher(@Valid @ModelAttribute("teacherForm")SignUpTeacherForm teacherForm,
                              BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            if (teacherForm.getPassword().equals(teacherForm.getPasswordCheck())){
                String plaintext = teacherForm.getPassword();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String enkripsi = passwordEncoder.encode(plaintext);

                User user = new User();
                user.setRole("ADMIN");
                user.setUsername(teacherForm.getUsername());
                user.setActive(true);
                user.setPasswordHash(enkripsi);

                Student student = new Student();
                student.setUser(user);

                if (userRepository.findByUsername(teacherForm.getUsername()) == null){
                    userRepository.save(user);
                    studentRepository.save(student);
                }else {
                    bindingResult.rejectValue("username", "error.userexists", "User is already taken");
                    return "content/signup-teacher";
                }
            }
            else {
                bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Password doesn't match");
                return "content/signup-teacher";
            }
        }else {
            return "content/signup-teacher";
        }
        return "redirect:/courses";
    }
}
