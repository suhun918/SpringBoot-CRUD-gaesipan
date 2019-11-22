package com.cos.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.crud.model.Post;
import com.cos.crud.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	public void postUpdate(Post post) {
		postRepository.delete(post.getId());
		postRepository.save(post);
	}
}
