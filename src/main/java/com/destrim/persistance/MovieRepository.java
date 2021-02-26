package com.destrim.persistance;

import com.destrim.movie.representation.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
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

    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Movie movie = session.get(Movie.class, id);
        session.delete(movie);

        tx.commit();
        session.close();
    }

    public Movie getById(long id) {
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
