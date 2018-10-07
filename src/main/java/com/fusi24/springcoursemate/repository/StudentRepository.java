package com.fusi24.springcoursemate.repository;

import com.fusi24.springcoursemate.entity.Student;
import com.fusi24.springcoursemate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByLastname(String lastname);

    Student findByUser(User user);
}
