package com.manoj.spring.mongodb.springmongodb;

import org.springframework.data.annotation.Id;

public class Story {

	@Id
	private String id;
	private String category;
	private String title;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Story [id=" + id + ", category=" + category + ", title="
				+ title + ", content=" + content + "]";
	}
	
	
}
