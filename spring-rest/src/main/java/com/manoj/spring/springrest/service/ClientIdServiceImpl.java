package com.manoj.spring.springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manoj.spring.springrest.model.ClientIds;
import com.manoj.spring.springrest.repository.ClientIdRepository;

@Service
public class ClientIdServiceImpl implements ClientIdService {

    @Autowired
    private ClientIdRepository clientIdRepository;

    public List<ClientIds> findAll() {
        return clientIdRepository.findAll();
    }

}