package org.sazonov.finl.entity.stuff;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.sazonov.finl.entity.Item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
public class OrderDto {
    private String userName;
    private StatusOfOrder statusOfOrder;
    private String adress;
    private List<Item> items;
    private LocalDate localDate;
    private BigDecimal summaryPrice;
}
