package com.manoj.thoughtsnet.myapp.endpoint;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.manoj.thoughtsnet.myapp.domain.Story;
import com.manoj.thoughtsnet.myapp.domain.StoryDTO;
import com.manoj.thoughtsnet.myapp.service.StoryService;

@RestController
@RequestMapping("/api")
public class StoryController {

	public static final Logger logger = LoggerFactory
			.getLogger(StoryController.class);

	private static final Decoder BASE_64_DECODER = Base64.getMimeDecoder();

	private static final Encoder BASE_64_ENCODER = Base64.getMimeEncoder();

	private static final String ENCODING = "UTF-8";// Use the correct encoding
													// here.

	@Autowired
	StoryService storyService;

	// -------------------Retrieve All
	// stories---------------------------------------------

	@RequestMapping(value = "/story/", method = RequestMethod.GET)
	public ResponseEntity<List<StoryDTO>> listAllStories()
			throws UnsupportedEncodingException {
		List<Story> stories = storyService.findAllStories();
		if (stories.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		List<StoryDTO> storiesDto = new ArrayList<StoryDTO>();
		for (Story story : stories) {
			StoryDTO storyDto = new StoryDTO();
			storyDto.parseStory(story);
			storiesDto.add(storyDto);
		}
		return new ResponseEntity<List<StoryDTO>>(storiesDto, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Story------------------------------------------

	@RequestMapping(value = "/story/{title}", method = RequestMethod.GET)
	public ResponseEntity<?> getStory(@PathVariable("title") String title)
			throws UnsupportedEncodingException {
		logger.info("Fetching Story with id {}", title);
		Story story = storyService.findByTitle(title);
		if (story == null) {
			logger.error("Story with id {} not found.", title);
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		StoryDTO storyDto = new StoryDTO();
		storyDto.parseStory(story);

		return new ResponseEntity<StoryDTO>(storyDto, HttpStatus.OK);
	}

	// -------------------Create a
	// Story-------------------------------------------

	@RequestMapping(value = "/story/", method = RequestMethod.POST)
	public ResponseEntity<?> createStory(@RequestBody StoryDTO storydto,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating Story : {}", storydto);
		
		
		/*Story story = Story.createBuilder().title(storydto.getTitle())
				.content(storydto.getContent())
				.imageDate(BASE_64_DECODER.decode(byteBuffer.array())).build();
		*/
		
		
		Story story = Story.createBuilder().title(storydto.getTitle())
				.content1(storydto.getContent1())
				.imageDate1(getByteImage(storydto.getImageData1()))
				.content2(storydto.getContent2())
				.imageDate2(getByteImage(storydto.getImageData2()))
				.content3(storydto.getContent3())
				.imageDate3(getByteImage(storydto.getImageData3()))
				.content4(storydto.getContent4())
				.imageDate4(getByteImage(storydto.getImageData4()))
				.content5(storydto.getContent5())
				.imageDate5(getByteImage(storydto.getImageData5()))
				.content6(storydto.getContent6())
				.imageDate6(getByteImage(storydto.getImageData6()))
				.build();

		if (storyService.isStoryExist(storydto)) {
			logger.error(
					"Unable to create. A Story with name {} already exist",
					storydto.getTitle());
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		storyService.saveStory(story);

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	// ------------------- Delete a
	// Story-----------------------------------------

	private byte[] getByteImage(char[] array) {
		// TODO Auto-generated method stub
		if(array == null) return null;
		CharBuffer charBuffer = CharBuffer.wrap(array);
		ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
		return byteBuffer.array();
	}

	@RequestMapping(value = "/story/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStory(@PathVariable("id") String id) {
		logger.info("Fetching & Deleting Story with id {}", id);

		Story user = storyService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. Story with id {} not found.", id);
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		storyService.deleteStoryById(id);
		return new ResponseEntity<Story>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Storys-----------------------------

	@RequestMapping(value = "/story/", method = RequestMethod.DELETE)
	public ResponseEntity<Story> deleteAllStorys() {
		logger.info("Deleting All Storys");

		storyService.deleteAllStories();
		return new ResponseEntity<Story>(HttpStatus.NO_CONTENT);
	}

}
