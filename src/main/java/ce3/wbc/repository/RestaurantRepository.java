package ce3.wbc.repository;

import ce3.wbc.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>, JpaSpecificationExecutor<Restaurant> {
    Page<Restaurant> findRestaurantByChef_ChefName(String chef, Pageable pageable);



}
