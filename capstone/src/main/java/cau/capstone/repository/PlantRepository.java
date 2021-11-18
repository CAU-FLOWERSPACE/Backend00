package cau.capstone.repository;

import cau.capstone.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    Optional<Plant> findById(Long plantId);
}
