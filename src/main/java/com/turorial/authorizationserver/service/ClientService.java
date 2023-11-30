package com.turorial.authorizationserver.service;

import com.turorial.authorizationserver.dto.CreateClientDto;
import com.turorial.authorizationserver.dto.MessageDto;
import com.turorial.authorizationserver.entity.Client;
import com.turorial.authorizationserver.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void save(RegisteredClient registeredClient) {

    }


    public MessageDto create (CreateClientDto dto){
        Client client = clientFromDto(dto);
        clientRepository.save(client);
        return new MessageDto("client " + client.getClientId() + " saved");
    }
    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findByClientId(id).orElseThrow(() -> new RuntimeException("El id del cliente no se a encontrado"));
        return client.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId).orElseThrow(() -> new RuntimeException("El id del cliente no se a encontrado"));
        return client.toRegisteredClient(client);
    }

    private Client clientFromDto(CreateClientDto createClientDto){
        Client client = Client.builder()
                .clientId(createClientDto.getClientId())
                .clientSecret(passwordEncoder.encode(createClientDto.getClientSecret()))
                .authenticationMethods(createClientDto.getAuthenticationMethods())
                .authorizationGrantTypes(createClientDto.getAuthorizationGrantTypes())
                .redirectUris(createClientDto.getRedirectUris())
                .scopes(createClientDto.getScopes())
                .requireProofkey(createClientDto.isRequireProofkey())
                .build();

        return client;
    }
}
