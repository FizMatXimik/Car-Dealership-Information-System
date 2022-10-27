package ru.gaplikov.CarDealershipInformationSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gaplikov.CarDealershipInformationSystem.models.Cmodel;
import ru.gaplikov.CarDealershipInformationSystem.repositories.CmodelRepository;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class CmodelService {

    private final CmodelRepository cmodelRepository;

    @Autowired
    public CmodelService(CmodelRepository cmodelRepository) {
        this.cmodelRepository = cmodelRepository;
    }

    public List<Cmodel> findAll() {
        return cmodelRepository.findAll();
    }
}
