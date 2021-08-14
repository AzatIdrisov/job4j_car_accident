package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
public class RulesJdbc {
    private final JdbcTemplate jdbc;

    public RulesJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Rule> getAll() {
        return jdbc.query(
                "select * from rule order by rule.rule_id",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("rule_id"));
                    rule.setName(rs.getString("rule_name"));
                    return rule;
                });
    }

    public Rule getById(int id) {
        return jdbc.query(
                "select * from rule where rule.rule_id = " + id,
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("rule_id"));
                    rule.setName(rs.getString("rule_name"));
                    return rule;
                }).get(0);
    }
}
