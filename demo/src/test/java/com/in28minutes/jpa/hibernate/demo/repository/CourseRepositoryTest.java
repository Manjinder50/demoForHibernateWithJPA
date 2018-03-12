package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseRepositoryTest {

	@Autowired
	CourseRepository repository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void findById_basic() {
		
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 steps",course.getName());
		logger.info("Test is being run");
	}
	
	@Test
	@DirtiesContext
	public void deleteById_basic() {
		
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	@Test
	public void saveCourse_basic() {
		//get a course
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 steps",course.getName());
	
		//update details
		course.setName("JPA in 50 steps-Updated");
		repository.save(course);
		
		//check the value
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 50 steps-Updated",course1.getName());
	}
	
	@Test
	public void playWithEntityManagerTest() {
		repository.playWithEntityManager();
	}
}
