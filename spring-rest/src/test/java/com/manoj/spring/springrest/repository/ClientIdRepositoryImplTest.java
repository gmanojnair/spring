package com.manoj.spring.springrest.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import com.manoj.spring.springrest.model.ClientIds;
import com.manoj.spring.springrest.repository.ClientIdRepository;
import com.manoj.spring.springrest.repository.ClientIdRepositoryImpl;

@RunWith(SpringRunner.class)
public class ClientIdRepositoryImplTest {

    @TestConfiguration
    public static class ClientIdRepositoryImplTestConfiguration {

        @Bean
        @Primary
        public EntityManager entityManager() {
            return Mockito.mock(EntityManager.class);
        }

    }

    @Autowired
    private EntityManager entityManager;

    @Mock
    private Query query;

    private ClientIdRepository repository;

    @Before
    public void init() {

        repository = new ClientIdRepositoryImpl();
        org.springframework.test.util.ReflectionTestUtils.setField(repository, "em", entityManager);

    }

    @Test
    public void testFindAll() {
        // given
        ClientIds test = new ClientIds();
        test.setClientId("TEST");
        List<ClientIds> expectedClientIds = new ArrayList<ClientIds>();
        expectedClientIds.add(test);
        Mockito.when(entityManager.createQuery(Matchers.any())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(expectedClientIds);

        // when
        List<ClientIds> found = repository.findAll();

        // then

        Assert.assertTrue(found.get(0).getClientId().equals("TEST"));
    }

}
