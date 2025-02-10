package ce3.wbc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonProperty;

import ce3.wbc.controller.rto.request.CommentReq;
import ce3.wbc.controller.rto.response.CommentRes;
import ce3.wbc.dto.CommentDto;
import ce3.wbc.dto.UserDto;
import ce3.wbc.entity.Comment;
import ce3.wbc.entity.Restaurant;
import ce3.wbc.entity.User;
import ce3.wbc.repository.RestaurantRepository;
import ce3.wbc.service.CommentService;
import ce3.wbc.service.RestaurantService;
import ce3.wbc.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
class CommentListReq {
    @NotNull
    @JsonProperty("restId")
    private Integer restId;
}

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
class CommentUpdateReq {
	@NotNull
    private String commContent; 

    @NotNull
    private String commStar;
}

@Controller
@RequestMapping("replies")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final RestaurantService restaurantService;
    private final UserService userService;
    
    

    
    /************************************************** 댓글 등록 **************************************************/
    @PostMapping // localhost:8080/replies
    public ResponseEntity<Void> registerComment(@RequestBody @Valid CommentReq commentReq, BindingResult bindingResult) {

		// 요청 유효성 검사
		if (bindingResult.hasErrors()) { 
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400                                 
		}
		
		// 댓글과 별점
	    String content = commentReq.getCommContent();
	    String star = commentReq.getCommStar();
        

        	// 작성자 401에러?? : HttpStatus.UNAUTHORIZED
    	    Integer uId = commentReq.getUId();
    	    
    	    User user = userService.getUser(uId);
    	    System.out.println(user.getUserName()); // ce3.wbc.entity.User@6eef4a21
    	    UserDto userDto = UserDto.toDto(user);
    	    System.out.println(userDto.getUserName()); //ce3.wbc.dto.UserDto@26d3f8dd

    	    // 식당
            Integer restId = commentReq.getRestId();
            
            // 댓글 생성
            CommentDto commentDto = CommentDto.of(content, star, restId, userDto);
            System.out.println(commentDto.getUserDto().getUId());
            
        	// DB에 저장
        	commentService.addComment(commentDto);
        	return ResponseEntity.ok().build(); // 200

        
    }
    
    
    
    /******************************************* 댓글 조회: restaurant id *******************************************/
    @GetMapping // localhost:8080/replies?restaurant=3
    public ResponseEntity<List<CommentRes>> getComments(@RequestParam("restaurant") Integer restId) {        
        
        /* 로그
        for (CommentDto comment : comments) {
            System.out.println(comment.getCommContent());
        }
        */       
        
        
//        try {
        	List<CommentDto> comments = commentService.getComments(restId);
        	
        	List<CommentRes> response = comments.stream()
                    .map(CommentRes::toResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(response); // 200 
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
//        } catch (NumberFormatException e) {
//        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
//        } catch (Exception e) {
//        	return ResponseEntity.internalServerError().build(); // 500
//        }
//        
        
        
        
    }
    
    
    
    /********************************************* 댓글 수정: comment id *********************************************/
    @PutMapping("/{commId}") // localhost:8080/replies/52
    public ResponseEntity<CommentRes> updateComment(@PathVariable("commId") Integer commId,
                                                               @RequestBody @Valid CommentUpdateReq commentUpdateReq, BindingResult bindingResult) {
    	// 요청 유효성 검사
    	if (bindingResult.hasErrors()) {   
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400                                 
	    }
    	
//        try {
        	CommentDto commentDto = commentService.updateComment(commId, commentUpdateReq.getCommContent(), commentUpdateReq.getCommStar());
            CommentRes response = CommentRes.toResponse(commentDto);
            
            return ResponseEntity.ok(response); // 200 
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
//        } catch (NumberFormatException e) {
//        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
//        } catch (Exception e) {
//        	return ResponseEntity.internalServerError().build(); // 500
//        }
    }

    
    
    
    /********************************************* 댓글 삭제: comment id *********************************************/
    @DeleteMapping("/{commId}") // localhost:8080/replies/39
    public ResponseEntity<Void> deleteComment(@PathVariable("commId") Integer commId) {
    	
//        try {
//            CommentDto commentDto = commentService.deleteComment(commId);
            Boolean isDeleted = commentService.deleteComment(commId);
            return ResponseEntity.noContent().build();
            
            
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
//        } catch (NumberFormatException e) {
//        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
//        } catch (Exception e) {
//        	return ResponseEntity.internalServerError().build(); // 500
//        }
    }
    

}
