package ru.gaplikov.CarDealershipInformationSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gaplikov.CarDealershipInformationSystem.models.Parts;
import ru.gaplikov.CarDealershipInformationSystem.repositories.PartsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PartsService {

    private final PartsRepository partsRepository;

    @Autowired
    public PartsService(PartsRepository partsRepository) {
        this.partsRepository = partsRepository;
    }

    public List<Parts> findAll() {
        return partsRepository.findAll();
    }
}
