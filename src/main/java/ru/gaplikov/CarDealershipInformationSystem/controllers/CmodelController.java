package ru.gaplikov.CarDealershipInformationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gaplikov.CarDealershipInformationSystem.services.CmodelService;

@Controller
@RequestMapping("/car_models")
public class CmodelController {

    private final CmodelService cmodelService;

    @Autowired
    public CmodelController(CmodelService cmodelService) {
        this.cmodelService = cmodelService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cmodels", cmodelService.findAll());
        return "carModel/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("carModel", cmodelService.findOne(id));
        return "carModel/show";
    }
}
