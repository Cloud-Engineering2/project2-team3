package ce3.wbc.repository;


import ce3.wbc.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChefRepository extends JpaRepository<Chef, Integer> {


    boolean existsByChefName(String chef);

    Optional<Chef> findByChefName(String chefName);
}

