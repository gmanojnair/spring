package com.manoj.spring.mongodb.springmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manoj.spring.mongodb.springmongodb.Story;

public interface StoryRepository extends MongoRepository<Story, String> {

	public Story findByCategory(String category);

	public List<Story> findByTitle(String title);
}
