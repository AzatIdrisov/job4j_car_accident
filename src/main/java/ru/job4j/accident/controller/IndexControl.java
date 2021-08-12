package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("user", "Azat Idrisov");
        model.addAttribute("accidents", new AccidentMem().getAccidents());
        return "index";
    }
}
