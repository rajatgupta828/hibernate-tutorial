package com.rajat.hibernate.runners;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rajat.hibernate.entities.Student;

public class HQLExampleQueryBuilder {
	
	public static void main(String[]  args) {
		
		// Create session Factory
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		System.out.println("Factory Created  : " + factory);
		
		
		
		// Create the session
		
		Session currentSession = factory.getCurrentSession();
		
		
		try {
			// Start the transaction
			
			currentSession.beginTransaction();
			
			// Query Students

			//MySQL query :  Select * from student 
			List<Student> studentList = currentSession
					.createQuery("from Student")
					.getResultList();
			
			
			int i = 0;
			// Display Students
			i = fetchStudentsAndDisplay(studentList, i);
			
			System.out.println("Executing where query");
			//MySQL query :  Select * from student where last_name = "Gupta"
			List<Student> whereStudentList = currentSession
					.createQuery("from Student s1 where s1.lastName = 'Gupta'")
					.getResultList();
			
			i = 0;
			i = fetchStudentsAndDisplay(whereStudentList, i);
			
			
			//MySQL query :  Select * from student where last_name = "Gupta"
			List<Student> likeStudentList = currentSession
					.createQuery("from Student s1 where s1.email like '%@gmail.com'")
					.getResultList();
			
			i = 0;
			i = fetchStudentsAndDisplay(likeStudentList, i);
			
			
			// Commit the transaction
			currentSession.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Got exception while creating student...");
		}finally {
			currentSession.close();
		}
		
	}

	private static int fetchStudentsAndDisplay(List<Student> studentList, int i) {
		for(Student st : studentList) {
			i += 1;
			System.out.println("Fetched student " + i + " " + st.toString());
		}
		return i;
	}

}
