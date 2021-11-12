package cau.capstone.repository;

import cau.capstone.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Plant order by rand() LIMIT 5")
    List<Plant> findPlantByPlace(String place);
}
