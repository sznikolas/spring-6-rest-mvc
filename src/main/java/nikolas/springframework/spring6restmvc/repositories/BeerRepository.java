package nikolas.springframework.spring6restmvc.repositories;

import nikolas.springframework.spring6restmvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
