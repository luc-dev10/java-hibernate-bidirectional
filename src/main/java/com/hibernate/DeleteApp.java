package com.hibernate;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteApp {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Session
        Session session = factory.openSession();

        try {

            // get instructor
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            // delete a record
            if (instructor != null) {
                // begin transaction
                session.beginTransaction();
                // delete
                session.delete(instructor);
                // commit
                session.getTransaction().commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
