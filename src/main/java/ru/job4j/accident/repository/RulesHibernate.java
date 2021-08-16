package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class RulesHibernate {

    private final SessionFactory sf;

    public RulesHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Rule> getAll() {
        try (Session session = sf.openSession()) {
            return  session.createQuery("from Rule", Rule.class)
                    .list();
        }
    }
    public Rule getById(int id){
        try (Session session = sf.openSession()) {
            return session.get(Rule.class, id);
        }
    }
}
