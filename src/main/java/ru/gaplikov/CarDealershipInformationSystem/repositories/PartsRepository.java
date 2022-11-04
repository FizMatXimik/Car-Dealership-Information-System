package ru.gaplikov.CarDealershipInformationSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gaplikov.CarDealershipInformationSystem.models.Manager;
import ru.gaplikov.CarDealershipInformationSystem.models.Parts;

@Repository
public interface PartsRepository extends JpaRepository<Parts, Integer> {

    @Query(
            value = "SELECT p.* FROM parts p\n" +
                    "    JOIN (\n" +
                    "                    SELECT p.id, count(*) as count_of_cars\n" +
                    "    FROM car c\n" +
                    "    JOIN car_parts cp on c.id = cp.car_id\n" +
                    "    JOIN parts p on p.id = cp.parts_id\n" +
                    "    WHERE c.cmodel_id = ?1\n" +
                    "    GROUP BY p.id) t on t.id = p.id\n" +
                    "    ORDER BY count_of_cars desc\n" +
                    "    limit 1;",
            nativeQuery = true)
    Parts findPartsMaxPopularForCmodel(int id);
}
