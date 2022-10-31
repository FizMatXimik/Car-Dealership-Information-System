package ru.gaplikov.CarDealershipInformationSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gaplikov.CarDealershipInformationSystem.models.Manager;
import ru.gaplikov.CarDealershipInformationSystem.repositories.ManagerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ManagerService {

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    public Manager findOne(int id) {
        Optional<Manager> foundManager = managerRepository.findById(id);
        return foundManager.orElse(null);
    }

    @Transactional
    public void save(Manager manager) {
        managerRepository.save(manager);
    }

    @Transactional
    public void update(int id, Manager updatedManager) {
        updatedManager.setId(id);
        managerRepository.save(updatedManager);
    }

    @Transactional
    public void delete(int id) {
        managerRepository.deleteById(id);
    }
}
