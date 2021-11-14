package cau.capstone.repository;

import cau.capstone.domain.Flower;
import cau.capstone.dto.color.ColorClassification;
import cau.capstone.dto.place.PlaceFlowerResponse.FlowerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface FlowerRepository extends JpaRepository<Flower, Long> {

  @Query(nativeQuery = true, value = "SELECT * FROM Flower f WHERE f.color = :color order by rand() LIMIT 5")
  List<Flower> findByColor(@Param("color") String color);

  Optional<Flower> findById(Long flower_id);

  @Query(nativeQuery = true, value = "SELECT * FROM Flower where color = :color and s >= :s order by rand() LIMIT 2")
  List<FlowerResponse> findByColorAndSGreaterThanEqual(ColorClassification color, int s);  // 공간이 진한 경우 채도 높은 꽃

  @Query(nativeQuery = true, value = "SELECT * FROM Flower where color = :color and s < :s order by rand() LIMIT 2")
  List<FlowerResponse> findByColorAndSLessThan(ColorClassification color, int s);  // 공간이 진한 경우 채도 낮은 꽃

  @Query(nativeQuery = true, value = "SELECT * FROM Flower where color = :color order by rand() LIMIT 2")
  List<FlowerResponse> findByColor(ColorClassification color);
}
