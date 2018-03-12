package com.in28minutes.jpa.hibernate.demo.repository;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class JPQLTest {

	@Autowired
	EntityManager em;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void jpql_basic() {

		Query query = em.createNamedQuery("get_all_courses");
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}",resultList);
	}
	
	@Test
	public void jpql_Typed() {

		TypedQuery<Course> query = 
				em.createNamedQuery("get_all_courses",Course.class);
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}",resultList);
	}
	@Test
	public void jpql_Where() {

		TypedQuery<Course> query = 
				em.createNamedQuery("get_all_100_step_courses",Course.class);
		List resultList = query.getResultList();
		logger.info("Select c from Course c where name like '%100 steps'-> {}",resultList);
	}

}
