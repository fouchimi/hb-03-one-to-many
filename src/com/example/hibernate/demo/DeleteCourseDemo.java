package com.example.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {

	public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.addAnnotatedClass(Course.class)
                .buildSessionFactory();
 
        // create a session
        Session session = factory.getCurrentSession();
 
        try {
      
            // start a transaction
            session.beginTransaction();
            
            // get the course from db
            Course course = session.get(Course.class, 1);
            
            System.out.println("Course: " + course);
            
            // delete course
            session.delete(course);
            
            // commit transaction
            session.getTransaction().commit();
            
            System.out.println("Done!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
        	session.close();
            factory.close();
        }
    }
}
