package ce3.wbc.repository;


import ce3.wbc.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChefRepository extends JpaRepository<Chef, Integer> {

}