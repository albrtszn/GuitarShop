package org.sazonov.finl.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.sazonov.finl.services.model.AdressDto;
import org.sazonov.finl.services.model.RoleDto;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
public class UserDto {
    private String username;
    private String firstname;
    private String secondName;
    private List<AdressDto> adresses;
    private List<RoleDto> authorities;
}
