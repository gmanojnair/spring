package com.manoj.spring.springrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "clientids")
@Table(name = "clientids")
public class ClientIds {

    @Id
    @Column(name = "ClientId", unique = true, columnDefinition = "VARCHAR(255)")
    private String clientId;

    @Column(name = "ClientName")
    private String clientIdName;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientIdName() {
        return clientIdName;
    }

    public void setClientIdName(String clientIdName) {
        this.clientIdName = clientIdName;
    }

}