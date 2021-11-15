package com.rajat.hibernate.runners;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rajat.hibernate.entities.Student;

public class UpdateDataHibernate {
	public static void main(String[] args) {

		// Create session Factory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		System.out.println("Factory Created  : " + factory);

		// Create the session

		Session currentSession = factory.getCurrentSession();

		try {
			// Create the student Object

			int studentID = 1;

			// Start the transaction

			currentSession.beginTransaction();

			// Save the current

			Student fetchedStudent = currentSession.get(Student.class, studentID);

			// Commit the transaction
			fetchedStudent.setEmail("updatedemail@gmail.com");

			// Bulk Updates :
			System.out.println("Update Last Name");
			
			currentSession.createQuery("update Student set last_name = 'gupta1'").executeUpdate();

			// Sysout for completion
			currentSession.getTransaction().commit();
			System.out.println("Saved Student");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Got exception while creating student...");
		} finally {
			currentSession.close();
			factory.close();
		}

	}
}
