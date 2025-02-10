package ce3.wbc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import ce3.wbc.dto.CommentDto;
import ce3.wbc.entity.Comment;
import ce3.wbc.entity.Restaurant;
import ce3.wbc.entity.User;
import ce3.wbc.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;
    
    public List<CommentDto> getComments(Integer restId) {
    	Restaurant restaurant = restaurantService.getRestaurant(restId);
    	List<Comment> comments = commentRepository.findByRestaurant(restaurant);
				
		for (Comment comment : comments) {
            System.out.println(comment.getCommContent());
        }
		
		List<CommentDto> commentDtos = new ArrayList<>();
		
		for (Comment comment : comments) {
			commentDtos.add(CommentDto.toCommentDto(comment, restaurant)); 
		}
		
		return commentDtos;
		
    }
    
    @Transactional
    public void addComment(CommentDto commentDto) {
    	
    	String commContent = commentDto.getCommContent();
    	String commStar = commentDto.getCommStar();
    	
    	Restaurant restaurant = commentDto.getRestaurant();
    	
    	Integer uId = commentDto.getUserDto().getUId();
    	
    	User user = userService.getUser(uId); 
    	
    	Comment comment = Comment.of(commContent, commStar, restaurant, user);

    	commentRepository.save(comment);
    	
    }
    
    
    // 댓글 수정
    @Transactional
    public CommentDto updateComment(Integer commId, String commContent, String commStar) {
    	Comment comment = commentRepository.findById(commId)
    											.orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commId));;
        // 수정
        comment.update(commId, commContent, commStar);
//        commentRepository.save(comment);
        
        // Restaurant
        Restaurant restaurant = comment.getRestaurant();
        
        return CommentDto.toCommentDto(comment, restaurant);
        
	}

    // 댓글 삭제	
    @Transactional
    public CommentDto deleteComment(Integer commId) {
        // 댓글이 존재하는지 확인
        Comment comment = commentRepository.findById(commId).orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commId));
        Restaurant restaurant = comment.getRestaurant();
        
        commentRepository.deleteById(comment.getCommId()); // 댓글 삭제
        
        CommentDto commentDto = CommentDto.toCommentDto(comment, restaurant);
        return commentDto;
    }

	
    

}
