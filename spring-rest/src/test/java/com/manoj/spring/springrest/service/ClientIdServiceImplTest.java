package com.manoj.spring.springrest.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import com.manoj.spring.springrest.model.ClientIds;
import com.manoj.spring.springrest.repository.ClientIdRepository;
import com.manoj.spring.springrest.service.ClientIdService;
import com.manoj.spring.springrest.service.ClientIdServiceImpl;

@RunWith(SpringRunner.class)
public class ClientIdServiceImplTest {

    @TestConfiguration
    public static class ClientIdServiceImplTestConfiguration {

        @Bean
        public ClientIdService ClientIdService() {
            return new ClientIdServiceImpl();
        }

        @Bean
        @Primary
        public ClientIdRepository clientIdRepository() {
            return Mockito.mock(ClientIdRepository.class);
        }

    }

    @Autowired
    private ClientIdRepository clientIdRepository;

    @Autowired
    private ClientIdService clientIdService;

    @Test
    public void testFindAllClientIds() {
        ClientIds id1 = new ClientIds();
        id1.setClientId("Test_ClientId");
        List<ClientIds> expectedClientIds = new ArrayList<ClientIds>();
        expectedClientIds.add(id1);
        Mockito.when(clientIdRepository.findAll()).thenReturn(expectedClientIds);
        List<ClientIds> result = clientIdService.findAll();
        Assert.assertTrue(result.get(0).getClientId().equals("Test_ClientId"));
    }
}
