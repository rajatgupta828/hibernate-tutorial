package com.rajat.hibernate.runners;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rajat.hibernate.entities.Student;

public class DeleteUsingHibernate {
	public static void main(String[] args) {

		// Create session Factory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		System.out.println("Factory Created  : " + factory);

		// Create the session

		Session currentSession = factory.getCurrentSession();

		try {
			// Create the student Object

			int studentID = 3;

			// Start the transaction

			currentSession.beginTransaction();

			// Save the current

			Student fetchedStudent = currentSession.get(Student.class, studentID);

			
			// Perform the delete
			currentSession.delete(fetchedStudent);
			
			// deleting using query
			currentSession.createQuery("delete from Student where id = 4")
			.executeUpdate();
			
			// Sysout for completion
			currentSession.getTransaction().commit();
			System.out.println("Deleted Student");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Got exception while Deleting student...");
		} finally {
			currentSession.close();
			factory.close();
		}

	}
}
