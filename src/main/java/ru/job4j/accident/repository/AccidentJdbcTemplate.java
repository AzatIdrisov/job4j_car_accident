package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final RulesJdbc rules;

    public AccidentJdbcTemplate(JdbcTemplate jdbc, RulesJdbc rules) {
        this.jdbc = jdbc;
        this.rules = rules;
    }

    public Accident save(Accident accident) {
        if (accident.getId() == 0) {
            insertAccident(accident);
        } else {
            updateAccident(accident);
        }

        return accident;
    }

    public List<Accident> getAll() {
        List<Rule> allRules = (List<Rule>) this.rules.getAll();
        Map<Integer, Set<Integer>> accidentRules = new HashMap<>();
        List<Accident> result = jdbc.query(
                "select * from accident JOIN accident_type on accident.accident_type_id = accident_type.type_id " +
                        "JOIN accident_rules on accident.id = accident_rules.accident_id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("description"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(AccidentType.of(rs.getInt("type_id"), rs.getString("type_name")));
                    int accidentId = rs.getInt("accident_id");
                    int ruleId = rs.getInt("rule_id");
                    if (accidentRules.containsKey(rs.getInt("id"))) {
                        Set<Integer> rules = accidentRules.get(accidentId);
                        rules.add(ruleId);
                        accidentRules.put(accidentId, rules);
                    } else {
                        Set<Integer> rules = new HashSet<>();
                        rules.add(ruleId);
                        accidentRules.put(accidentId, rules);
                    }
                    return accident;
                });
        for (Accident accident : result) {
            List<Rule> rules = new ArrayList<>();
            for (Integer ruleId : accidentRules.get(accident.getId())) {
                rules.add(allRules.get(ruleId - 1));
            }
            accident.setRules(rules);
        }
        return result.stream().distinct().collect(Collectors.toList());
    }

    public Accident findById(int id) {
        List<Rule> allRules = (List<Rule>) this.rules.getAll();
        Map<Integer, Set<Integer>> accidentRules = new HashMap<>();
        List<Accident> result = jdbc.query(
                "select * from accident JOIN accident_type on accident.accident_type_id = accident_type.type_id " +
                        " JOIN accident_rules on accident.id = accident_rules.accident_id where accident.id = " + id,
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("description"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(AccidentType.of(rs.getInt("type_id"), rs.getString("type_name")));
                    int accidentId = rs.getInt("accident_id");
                    int ruleId = rs.getInt("rule_id");
                    if (accidentRules.containsKey(rs.getInt("id"))) {
                        Set<Integer> rules = accidentRules.get(accidentId);
                        rules.add(ruleId);
                        accidentRules.put(accidentId, rules);
                    } else {
                        Set<Integer> rules = new HashSet<>();
                        rules.add(ruleId);
                        accidentRules.put(accidentId, rules);
                    }
                    return accident;
                });
        for (Accident accident : result) {
            List<Rule> rules = new ArrayList<>();
            for (Integer ruleId : accidentRules.get(accident.getId())) {
                rules.add(allRules.get(ruleId - 1));
            }
            accident.setRules(rules);
        }
        return result.get(0);
    }

    private Accident insertAccident(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accident (name, description, address, accident_type_id) values (?, ?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        Number accidentId = (Number) keyHolder.getKeys().get("id");
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rules (accident_id, rule_id) values (?, ?)",
                    accidentId.intValue(),
                    rule.getId());
        }
        return accident;
    }

    private Accident updateAccident(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "update accident set name = ?, description = ?, address = ?, accident_type_id = ? where id = ?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            ps.setInt(5, accident.getId());
            return ps;
        }, keyHolder);
        Number accidentId = (Number) keyHolder.getKeys().get("id");
        jdbc.update("delete from accident_rules where accident_id = ?",
                accidentId.intValue());
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rules (accident_id, rule_id) values (?, ?)",
                    accidentId.intValue(),
                    rule.getId());
        }
        return accident;
    }
}
