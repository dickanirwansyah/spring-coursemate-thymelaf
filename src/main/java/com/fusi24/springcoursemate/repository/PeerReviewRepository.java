package com.fusi24.springcoursemate.repository;

import com.fusi24.springcoursemate.entity.Course;
import com.fusi24.springcoursemate.entity.PeerReview;
import com.fusi24.springcoursemate.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeerReviewRepository extends JpaRepository<PeerReview, Long> {

    List<PeerReview> findByStudentAndCourse(Student student, Course course);

    List<PeerReview> findByCourseOrderByStudentAscCourseAsc(Course course);

    List<PeerReview> findByStudentAndCourseAndCreatedBy(Student student, Course course, String reviewer);

    List<PeerReview> findAllByOrderByStudentAsc();
}
