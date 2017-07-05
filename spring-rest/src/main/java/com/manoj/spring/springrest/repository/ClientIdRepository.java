package com.manoj.spring.springrest.repository;

import java.util.List;

import com.manoj.spring.springrest.model.ClientIds;

public interface ClientIdRepository {

    public List<ClientIds> findAll();
}
