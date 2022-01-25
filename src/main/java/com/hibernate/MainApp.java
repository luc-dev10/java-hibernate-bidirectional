package com.hibernate;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Session
        Session session = factory.openSession();

        try {
            
            // instructor
            Instructor instructor = new Instructor();
            instructor.setFirstName("Lucio");
            instructor.setLastName("Zhao");
            instructor.setEmail("lucio.zhao@email.com");

            // instructor details
            InstructorDetail instructorDetail = new InstructorDetail();
            instructorDetail.setHobby("coding");
            instructorDetail.setYoutubeChannel("lucio zhao");

            // set in memory
            instructor.setInstructorDetail(instructorDetail);

            // start transaction
            session.beginTransaction();

            // save instructor
            session.save(instructor);

            // commit transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
