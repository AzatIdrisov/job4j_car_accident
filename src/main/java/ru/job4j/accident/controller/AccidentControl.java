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
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentTypesMem;
import ru.job4j.accident.repository.RulesMem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AccidentControl {

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types",AccidentTypesMem.instOf().getAll());
        model.addAttribute("rules", RulesMem.instOf().getAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        AccidentMem.instOf().create(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", AccidentMem.instOf().findById(id));
        model.addAttribute("types",AccidentTypesMem.instOf().getAll());
        return "accident/update";
    }
}
