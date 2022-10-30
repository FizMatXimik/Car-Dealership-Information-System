package ru.gaplikov.CarDealershipInformationSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gaplikov.CarDealershipInformationSystem.models.Cmodel;
import ru.gaplikov.CarDealershipInformationSystem.repositories.CmodelRepository;

import java.util.List;
import java.util.Optional;

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

    public Cmodel findOne(int id) {
        Optional<Cmodel> foundCmodel = cmodelRepository.findById(id);
        return foundCmodel.orElse(null);
    }

    @Transactional
    public void save(Cmodel cmodel) {
        cmodelRepository.save(cmodel);
    }

    @Transactional
    public void update(int id, Cmodel updatedCmodel) {
        updatedCmodel.setId(id);
        cmodelRepository.save(updatedCmodel);
    }

    @Transactional
    public void delete(int id) {
        cmodelRepository.deleteById(id);
    }
}
