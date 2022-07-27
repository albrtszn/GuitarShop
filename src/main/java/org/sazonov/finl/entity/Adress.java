package org.sazonov.finl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String acity;
    @Column(name = "street")
    private String bstreet;
    @Column(name = "numofhouse")
    private String cnumOfHouse;

    @ManyToOne(targetEntity= User.class)
    @ToString.Exclude
    private User user;

    public String toModel(){
        return this.acity+this.bstreet+this.cnumOfHouse;
    }
}
