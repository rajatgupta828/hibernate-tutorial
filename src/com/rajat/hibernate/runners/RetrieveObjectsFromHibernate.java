package com.rajat.hibernate.runners;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rajat.hibernate.entities.Student;

public class RetrieveObjectsFromHibernate {

	public static void main(String[] args) {
		// Create session Factory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		System.out.println("Factory Created  : " + factory);

		// Create the session

		Session currentSession = factory.getCurrentSession();

		try {
			// . Begin the transaction , Use the get mthod to get the student Details and
			// store in object

			currentSession.beginTransaction();

			Student myStudent = currentSession.get(Student.class, 1);
			System.out.println(myStudent.toString());

			// Commit the transactions

			currentSession.getTransaction().commit();

			System.out.println("Fetched Students");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Got exception while creating student...");
		}
	}

}
