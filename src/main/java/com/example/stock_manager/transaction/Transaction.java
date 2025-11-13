package com.example.stock_manager.transaction;

import com.example.stock_manager.common.BaseEntity;
import com.example.stock_manager.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_transaction")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {

    @NotNull
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @NotNull
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull
    @JoinColumn(name = "product_id")
    @ManyToOne
    public Product product;

}
