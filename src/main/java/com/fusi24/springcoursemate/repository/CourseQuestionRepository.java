package com.fusi24.springcoursemate.repository;

import com.fusi24.springcoursemate.entity.Course;
import com.fusi24.springcoursemate.entity.CourseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseQuestionRepository extends JpaRepository<CourseQuestion, Long> {

    List<CourseQuestion> findByCourseOrderByQuestionorder(Course course);
}
