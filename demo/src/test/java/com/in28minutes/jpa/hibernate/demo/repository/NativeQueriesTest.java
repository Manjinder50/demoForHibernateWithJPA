package com.in28minutes.jpa.hibernate.demo.repository;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
public class NativeQueriesTest {

	@Autowired
	EntityManager em;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void native_queries_basic() {

		Query query = em.createNativeQuery("Select * from Course",Course.class);
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}",resultList);
	}
	
	@Test
	public void native_queries_with_paramater() {

		Query query = em.createNativeQuery("Select * from Course where id=?",Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("Select c from Course where id=? -> {}",resultList);
	}
	
	@Test
	public void native_queries_with_namedparamater() {

		Query query = em.createNativeQuery("Select * from Course where id=:id",Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("Select c from Course where id=:id -> {}",resultList);
	}
	@Test
	@Transactional
	public void native_queries_to_update() {

		Query query = em.createNativeQuery("Update Course set last_updated_date=sysdate()",Course.class);

		int noOfRowsUpdated = query.executeUpdate();
		logger.info("noOfRowsUpdated -> {}",noOfRowsUpdated);
	}
}
