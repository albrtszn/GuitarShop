package org.sazonov.finl.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.sazonov.finl.entity.User;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "roles_of_users")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private String number;
    private String name;

    @ManyToOne
    @ToString.Exclude
    private User user;

    public Role(String name){
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}