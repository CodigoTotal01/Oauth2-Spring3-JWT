package com.turorial.authorizationserver.controllers;

import com.turorial.authorizationserver.dto.CreateClientDto;
import com.turorial.authorizationserver.dto.MessageDto;
import com.turorial.authorizationserver.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<MessageDto> create (@RequestBody CreateClientDto dto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(dto));
    }

}
