package com.rajat.hibernate.runners;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rajat.hibernate.entities.Student;

public class MultipleStudentsAddPrimaryKeyExample {

	public static void main(String[] args) {
		// Create session Factory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		System.out.println("Factory Created  : " + factory);

		// Create the session

		Session currentSession = factory.getCurrentSession();

		try {
			// Create the student Object

			Student s1 = new Student("Rajat", "Gupta", "rajatgupta828@gmail.com");
			Student s2 = new Student("Pankaj", "Dabas", "pankajdabas2626@gmail.com");
			Student s3 = new Student("Ankit", "Singh", "ankitsingh.iksc@gmail.com");

			// Start the transaction

			currentSession.beginTransaction();

			// Save the current

			currentSession.save(s1);
			currentSession.save(s2);
			currentSession.save(s3);

			// Commit the transaction
			currentSession.getTransaction().commit();

			// Sysout for completion

			System.out.println("Saved Studentsa");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Got exception while creating student...");
		}
	}

}
