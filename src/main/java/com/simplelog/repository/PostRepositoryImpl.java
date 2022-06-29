package com.simplelog.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simplelog.domain.Post;
import com.simplelog.domain.QPost;
import com.simplelog.request.PostSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.simplelog.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(post)
                .limit(postSearch.getSize())
                .offset(postSearch.getOffset())
                .orderBy(post.id.desc())
                .fetch();
    }

}
