package com.manoj.thoughtsnet.myapp.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.manoj.thoughtsnet.myapp.domain.Story;

public interface StoryRepository extends MongoRepository<Story, String> {

     public Story findByTitle(String title);

	public Story findById(String id);

}
