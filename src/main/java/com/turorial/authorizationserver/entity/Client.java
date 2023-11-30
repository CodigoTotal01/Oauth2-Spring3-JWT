package com.turorial.authorizationserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String clientId;

    private String clientSecret;

    //Impornate
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<ClientAuthenticationMethod> authenticationMethods;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<AuthorizationGrantType> authorizationGrantTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> redirectUris;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> scopes;

    private boolean requireProofkey;


    //La informacion nos la proporcionara el cliente (React, Vue, ANgular)
    public static RegisteredClient toRegisteredClient(Client client){
        RegisteredClient.Builder builder = RegisteredClient.withId(client.getClientId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientIdIssuedAt(new Date().toInstant())
                .clientAuthenticationMethods(am -> am.addAll(client.getAuthenticationMethods()))
                .authorizationGrantTypes(authorizationGrantTypes1 -> authorizationGrantTypes1.addAll(client.getAuthorizationGrantTypes()))
                .redirectUris(ru -> ru.addAll(client.getRedirectUris()))
                .scopes(sc -> sc.addAll(client.getScopes()))
                .clientSettings(ClientSettings.builder().requireProofKey(client.isRequireProofkey()).build());
        return builder.build();
    }


}
