package com.fusi24.springcoursemate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "peer_review")
@EntityListeners(AuditingEntityListener.class)
public class PeerReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseid")
    private Course course;

    /** setiap ada one to many akan
     *  membuat junction table, atau table tambahan
     *  untuk menampung id = peer_review_reviewid
     *  dan question_id
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions;

    @Column(name = "grade")
    private int grade;

    @Column(name = "description")
    private String description;

    @CreatedDate
    private Date createdDate;

    @CreatedBy
    private String createdBy;

    public PeerReview(){}

    public PeerReview(Student student, Course course){
        super();
        this.student= student;
        this.course = course;
    }

    public long getReviewid(){
        return reviewid;
    }

    public void setReviewid(long reviewid){
        this.reviewid = reviewid;
    }

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public Course getCourse(){
        return course;
    }

    public void setCourse(Course course){
        this.course = course;
    }

    public List<Question> getQuestions(){
        return questions;
    }

    public void setQuestions(List<Question> questions){
        this.questions = questions;
    }

    public int getGrade(){
        return grade;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Date getCreatedDate(){
        return createdDate;
    }

    public void setCreatedDate(Date createdDate){
        this.createdDate = createdDate;
    }

    public String getCreatedBy(){
        return createdBy;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
}
