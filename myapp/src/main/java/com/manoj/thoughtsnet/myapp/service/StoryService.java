package com.manoj.thoughtsnet.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manoj.thoughtsnet.myapp.domain.Story;
import com.manoj.thoughtsnet.myapp.domain.StoryDTO;
import com.manoj.thoughtsnet.myapp.repository.StoryRepository;

@Service
@Transactional
public class StoryService {
	@Autowired
	private StoryRepository repository;

	public List<Story> findAllStories() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Story findByTitle(String title) {
		// TODO Auto-generated method stub
		return repository.findByTitle(title);
	}

	public boolean isStoryExist(StoryDTO story) {
		// TODO Auto-generated method stub
	    Story savedStory = repository.findByTitle(story.getTitle());
	    if (savedStory != null) return true;
		return false;
	}

	public void saveStory(Story story) {
		// TODO Auto-generated method stub
		repository.save(story);
		return ;
	}

	public Story findById(String id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	public void deleteStoryById(String id) {
		// TODO Auto-generated method stub
		Story savedStory = repository.findById(id);
		repository.delete(savedStory);
		
	}

	public void deleteAllStories() {
		// TODO Auto-generated method stub
		repository.deleteAll();		
	}



}
