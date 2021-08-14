package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentTypeJdbc;
import ru.job4j.accident.repository.RulesJdbc;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate accidents;
    private final AccidentTypeJdbc types;
    private final RulesJdbc rules;


    public AccidentService(AccidentJdbcTemplate accidents, AccidentTypeJdbc types, RulesJdbc rules) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    public Collection<AccidentType> getALlTypes() {
        return types.getAll();
    }

    public Collection<Rule> getAllRules() {
        return rules.getAll();
    }

    @Transactional
    public void addAccident(Accident accident) {
        accidents.save(accident);
    }

    public void addTypeToAccident(Accident acc) {
        acc.setType(types.getById(acc.getType().getId()));
    }

    public void setRulesForAccident(Accident accident, String[] ruleIds) {
        for (String idStr: ruleIds) {
            accident.addRule(rules.getById(Integer.parseInt(idStr)));
        }
    }

    public Accident getAccidentById(int id) {
        return accidents.findById(id);
    }
}
