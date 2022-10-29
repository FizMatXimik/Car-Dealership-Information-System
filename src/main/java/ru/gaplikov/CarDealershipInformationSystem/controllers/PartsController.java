package ru.gaplikov.CarDealershipInformationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gaplikov.CarDealershipInformationSystem.services.PartsService;

@Controller
@RequestMapping("/parts")
public class PartsController {

    private final PartsService partsService;

    @Autowired
    public PartsController(PartsService partsService) {
        this.partsService = partsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("parts", partsService.findAll());
        return "parts/index";
    }
}
