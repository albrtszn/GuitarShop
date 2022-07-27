package org.sazonov.finl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sazonov.finl.entity.stuff.StatusOfOrder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private StatusOfOrder statusOfOrder;
    @ManyToOne
    private User user;
    @ManyToOne
    private Adress adress;
    private List<Item> items;
    private LocalDate data;
    private BigDecimal price;
}
