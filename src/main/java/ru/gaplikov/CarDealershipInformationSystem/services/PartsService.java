package ru.gaplikov.CarDealershipInformationSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gaplikov.CarDealershipInformationSystem.models.Parts;
import ru.gaplikov.CarDealershipInformationSystem.repositories.PartsRepository;

import java.util.List;
import java.util.Optional;

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

    public Parts findOne(int id) {
        Optional<Parts> foundParts = partsRepository.findById(id);
        return foundParts.orElse(null);
    }

    @Transactional
    public void save(Parts parts) {
        partsRepository.save(parts);
    }

    @Transactional
    public void update(int id, Parts updatedParts) {
        updatedParts.setId(id);
        partsRepository.save(updatedParts);
    }

    @Transactional
    public void delete(int id) {
        partsRepository.deleteById(id);
    }
}
