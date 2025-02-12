package ce3.wbc.controller;

import java.util.List;
import java.util.stream.Collectors;

import ce3.wbc.security.WbcUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import ce3.wbc.controller.rto.request.CommentReq;
import ce3.wbc.controller.rto.request.CommentUpdateReq;
import ce3.wbc.controller.rto.request.CommentListReq;
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


@Controller
@RequestMapping("replies")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final RestaurantService restaurantService;
    private final UserService userService;
    
    

    
    /************************************************** 댓글 등록 **************************************************/
    @PostMapping 
    public String registerComment(@RequestBody @Valid CommentReq commentReq,
                                                BindingResult bindingResult,
                                                @AuthenticationPrincipal WbcUserDetails wbcUser) {
		// 요청 유효성 검사
//		if (bindingResult.hasErrors()) {
//	        return "400"; // 400
//		}
		
		// 댓글과 별점
	    String content = commentReq.getCommContent();
	    String star = commentReq.getCommStar();
        

    	// 작성자 401에러?? : HttpStatus.UNAUTHORIZED
//	    User user = userService.getUser(uId);
	    UserDto userDto = UserDto.toDto(wbcUser.getUser());

	    // 식당
        Integer restId = commentReq.getRestId();
        
        // 댓글 생성
        CommentDto commentDto = CommentDto.of(content, star, restId, userDto);
        
    	// DB에 저장
        System.out.println("restID: "+ restId);
    	commentService.addComment(commentReq, userDto);
        return "redirect:/restaurants/"+restId;
    }
    
    
    
    /******************************************* 댓글 조회: restaurant id *******************************************/
    @GetMapping 
    public ResponseEntity<List<CommentRes>> getComments(@RequestParam("restaurant") Integer restId) {        
        
        List<CommentDto> comments = commentService.getComments(restId);
        
        List<CommentRes> response = comments.stream()
                .map(CommentRes::toResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(response); // 200 
        
        
        
    }
    
    
    
    /********************************************* 댓글 수정: comment id *********************************************/
    @PutMapping("/{commId}") 
    public ResponseEntity<CommentRes> updateComment(@PathVariable("commId") Integer commId,
                                                               @RequestBody @Valid CommentUpdateReq commentUpdateReq, BindingResult bindingResult) {
    	// 요청 유효성 검사
    	if (bindingResult.hasErrors()) {   
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400                                 
	    }
    	
        CommentDto commentDto = commentService.updateComment(commId, commentUpdateReq.getCommContent(), commentUpdateReq.getCommStar());
        CommentRes response = CommentRes.toResponse(commentDto);
        
        return ResponseEntity.ok(response); // 200 
    }

    
    
    
    /********************************************* 댓글 삭제: comment id *********************************************/
    @DeleteMapping("/{commId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commId") Integer commId) {

            commentService.deleteComment(commId);
            return ResponseEntity.noContent().build();
            
    }
    

}
