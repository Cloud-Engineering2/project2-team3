package ce3.wbc.repository;

import ce3.wbc.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>, JpaSpecificationExecutor<Restaurant> {
    @Query("SELECT r FROM Restaurant r JOIN r.chef c WHERE c.id = :chefId")
    Page<Restaurant> findRestaurantByChefId(@Param("chefId") int chefId, Pageable pageable);




}
