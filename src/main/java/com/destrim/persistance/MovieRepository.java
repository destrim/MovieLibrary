package com.destrim.persistance;

import com.destrim.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MovieRepository {

    private final SessionFactory sessionFactory;

    public MovieRepository() {
        sessionFactory = new HibernateFactory().getSessionFactory();
    }

    public void add(Movie movie) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(movie);

        tx.commit();
        session.close();
    }

    public void delete(Movie movie) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.delete(movie);

        tx.commit();
        session.close();
    }

    public Movie get(long id) {
        Movie movie;

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        movie = session.get(Movie.class, id);

        tx.commit();
        session.close();

        return movie;
    }

    public List<Movie> getAll() {
        List<Movie> movies;

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        // TODO fix warning
        movies = session.createQuery("from Movie").list();

        tx.commit();
        session.close();

        return movies;
    }

    public void addAll(List<Movie> movies) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        for (Movie m : movies) {
            session.save(m);
        }

        tx.commit();
        session.close();
    }
}
