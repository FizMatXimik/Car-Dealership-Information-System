package ru.gaplikov.CarDealershipInformationSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gaplikov.CarDealershipInformationSystem.models.Cmodel;
import ru.gaplikov.CarDealershipInformationSystem.models.Manager;
import ru.gaplikov.CarDealershipInformationSystem.models.Parts;
import ru.gaplikov.CarDealershipInformationSystem.repositories.CmodelRepository;
import ru.gaplikov.CarDealershipInformationSystem.repositories.ManagerRepository;
import ru.gaplikov.CarDealershipInformationSystem.repositories.PartsRepository;

@Service
@Transactional(readOnly = true)
public class HomeService {

    private final CmodelRepository cmodelRepository;
    private final ManagerRepository managerRepository;
    private final PartsRepository partsRepository;

    @Autowired
    public HomeService(CmodelRepository cmodelRepository, ManagerRepository managerRepository, PartsRepository partsRepository) {
        this.cmodelRepository = cmodelRepository;
        this.managerRepository = managerRepository;
        this.partsRepository = partsRepository;
    }

    public Cmodel findCmodelMaxSold() {
        return cmodelRepository.findCmodelMaxSold();
    }

    public Manager findManagerMaxSold() {
        return managerRepository.findManagerMaxSold();
    }

    public Parts findPartsMaxPopularForCmodel(int id) {
        return partsRepository.findPartsMaxPopularForCmodel(id);
    }


}
