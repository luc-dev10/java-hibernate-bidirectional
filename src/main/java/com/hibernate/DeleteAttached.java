package com.hibernate;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteAttached {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Session
        Session session = factory.openSession();
        try {

            // get instructor detail
            int id = 3;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            // delete a record
            if (instructorDetail != null) {

                System.out.println(instructorDetail.getInstructor().getEmail());

                // begin transaction
                session.beginTransaction();

                // remove link for the instructor
                instructorDetail.getInstructor().setInstructorDetail(null);

                // delete record
                session.delete(instructorDetail);

                // commit
                session.getTransaction().commit();
            }

        } catch (Exception e) {

            // close session and session factory
            session.close();
            factory.close();

            e.printStackTrace();
        }
    }

}
