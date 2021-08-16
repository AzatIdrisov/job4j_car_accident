package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentRepository accidents;
    private final AccidentTypeRepository types;
    private final RuleRepository rules;


    public AccidentService(AccidentRepository accidents, AccidentTypeRepository types, RuleRepository rules) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    @Transactional
    public Collection<AccidentType> getALlTypes() {
        List<AccidentType> result = new ArrayList<>();
        types.findAll().forEach(result::add);
        return result;
    }

    @Transactional
    public Collection<Rule> getAllRules() {
        List<Rule> result = new ArrayList<>();
        rules.findAll().forEach(result::add);
        return result;
    }

    @Transactional
    public void addAccident(Accident accident) {
        accidents.save(accident);
    }

    @Transactional
    public void addTypeToAccident(Accident accident) {
        accident.setType(types.findById(accident.getType().getId()).orElse(null));
    }

    @Transactional
    public void setRulesForAccident(Accident accident, String[] ruleIds) {
        for (String idStr: ruleIds) {
            accident.addRule(rules.findById(Integer.parseInt(idStr)).orElse(null));
        }
    }

    @Transactional
    public Accident getAccidentById(int id) {
        return accidents.findById(id).orElse(null);
    }
}
