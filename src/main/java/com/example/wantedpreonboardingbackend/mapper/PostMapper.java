package com.example.wantedpreonboardingbackend.mapper;

import com.example.wantedpreonboardingbackend.dto.PostingPostDto;
import com.example.wantedpreonboardingbackend.dto.PostingResponseDto;
import com.example.wantedpreonboardingbackend.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post PostingPostDtoTopost(Long userId,PostingPostDto postingPostDto) {
        Post post = new Post();

        post.updateUserId(userId);
        post.updateTitle(postingPostDto.getTitle());
        post.updateContent(postingPostDto.getContent());
        return post;
    }
    public PostingResponseDto postToPostingResponseDto(String name,Post post){
        PostingResponseDto postingResponseDto = new PostingResponseDto
                (name,post.getTitle(), post.getContent(), post.getCreated_At());
        return postingResponseDto;
    }


}
