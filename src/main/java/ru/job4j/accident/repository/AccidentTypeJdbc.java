package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

//@Repository
public class AccidentTypeJdbc {
    private final JdbcTemplate jdbc;

    public AccidentTypeJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<AccidentType> getAll() {
        return jdbc.query(
                "select * from accident_type",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("type_id"));
                    type.setName(rs.getString("type_name"));
                    return type;
                });
    }

    public AccidentType getById(int id) {
        return jdbc.query(
                "select * from accident_type where accident_type.type_id = " + id,
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("type_id"));
                    type.setName(rs.getString("type_name"));
                    return type;
                }).get(0);
    }
}
