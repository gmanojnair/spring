package com.manoj.thoughtsnet.myapp.domain;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class StoryDTO {

	
	private String id;

	private Date createTime;
	private Date updateTime;

	@NotEmpty
	private String title;

	private String content1;

	private char[] image1;

	private char[] imageData1;

	private String content2;

	private char[] image2;

	private char[] imageData2;

	private String content3;

	private char[] image3;

	private char[] imageData3;

	private String content4;

	private char[] image4;

	private char[] imageData4;

	private String content5;

	private char[] image5;

	private char[] imageData5;

	private String content6;

	private char[] image6;

	private char[] imageData6;

	public String getId() {
		return id;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public char[] getImage1() {
		return image1;
	}

	public void setImage1(char[] image1) {
		this.image1 = image1;
	}

	public char[] getImageData1() {
		return imageData1;
	}

	public void setImageData1(char[] imageData1) {
		this.imageData1 = imageData1;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public char[] getImage2() {
		return image2;
	}

	public void setImage2(char[] image2) {
		this.image2 = image2;
	}

	public char[] getImageData2() {
		return imageData2;
	}

	public void setImageData2(char[] imageData2) {
		this.imageData2 = imageData2;
	}

	public String getContent3() {
		return content3;
	}

	public void setContent3(String content3) {
		this.content3 = content3;
	}

	public char[] getImage3() {
		return image3;
	}

	public void setImage3(char[] image3) {
		this.image3 = image3;
	}

	public char[] getImageData3() {
		return imageData3;
	}

	public void setImageData3(char[] imageData3) {
		this.imageData3 = imageData3;
	}

	public String getContent4() {
		return content4;
	}

	public void setContent4(String content4) {
		this.content4 = content4;
	}

	public char[] getImage4() {
		return image4;
	}

	public void setImage4(char[] image4) {
		this.image4 = image4;
	}

	public char[] getImageData4() {
		return imageData4;
	}

	public void setImageData4(char[] imageData4) {
		this.imageData4 = imageData4;
	}

	public String getContent5() {
		return content5;
	}

	public void setContent5(String content5) {
		this.content5 = content5;
	}

	public char[] getImage5() {
		return image5;
	}

	public void setImage5(char[] image5) {
		this.image5 = image5;
	}

	public char[] getImageData5() {
		return imageData5;
	}

	public void setImageData5(char[] imageData5) {
		this.imageData5 = imageData5;
	}

	public String getContent6() {
		return content6;
	}

	public void setContent6(String content6) {
		this.content6 = content6;
	}

	public char[] getImage6() {
		return image6;
	}

	public void setImage6(char[] image6) {
		this.image6 = image6;
	}

	public char[] getImageData6() {
		return imageData6;
	}

	public void setImageData6(char[] imageData6) {
		this.imageData6 = imageData6;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void parseStory(Story story) throws UnsupportedEncodingException {
		this.setId(story.getId());
		this.setContent1(story.getContent1());
		this.setContent2(story.getContent2());
		this.setContent3(story.getContent3());
		this.setContent4(story.getContent4());
		this.setContent5(story.getContent5());
		this.setContent6(story.getContent6());
		this.setImageData1(changeToByte(story.getImageData1()));
		this.setImageData2(changeToByte(story.getImageData2()));
		this.setImageData3(changeToByte(story.getImageData3()));
		this.setImageData4(changeToByte(story.getImageData4()));
		this.setImageData5(changeToByte(story.getImageData5()));
		this.setImageData6(changeToByte(story.getImageData6()));
		this.setTitle(story.getTitle());
	}

	
	
	private char[] changeToByte( byte[] imageData12) throws UnsupportedEncodingException{
		// TODO Auto-generated method stub
		
		if (imageData12 != null) {
			String s = new String(imageData12, "UTF-8"); // possibly
			char[] chars = s.toCharArray();
			return chars;
			//this.setImage(chars);
		}
		return null;
		
	}


}
