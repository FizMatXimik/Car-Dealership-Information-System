package ru.gaplikov.CarDealershipInformationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gaplikov.CarDealershipInformationSystem.models.Cmodel;
import ru.gaplikov.CarDealershipInformationSystem.models.Parts;
import ru.gaplikov.CarDealershipInformationSystem.services.CmodelService;
import ru.gaplikov.CarDealershipInformationSystem.services.HomeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {

    private final HomeService homeService;

    private final CmodelService cmodelService;

    @Autowired
    public HomeController(HomeService homeService, CmodelService cmodelService) {
        this.homeService = homeService;
        this.cmodelService = cmodelService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("maxPopularCmodel", homeService.findCmodelMaxSold());
        model.addAttribute("maxSoldManager", homeService.findManagerMaxSold());

        List<Cmodel> cmodels = cmodelService.findAll();
        HashMap<Cmodel, Parts> cmodels_parts = new HashMap<>();
        for (Cmodel cm: cmodels) {
            Parts found_parts = homeService.findPartsMaxPopularForCmodel(cm.getId());
            if (found_parts != null) {
                cmodels_parts.put(cm, found_parts);
            }
        }

        model.addAttribute("partsMaxPopularForCmodel", cmodels_parts);

        return "home/index";
    }
}
