package com.manoj.spring.springrest.dto;

import java.util.List;

import com.manoj.spring.springrest.model.ClientIds;

public class ClientIdsDto {

    private List<ClientIds> clientIds;

    public List<ClientIds> getClientIds() {
        return clientIds;
    }

    public void setClientIds(List<ClientIds> clientIds) {
        this.clientIds = clientIds;
    }

}
