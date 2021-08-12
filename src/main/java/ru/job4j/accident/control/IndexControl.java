package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("user", "Azat Idrisov");
        List<String> accidents = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            accidents.add("accident " + i);
        }
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
