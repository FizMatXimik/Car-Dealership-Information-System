package ru.gaplikov.CarDealershipInformationSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gaplikov.CarDealershipInformationSystem.models.Cmodel;

@Repository
public interface CmodelRepository extends JpaRepository<Cmodel, Integer> {
}
