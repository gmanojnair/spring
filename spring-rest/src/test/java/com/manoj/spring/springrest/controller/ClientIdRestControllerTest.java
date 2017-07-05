package com.manoj.spring.springrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.manoj.spring.springrest.controller.ClientIdRestController;
import com.manoj.spring.springrest.model.ClientIds;
import com.manoj.spring.springrest.service.ClientIdService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClientIdRestController.class, secure = false)
public class ClientIdRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientIdService service;

    @Test
    public void testFindAllClientIds() throws Exception {
        ClientIds id1 = new ClientIds();
        id1.setClientId("Test_ClientId");
        List<ClientIds> expectedClientIds = new ArrayList<ClientIds>();
        expectedClientIds.add(id1);
        Mockito.when(service.findAll()).thenReturn(expectedClientIds);

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get("/cfl/clientids/").accept(MediaType.APPLICATION_JSON_UTF8);
        MvcResult andReturn = mvc.perform(requestBuilder).andReturn();
        // String expected = "\"{\"clientIds\":[{\"clientId\":\"Test_ClientId\",\"clientIdName\":null}]}\"";
        String expected = "{'clientIds':[{'clientId':'Test_ClientId','clientIdName':null}]}";
        System.out.println(expected);
        JSONAssert.assertEquals(expected, andReturn.getResponse().getContentAsString(), false);

    }

    @Test
    public void testFindAllClientIdsforAsync() throws Exception {
        ClientIds id1 = new ClientIds();
        id1.setClientId("Test_ClientId");
        List<ClientIds> expectedClientIds = new ArrayList<ClientIds>();
        expectedClientIds.add(id1);
        Mockito.when(service.findAll()).thenReturn(expectedClientIds);

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get("/cfl/async/clientid/").accept(MediaType.APPLICATION_JSON_UTF8);
        MvcResult andReturn = mvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(andReturn.getResponse().getStatus(), HttpStatus.OK.value());

    }
}
