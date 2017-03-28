package com.manoj.spring.mongodb.springmongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.DataInputStream;
import java.io.FileInputStream;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.manoj.spring.mongodb.springmongodb.configuration.ApplicationConfiguration;
import com.manoj.spring.mongodb.springmongodb.configuration.MongoConfiguration;
import com.manoj.spring.mongodb.springmongodb.repository.CustomerRepository;
import com.manoj.spring.mongodb.springmongodb.repository.ImageStorageRepository;

/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class,ApplicationConfiguration.class})
public class AppTest extends TestCase {

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private ImageStorageRepository imagerepository;
	
	@Value("classpath:child.jpg")
	private Resource imageFile;

	@Autowired
	private MongoOperations mongoOps;

	@Before
	public void testSetup() {
		if (!mongoOps.collectionExists(Customer.class)) {
			mongoOps.createCollection(Customer.class);
		}
	}

	@After
	public void tearDown() {
		mongoOps.dropCollection(User.class);
	}

	@Test
	public void whenInsertingCustomer_thenCustomerIsInserted() {
		final Customer user = new Customer("Alice", "Smith");
		repository.insert(user);

		Customer customer = mongoOps.findOne(
				Query.query(Criteria.where("firstName").is("Alice")),
				Customer.class);

		assertThat(customer.getFirstName(), is("Alice"));
	}
	
	@Test
	public void whenInsertingImage_thenImageInserted() throws Exception {
		   Resource resource = imageFile;
		   byte[] imgDataBa = new byte[(int)resource.getFile().length()];
		   DataInputStream dataIs = new DataInputStream(new FileInputStream(resource.getFile()));
		   dataIs.readFully(imgDataBa);
		   
		   final Image image = new Image("test",imgDataBa);
		   
		   imagerepository.insert(image);

		
		   
	}
}
