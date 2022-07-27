package org.sazonov.finl.entity.stuff;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.sazonov.finl.entity.Item;
import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
public class CartDto {
    private List<Item> items;
    private BigDecimal summaryPrice;
}
