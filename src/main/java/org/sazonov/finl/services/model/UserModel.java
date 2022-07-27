package org.sazonov.finl.services.model;

import lombok.Data;

@Data
public class UserModel {
    private String username;
    private String password;
    private String firstName;
    private String secondName;
}
