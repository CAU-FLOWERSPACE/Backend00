package cau.capstone.repository;

import cau.capstone.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlaceRepository extends JpaRepository<Place, String> {

    Place findByPlace(String place);
}
