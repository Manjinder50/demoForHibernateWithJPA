package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class StudentRepositoryTest {

	@Autowired
	StudentRepository repository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void someTest(){
		//Database Operation 1- retrieve student
		Student student = em.find(Student.class, 20001L);
		//Persistence Context(student)
		
		//Database Operation 2- retrieve passport
		Passport passport = student.getPassport();
		//Persistence Context(student,passport)
		
		//Database Operation 3- update passport
		passport.setNumber("N785674");
		//Persistence Context(student,passport++)
		
		//Database Operation 4- update student
		student.setName("Ranga -Updated");
		//Persistence Context(student++,passport++)
		
	}
	@Test
	@Transactional
	public void retrieveStudentAndPassport() {
		
		/*Student student = repository.findById(10001L);
		assertEquals("JPA in 50 steps",student.getName());*/
		Student student = em.find(Student.class, 20001L);
		logger.info("Student -> {}",student);
		logger.info("passport -> {} ",student.getPassport());
	}

	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		
		/*Student student = repository.findById(10001L);
		assertEquals("JPA in 50 steps",student.getName());*/
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {}",passport);
		logger.info("student -> {} ",passport.getStudent());
	}
	@Test
	@DirtiesContext
	public void deleteById_basic() {
		
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	@Test
	public void saveStudent_basic() {
		//get a student
		Student student = repository.findById(10001L);
		assertEquals("JPA in 50 steps",student.getName());
	
		//update details
		student.setName("JPA in 50 steps-Updated");
		repository.save(student);
		
		//check the value
		Student student1 = repository.findById(10001L);
		assertEquals("JPA in 50 steps-Updated",student1.getName());
	}
	
	/*@Test
	public void playWithEntityManagerTest() {
		repository.playWithEntityManager();
	}*/
}
