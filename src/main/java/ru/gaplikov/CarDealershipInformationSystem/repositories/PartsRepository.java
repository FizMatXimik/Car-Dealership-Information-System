package ru.gaplikov.CarDealershipInformationSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gaplikov.CarDealershipInformationSystem.models.Parts;

@Repository
public interface PartsRepository extends JpaRepository<Parts, Integer> {
}
