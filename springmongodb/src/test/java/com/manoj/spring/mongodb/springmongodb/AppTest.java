package com.manoj.spring.mongodb.springmongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.manoj.spring.mongodb.springmongodb.configuration.ApplicationConfiguration;
import com.manoj.spring.mongodb.springmongodb.configuration.MongoConfiguration;
import com.manoj.spring.mongodb.springmongodb.repository.CustomerRepository;

/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class,ApplicationConfiguration.class})
public class AppTest extends TestCase {

	@Autowired
	private CustomerRepository repository;

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
}
