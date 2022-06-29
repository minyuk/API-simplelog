package com.simplelog.controller;

import com.simplelog.domain.Post;
import com.simplelog.exception.InvalidRequest;
import com.simplelog.request.PostCreate;
import com.simplelog.request.PostEdit;
import com.simplelog.request.PostSearch;
import com.simplelog.response.PostResponse;
import com.simplelog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // SSR -> jsp, thymeleaf, freemarker
    // SPA -> javascript + <-> API (JSON)
    // veu -> vue + SSR = nuxt.js
    // react -> react + SSR = next.js

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) {
        // case1. 저장한 데이터 Entity -> response로 응답하기 -> return Post
        // case2. 저장한 데이터의 primary_id -> response로 응답하기 -> return id
        //          client에서는 수신한 id를 post 조회 API를 통해서 글 데이터를 수신 받음
        // case3. 응답 필요 없음 -> 클라이언트에서 모든 POST 데이터 context를 잘 관리함 -> void
        // Bad Case: 서버에서 -> 반드시 이렇게 할껍니다! fix
        //      -> 서버에서 차라리 유연하게 대응하는게 좋다.
        //      -> 한 번에 일괄적으로 잘 처리되는 케이스가 없음 -> 잘 관리하는 형태가 중요

//        Long postId = postService.write(request);
//        return Map.of("postId", postId);
        request.validate();
        postService.write(request);
    }

    /**
     * /posts -> 글 전체조회(검색 + 페이징)
     * /posts/{postId} -> 글 한개만 조회
     */

    //Request 클래스
    //Response 클래스 -> 응답 클래스 분리 (서비스 정책에 맞는)
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit postEdit) {
        postService.edit(postId, postEdit);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
