package org.sazonov.finl.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sazonov.finl.entity.Role;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    public String roleOfUser;

    public RoleDto convertToDto(Role role){
        return RoleDto.builder()
                .roleOfUser(role.getName())
                .build();
    }
}
