package com.manoj.spring.springrest.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.manoj.spring.springrest.dto.ClientIdsDto;
import com.manoj.spring.springrest.model.ClientIds;
import com.manoj.spring.springrest.service.ClientIdService;

@RestController
@RequestMapping("/cfl")
public class ClientIdRestController {

    private static final Logger log = LoggerFactory.getLogger(ClientIdRestController.class);

    @Autowired
    private ClientIdService clientIdService;

    @RequestMapping(value = "/clientids/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllClientIds(HttpServletRequest request) {

        final String hostName = getClientIp(request);
        log.info("Received request from remote address : " + hostName);

        List<ClientIds> clientIds;

        clientIds = clientIdService.findAll();

        if (clientIds.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ClientIdsDto dto = new ClientIdsDto();
        dto.setClientIds(clientIds);

        log.info("Processed request from IP : " + hostName);

        return new ResponseEntity<ClientIdsDto>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/async/clientid/", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<?>> listAllClientIdsUsingAsync(HttpServletRequest request) {

        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();

        CompletableFuture.supplyAsync(() -> listAllClientIds(request)).whenCompleteAsync((result, error) -> {
            if (error != null)
                deferredResult.setErrorResult(error);
            else
                deferredResult.setResult(result);
        });

        return deferredResult;
    }

    @RequestMapping(value = "/ping/", method = RequestMethod.GET)
    public ResponseEntity<?> ping() {

        return new ResponseEntity<ClientIdsDto>(HttpStatus.OK);
    }

    private static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (StringUtils.isEmpty(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
