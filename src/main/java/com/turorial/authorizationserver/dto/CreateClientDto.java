package com.turorial.authorizationserver.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateClientDto {

    private int id;

    private String clientId;

    private String clientSecret;

    private Set<ClientAuthenticationMethod> authenticationMethods;

    private Set<AuthorizationGrantType> authorizationGrantTypes;

    private Set<String> redirectUris;

    private Set<String> scopes;

    private boolean requireProofkey;
}
