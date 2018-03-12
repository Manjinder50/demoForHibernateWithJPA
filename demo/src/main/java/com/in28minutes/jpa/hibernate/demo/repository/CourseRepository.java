package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
	public Course findById(Long id)
	{
		return em.find(Course.class, id);
	}
	
	public Course save(Course course){
		if(course.getId()==null){
			//insert
			em.persist(course);
		}else {
			//update
			em.merge(course);
		}
		return course;
		
	}
	
	public void deleteById(Long id){
		 Course course=findById(id);
		 em.remove(course);
	}
	
	public void playWithEntityManager(){
		//To create a new Entity use persist
		/*Course course = new Course("Web Services in 100 steps");
		em.persist(course);
		*/
		Course course1 = new Course("Web Services in 100 steps");
		em.persist(course1);
		/*Course course2 = new Course("Angular JS in 100 steps");
		em.persist(course2);
		
		em.flush();
	//	em.clear();
		course1.setName("Web Services in 100 steps - updated");
		//em.flush();
		
		//em.detach(course2);
		course2.setName("Angular JS in 100 steps - Updated");
		//We do not want the course1 to be updated with the new name given in setter
		em.refresh(course1);
		em.flush();*/
		
		Course course2 = findById(10001L);
		course2.setName("JPA in 50 steps-Updated");
		
	}
}
