package cau.capstone.repository;

import cau.capstone.domain.Plant;
import cau.capstone.dto.place.PlacePlantInfoResponse;
import cau.capstone.dto.place.PlacePlantResponse.PlantResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Plant order by rand() LIMIT 5")
    List<PlantResponse> findPlantByPlace(String place);

    PlacePlantInfoResponse findPlantById(Long id);


}
