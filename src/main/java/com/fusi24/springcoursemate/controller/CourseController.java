package com.fusi24.springcoursemate.controller;

import com.fusi24.springcoursemate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PeerReviewRepository peerReviewRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CourseQuestionRepository courseQuestionRepository;

    /** courses controller **/

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ModelAndView getIndex(){
        ModelAndView view = new ModelAndView();
        view.setViewName("content/courses/courses");
        view.addObject("title", "Courses Index");
        return view;
    }
}
