package cau.capstone.repository;

import cau.capstone.domain.Place;
import cau.capstone.domain.Plant;
import cau.capstone.domain.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Recommend where place = :place order by rand() LIMIT 5")
    List<Recommend> findRecommendsByPlace(Place place);

    List<Recommend> findRecommendsByPlant(Plant plant);
//    List<Place> findPlacesByPlant(Plant plant);
}
