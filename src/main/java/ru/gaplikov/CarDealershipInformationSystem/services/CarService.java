package ru.gaplikov.CarDealershipInformationSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gaplikov.CarDealershipInformationSystem.models.Car;
import ru.gaplikov.CarDealershipInformationSystem.models.Parts;
import ru.gaplikov.CarDealershipInformationSystem.repositories.CarRepository;
import ru.gaplikov.CarDealershipInformationSystem.repositories.PartsRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;
    private final PartsRepository partsRepository;

    @Autowired
    public CarService(CarRepository carRepository, PartsRepository partsRepository) {
        this.carRepository = carRepository;
        this.partsRepository = partsRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findOne(int id) {
        Optional<Car> foundCar = carRepository.findById(id);
        return foundCar.orElse(null);
    }

    @Transactional
    public void save(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void update(int id, Car updatedCar) {
        updatedCar.setId(id);
        Optional<Car> foundCar = carRepository.findById(id);
        if(foundCar.isPresent()) {
            updatedCar.setParts(foundCar.get().getParts());
        }
        carRepository.save(updatedCar);
    }

    @Transactional
    public void delete(int id) {
        carRepository.deleteById(id);
    }

    @Transactional
    public void updateParts(int id, int part_id, boolean add) {
        Optional<Car> foundCar = carRepository.findById(id);
        Optional<Parts> foundParts = partsRepository.findById(part_id);
        if(foundCar.isPresent()) {
            List<Parts> parts_list = foundCar.get().getParts();
            if(foundParts.isPresent()) {
                if(add) {
                    parts_list.add(foundParts.get());
                } else {
                    parts_list.remove(foundParts.get());
                }
            }
            foundCar.get().setParts(parts_list);
            save(foundCar.get());
        }
    }
}
