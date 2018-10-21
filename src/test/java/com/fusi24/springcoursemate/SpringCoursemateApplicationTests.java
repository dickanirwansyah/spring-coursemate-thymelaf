package com.fusi24.springcoursemate;

import com.fusi24.springcoursemate.entity.Student;
import com.fusi24.springcoursemate.entity.User;
import com.fusi24.springcoursemate.repository.StudentRepository;
import com.fusi24.springcoursemate.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCoursemateApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	StudentRepository studentRepository;

	/**
	@Test
	public void contextLoads() {
	}
	**/

	/**
	@Test
	public void saveTeacher(){


		String pswd = "rootroot";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashpswd = passwordEncoder.encode(pswd);

		User user = new User();
		user.setUsername("admin");
		user.setPasswordHash(hashpswd);
		user.setRole("ADMIN");
		user.setActive(true);

		Student student = new Student();
		student.setUser(user);

		userRepository.save(user);
		studentRepository.save(student);
	}
	**/

}
