package com.turorial.authorizationserver.service;

import com.turorial.authorizationserver.dto.CreateAppUserDto;
import com.turorial.authorizationserver.dto.MessageDto;
import com.turorial.authorizationserver.entity.AppUser;
import com.turorial.authorizationserver.entity.Role;
import com.turorial.authorizationserver.enums.RoleName;
import com.turorial.authorizationserver.repository.AppUserRepository;
import com.turorial.authorizationserver.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository repository;
    private final PasswordEncoder passwordEncoder;

    public MessageDto createUser(CreateAppUserDto dto){
        AppUser appUser = AppUser.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .build();
        Set<Role> roles = new HashSet<>();
        dto.roles().forEach(r -> {
            Role role = repository.findByRole(RoleName.valueOf(r))
                    .orElseThrow(()-> new RuntimeException("role not found"));
            roles.add(role);
        });
        appUser.setRoles(roles);
        appUserRepository.save(appUser);
        return new MessageDto("user " + appUser.getUsername() + " saved");
    }
}