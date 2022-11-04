package ru.gaplikov.CarDealershipInformationSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gaplikov.CarDealershipInformationSystem.models.Cmodel;

@Repository
public interface CmodelRepository extends JpaRepository<Cmodel, Integer> {

    @Query(
            value = "SELECT cm.* FROM cmodel cm\n" +
                    "JOIN (\n" +
                    "    SELECT cm.id as id, count(c.sold) as sold_number\n" +
                    "          FROM cmodel cm\n" +
                    "            JOIN car c on cm.id = c.cmodel_id\n" +
                    "          WHERE sold = true\n" +
                    "          GROUP BY cm.id) t on cm.id = t.id\n" +
                    "ORDER BY t.sold_number desc\n" +
                    "limit 1;",
            nativeQuery = true)
    Cmodel findCmodelMaxSold();
}
