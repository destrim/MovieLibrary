package com.destrim.persistance;

import com.destrim.movie.representation.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class MovieRepository {

    private final SessionFactory sessionFactory;

    public MovieRepository() {
        sessionFactory = new HibernateFactory().getSessionFactory();
    }

    public void save(ArrayList<Movie> movies) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        for (Movie m : movies) {
            session.save(m);
        }

        tx.commit();
        session.close();
    }
}
