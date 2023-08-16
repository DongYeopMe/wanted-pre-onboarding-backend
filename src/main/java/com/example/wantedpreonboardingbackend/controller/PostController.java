package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.PostingPageResponseDto;
import com.example.wantedpreonboardingbackend.dto.PostingPatchDto;
import com.example.wantedpreonboardingbackend.dto.PostingPostDto;
import com.example.wantedpreonboardingbackend.dto.PostingResponseDto;
import com.example.wantedpreonboardingbackend.service.PostService;
import com.example.wantedpreonboardingbackend.utils.dto.MultiResponseDto;
import com.example.wantedpreonboardingbackend.utils.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("Posting")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> postPosting(@RequestBody PostingPostDto postingPostDto){
        String message = postService.createPosting(postingPostDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/find/{postId}")
    public ResponseEntity<SingleResponseDto<PostingResponseDto>> getPosting(@PathVariable("postId") Long postId
                                                                            ){
        SingleResponseDto<PostingResponseDto> response = postService.getPosting(postId);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/update/{postId}")
    public ResponseEntity<String> patchPosting(@PathVariable("postId") Long postId,@RequestBody PostingPatchDto postingPatchDto){
        String message = postService.updatePosting(postId,postingPatchDto);

        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @GetMapping("/find-all")
    public ResponseEntity<MultiResponseDto<PostingResponseDto>> getPostingPage(
        @RequestParam int page,
        @RequestParam int size){
        MultiResponseDto<PostingResponseDto> response = postService.getPostingPage(page-1,size);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePosting(@PathVariable("postId") Long postId){
        String message = postService.deletePost(postId);

        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
