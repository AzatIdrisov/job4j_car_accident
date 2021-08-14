package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Set;

//@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return  session.createQuery("from Accident", Accident.class)
                    .list();
        }
    }
    public Accident findById(int id){
        try (Session session = sf.openSession()) {
            Query query = session.createQuery("from Accident  where  Accident.id =:id", Accident.class);
            query.setParameter("id", id);
            return (Accident) query.uniqueResult();
        }
    }
}
