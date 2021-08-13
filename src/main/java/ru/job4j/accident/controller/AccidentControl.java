package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentTypesMem;
import ru.job4j.accident.repository.RulesMem;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {

    private final AccidentJdbcTemplate accidents;

    public AccidentControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types",new AccidentTypesMem().getAll());
        model.addAttribute("rules", new RulesMem().getAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> rules = new HashSet<>();
        for(String ruleId: ids){
            rules.add(new RulesMem().getById(Integer.parseInt(ruleId)));
        }
        accident.setRules(rules);
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", AccidentMem.instOf().findById(id));
        model.addAttribute("types",new AccidentTypesMem().getAll());
        model.addAttribute("rules", new RulesMem().getAll());
        return "accident/update";
    }
}
