package com.turorial.authorizationserver.entity;

import com.turorial.authorizationserver.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) //Para que se guarde como String en la base de datos el nombre del rol
    private RoleName role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
