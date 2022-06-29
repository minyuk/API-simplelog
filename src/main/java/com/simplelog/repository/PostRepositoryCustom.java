package com.simplelog.repository;

import com.simplelog.domain.Post;
import com.simplelog.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
