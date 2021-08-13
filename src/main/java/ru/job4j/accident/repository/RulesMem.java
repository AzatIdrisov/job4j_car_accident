package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RulesMem {
    private final Map<Integer, Rule> rules = new HashMap<>();

    public RulesMem() {
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
    }

    public Collection<Rule> getAll() {
        return rules.values();
    }

    public Rule getById(int id) {
        return rules.get(id);
    }
}
