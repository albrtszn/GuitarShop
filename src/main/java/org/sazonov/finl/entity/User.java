package org.sazonov.finl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@SuperBuilder
public class User implements UserDetails {
    @Id
    //@Column(name = "number")
    private String username;
    private String password;
    private String firstName;
    private String secondName;

    //private LocalDateTime dateOfBorn;

    @OneToMany(mappedBy = "user")
    private List<Adress> adresses;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Singular
    private List<Role> authorities;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Order> orders;
    @OneToOne(mappedBy = "user")
    private Cart cart;
//    @OneToMany
//    private Order order;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
