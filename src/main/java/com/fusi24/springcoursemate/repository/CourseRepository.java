package com.fusi24.springcoursemate.repository;

import com.fusi24.springcoursemate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);

    @Query(value = "select * from course where status='OPEN' and courseid in " +
            "(select distinct courseid from student_course where id=?1);",
            nativeQuery = true)
    List<Course> findByCourseMember(long userid);

    List<Course> findByCreatedByAndStatus(String username, String status);

    List<Course> findAllByOrderByCreatedDateAsc();

    List<Course> findByStatus(String status);
}
