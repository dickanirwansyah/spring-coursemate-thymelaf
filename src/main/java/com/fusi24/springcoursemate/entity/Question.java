package com.fusi24.springcoursemate.entity;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "grade")
    private int grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewid", nullable = false)
    private PeerReview peerReview;

    public Question(){}

    public Question(String type, String title, String description, int grade, PeerReview peerReview){
        this.type = type;
        this.title = title;
        this.description = description;
        this.grade = grade;
        this.peerReview = peerReview;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getGrade(){
        return grade;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }

    public PeerReview getPeerReview(){
        return peerReview;
    }

    public void setPeerReview(PeerReview peerReview){
        this.peerReview = peerReview;
    }
}
