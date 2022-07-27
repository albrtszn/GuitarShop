package org.sazonov.finl.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sazonov.finl.entity.Adress;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AdressDto {
    private String city;
    private String street;
    private String numOfHouse;

    public AdressDto convertToDto(Adress adresses){
        return AdressDto.builder()
                .city(adresses.getAcity())
                .street(adresses.getBstreet())
                .numOfHouse(adresses.getCnumOfHouse())
                .build();
    }
}
