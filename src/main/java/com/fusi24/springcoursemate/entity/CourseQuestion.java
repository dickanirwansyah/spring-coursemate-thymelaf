package com.fusi24.springcoursemate.entity;

import javax.persistence.*;

@Entity
@Table(name = "course_question")
public class CourseQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "questionorder")
    private int questionorder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseid", nullable = false)
    private Course course;

    public CourseQuestion(){}

    public CourseQuestion(Course course){
        super();
        this.course = course;
    }

    public CourseQuestion(String title, int questionorder, Course course){
        super();
        this.title = title;
        this.questionorder = questionorder;
        this.course = course;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getQuestionorder(){
        return questionorder;
    }

    public void setQuestionorder(int questionorder){
        this.questionorder = questionorder;
    }

    public Course getCourse(){
        return course;
    }

    public void setCourse(Course course){
        this.course = course;
    }
}
