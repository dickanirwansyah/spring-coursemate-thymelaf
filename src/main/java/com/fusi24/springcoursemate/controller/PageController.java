package com.fusi24.springcoursemate.controller;

import com.fusi24.springcoursemate.entity.Student;
import com.fusi24.springcoursemate.entity.User;
import com.fusi24.springcoursemate.repository.StudentRepository;
import com.fusi24.springcoursemate.repository.UserRepository;
import com.fusi24.springcoursemate.request.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PageController {

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
}
