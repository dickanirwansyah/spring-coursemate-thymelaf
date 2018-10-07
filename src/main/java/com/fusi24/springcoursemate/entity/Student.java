package com.fusi24.springcoursemate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "student_number")
    private String studentNumber;

    @Column(name = "department")
    private String department;

    @Email
    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "courseid")})
    @JsonIgnore
    private Set<Course> courses = new HashSet<Course>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @JsonIgnore
    private List<PeerReview> reviews;

    public Student(){}

    public Student(String studentNumber, String firstname, String lastname, String department, String email){
        super();
        this.studentNumber = studentNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.email = email;
    }

    public Student(String studentNumber, String firstname, String lastname, String department, String email, User user){
        super();
        this.studentNumber = studentNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.email = email;
        this.user = user;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
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

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public String getStudentNumber(){
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber){
        this.studentNumber = studentNumber;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Set<Course> getCourses(){
        return courses;
    }

    public void setCourses(Set<Course> courses){
        this.courses = courses;
    }

    public boolean hasCourse(Course course){
        for (Course studentCourse : getCourses()){
            if (studentCourse.getCourseid() == course.getCourseid()){
                return true;
            }
        }
        return false;
    }
}
