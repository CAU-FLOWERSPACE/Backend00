package cau.capstone.repository;

import cau.capstone.domain.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface FlowerRepository extends JpaRepository<Flower, Long> {

  @Query(nativeQuery = true, value = "SELECT * FROM Flower order by rand() LIMIT 5")
  List<Flower> findByColor(String color);

  Optional<Flower> findById(Long flower_id);
}
