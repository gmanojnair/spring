package com.manoj.thoughtsnet.myapp.domain;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.google.common.base.Optional;

@Document
public class Story {

	public Story(){
		
	}
	
	public Story(Builder builder) {
		// TODO Auto-generated constructor stub
		this.id=builder.id;
		this.title=builder.title;
		this.content1=builder.content1;
		this.imageData1=builder.imageData1;
		this.content2=builder.content2;
		this.imageData2=builder.imageData2;
		this.content3=builder.content3;
		this.imageData3=builder.imageData3;
		this.content4=builder.content4;
		this.imageData4=builder.imageData4;
		this.content5=builder.content5;
		this.imageData5=builder.imageData5;
		this.content6=builder.content6;
		this.imageData6=builder.imageData6;
	}

	@Id
	private String id;

	private Date createTime;
	private Date updateTime;

	@Field(value = "title")
	private String title;

	@Field(value = "content1")
	private String content1;

	@Field(value = "imageData1")
	private byte[] imageData1;
	
	@Field(value = "content2")
	private String content2;

	@Field(value = "imageData2")
	private byte[] imageData2;
	
	@Field(value = "content3")
	private String content3;

	@Field(value = "imageData3")
	private byte[] imageData3;
	
	@Field(value = "content4")
	private String content4;

	@Field(value = "imageData4")
	private byte[] imageData4;
	
	
	@Field(value = "content5")
	private String content5;

	@Field(value = "imageData5")
	private byte[] imageData5;
	
	@Field(value = "content6")
	private String content6;

	@Field(value = "imageData6")
	private byte[] imageData6;
	
	
	

	public String getId() {
		return id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String getTitle() {
		return title;
	}




	// Getters and setters

	public String getContent1() {
		return content1;
	}

	public byte[] getImageData1() {
		return imageData1;
	}

	public String getContent2() {
		return content2;
	}

	public byte[] getImageData2() {
		return imageData2;
	}

	public String getContent3() {
		return content3;
	}

	public byte[] getImageData3() {
		return imageData3;
	}

	public String getContent4() {
		return content4;
	}

	public byte[] getImageData4() {
		return imageData4;
	}

	public String getContent5() {
		return content5;
	}

	public byte[] getImageData5() {
		return imageData5;
	}

	public String getContent6() {
		return content6;
	}

	public byte[] getImageData6() {
		return imageData6;
	}

	public static Builder createBuilder() {
		return new Builder();
	}

	public static class Builder {
		private String id;

		private Date createTime;
		private Date updateTime;

		private String title;

		private String content1;

		private byte[] imageData1;
		
		
		private String content2;

		private byte[] imageData2;
		
		
		private String content3;

		private byte[] imageData3;
		
		
		private String content4;

		private byte[] imageData4;
		
		
		private String content5;

		private byte[] imageData5;
		
		
		private String content6;

		private byte[] imageData6;
		
		
		
		
		public Builder id(String id){
			this.id=id;
			return this;
		}

		public Builder title(String title){
			this.title=title;
			return this;
		}
		
		public Builder content1(String content){
			this.content1=content;
			return this;
		}
		public Builder imageDate1(byte[]  data){
			this.imageData1=data;
			return this;
		}
		
		
		public Builder content2(String content){
			this.content2=content;
			return this;
		}
		public Builder imageDate2(byte[]  data){
			this.imageData2=data;
			return this;
		}
		
		
		public Builder content3(String content){
			this.content3=content;
			return this;
		}
		public Builder imageDate3(byte[]  data){
			this.imageData3=data;
			return this;
		}
		
		
		public Builder content4(String content){
			this.content4=content;
			return this;
		}
		public Builder imageDate4(byte[]  data){
			this.imageData4=data;
			return this;
		}
		
		public Builder content5(String content){
			this.content5=content;
			return this;
		}
		public Builder imageDate5(byte[]  data){
			this.imageData5=data;
			return this;
		}
		
		public Builder content6(String content){
			this.content6=content;
			return this;
		}
		public Builder imageDate6(byte[]  data){
			this.imageData6=data;
			return this;
		}
		
		public Story build() {
			Story story = new Story(this);
			return story;
			
		}
		
	}

}