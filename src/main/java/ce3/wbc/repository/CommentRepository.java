package ce3.wbc.repository;

import ce3.wbc.entity.Comment;
import ce3.wbc.entity.Restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByRestaurant(Restaurant restaurant);
}
