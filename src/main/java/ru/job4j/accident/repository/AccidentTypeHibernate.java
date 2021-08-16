package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

//@Repository
public class AccidentTypeHibernate {

    private final SessionFactory sf;

    public AccidentTypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<AccidentType> getAll() {
        try (Session session = sf.openSession()) {
            return  session.createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }
    public AccidentType getById(int id){
        try (Session session = sf.openSession()) {
            Query query = session.createQuery("from AccidentType  where  AccidentType.id =:id", AccidentType.class);
            query.setParameter("id", id);
            return (AccidentType) query.uniqueResult();
        }
    }
}
