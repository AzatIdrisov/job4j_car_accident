package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {

    private final AccidentHibernate accidents;
    private final AccidentTypesMem types;
    private final RulesMem rules;

    public AccidentControl(AccidentHibernate accidents, AccidentTypesMem types, RulesMem rules) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types",types.getAll());
        model.addAttribute("rules", rules.getAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> ruleSet = new HashSet<>();
        for(String ruleId: ids){
            ruleSet.add(rules.getById(Integer.parseInt(ruleId)));
        }
        accident.setRules(ruleSet);
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types",types.getAll());
        model.addAttribute("rules", rules.getAll());
        return "accident/update";
    }
}
