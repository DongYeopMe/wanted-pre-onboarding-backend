package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.PostingPatchDto;
import com.example.wantedpreonboardingbackend.dto.PostingPostDto;
import com.example.wantedpreonboardingbackend.dto.PostingResponseDto;
import com.example.wantedpreonboardingbackend.entity.Member;
import com.example.wantedpreonboardingbackend.entity.Post;
import com.example.wantedpreonboardingbackend.mapper.PostMapper;
import com.example.wantedpreonboardingbackend.repository.PostRepository;
import com.example.wantedpreonboardingbackend.utils.dto.MultiResponseDto;
import com.example.wantedpreonboardingbackend.utils.dto.SingleResponseDto;
import com.example.wantedpreonboardingbackend.utils.exception.BusinessLogicException;
import com.example.wantedpreonboardingbackend.utils.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final PostMapper postMapper;
    public String createPosting(PostingPostDto postingPostDto){
        Member member = memberService.getLoginMember();
        Post post = postMapper.PostingPostDtoTopost(member.getId(),postingPostDto);
        postRepository.save(post);
        return "작성 완료";
    }
    public String updatePosting(Long postId, PostingPatchDto postingPatchDto){
        //게시글 존재 확인
        Post post = verifyIdExistPost(postId);
        //유저 매치 확인
        MemberMatch(post);
        post.updateContent(postingPatchDto.getContent());
        post.updateTitle(postingPatchDto.getTitle());

        postRepository.save(post);

        return "수정 완료";
    }


    public String deletePost(Long postId){
        Post post = verifyIdExistPost(postId);
        //유저 매치 확인
        MemberMatch(post);
        postRepository.delete(post);

        return "삭제 완료";
    }
    public SingleResponseDto<PostingResponseDto> getPosting(Long postId){
        Post post = verifyIdExistPost(postId);
        String name = memberService.findMemberName(post.getUserId());
        PostingResponseDto responseDto = postMapper.postToPostingResponseDto(name,post);
        SingleResponseDto<PostingResponseDto> response = new SingleResponseDto<PostingResponseDto>(responseDto);
        return response;
    }

    public MultiResponseDto<PostingResponseDto> getPostingPage(int page, int size){
        Page<Post> postPage = postRepository.findAllPostPage(PageRequest.of(page,size, Sort.by("id").descending()));
        List<Post> postList =postPage.getContent();
        List<PostingResponseDto> postingResponseDtoList =new ArrayList<>();
        for(Post post : postList){
            String username = memberService.findMemberName(post.getUserId());
            PostingResponseDto responseDto = postMapper.postToPostingResponseDto(username,post);
            postingResponseDtoList.add(responseDto);
        }
        MultiResponseDto<PostingResponseDto> response = new MultiResponseDto<>(postingResponseDtoList,postPage);
        return response;
    }

    private void MemberMatch(Post post) {
        Member member = memberService.getLoginMember();
        if(member.getId() != post.getUserId()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_MATCH);
        }
    }

    private Post verifyIdExistPost(Long postId) {
        Optional<Post> findPost = postRepository.findById(postId);
        findPost.orElseThrow(() -> new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));
        return findPost.get();
    }



}
