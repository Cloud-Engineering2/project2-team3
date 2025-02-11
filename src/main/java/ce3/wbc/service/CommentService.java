package ce3.wbc.service;

import java.util.ArrayList;
import java.util.List;
import ce3.wbc.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import ce3.wbc.dto.CommentDto;
import ce3.wbc.entity.Comment;
import ce3.wbc.entity.Restaurant;
import ce3.wbc.entity.User;
import ce3.wbc.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;

    public List<CommentDto> getComments(Integer restId) {
    	Restaurant restaurant = restaurantRepository.findById(restId)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 레스토랑입니다."));
    	List<Comment> comments = commentRepository.findByRestaurant(restaurant);
				
		List<CommentDto> commentDtos = new ArrayList<>();
		
		for (Comment comment : comments) {
			commentDtos.add(CommentDto.toCommentDto(comment)); 
		}
		return commentDtos;
    }
    
    @Transactional
    public void addComment(CommentDto commentDto) {
    	User user = userService.getUser(commentDto.getUserDto().getUId());
    	Integer restId = commentDto.getRestId();
        Restaurant restaurant = restaurantRepository.findById(restId)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 레스토랑입니다."));
    	
    	Comment comment = CommentDto.toEntity(commentDto, restaurant, user);
    	commentRepository.save(comment);
    	
    }

    // 댓글 수정
    @Transactional
    public CommentDto updateComment(Integer commId, String commContent, String commStar) {
    	Comment comment = commentRepository.findById(commId)
    											.orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commId));;
        // 수정
        comment.update(commId, commContent, commStar);
        
        // Restaurant
        Restaurant restaurant = comment.getRestaurant();
        
        return CommentDto.toCommentDto(comment);
        
	}

    // 댓글 삭제	
    @Transactional
    public void deleteComment(Integer commId) {
        Comment comment = commentRepository.findById(commId).orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commId));
        
        commentRepository.deleteById(comment.getCommId()); 
    }
}
