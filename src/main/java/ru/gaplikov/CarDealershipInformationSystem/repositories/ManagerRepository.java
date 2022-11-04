package ru.gaplikov.CarDealershipInformationSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gaplikov.CarDealershipInformationSystem.models.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    @Query(
            value = "SELECT m.* FROM manager m\n" +
                    "    order by M.number_sold_cars DESC\n" +
                    "    LIMIT 1;",
            nativeQuery = true)
    Manager findManagerMaxSold();

}
