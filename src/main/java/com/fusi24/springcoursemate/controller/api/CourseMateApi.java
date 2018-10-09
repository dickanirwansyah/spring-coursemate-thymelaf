package com.fusi24.springcoursemate.controller.api;

import com.fusi24.springcoursemate.entity.Course;
import com.fusi24.springcoursemate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class CourseMateApi {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PeerReviewRepository peerReviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping(value = "/getcourses")
    public List<Course> getCourses(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        authentication.getAuthorities();

        long id = userRepository.findByUsername(username).getId();

        List<Course> courses = null;
        if (hasRole("ADMIN")){
            courses = courseRepository.findByCreatedByAndStatus(username, "OPEN");
        }else if(hasRole("SUPERUSER")){
            courses = courseRepository.findAll();
        }else {
            courses = courseRepository.findByCourseMember(id);
        }

        return courses;
    }

    /** check role **/
    private boolean hasRole(String role){
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities();

        boolean hasRole = false;
        for (GrantedAuthority authority : authorities){
            hasRole = authority.getAuthority().equals(role);
            if (hasRole){
                break;
            }
        }
        return hasRole;
    }
}
