package com.manoj.spring.springrest.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.manoj.spring.springrest.model.ClientIds;

@Component
public class ClientIdRepositoryImpl implements ClientIdRepository {

    private static final Logger log = LoggerFactory.getLogger(ClientIdRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    final String hql = "FROM clientids ORDER BY clientId";

    @Cacheable(value = "clientIds")
    public List<ClientIds> findAll() {
        List<ClientIds> result = new ArrayList<ClientIds>();
        result = em.createQuery(hql).getResultList();
        return result;
    }
}
