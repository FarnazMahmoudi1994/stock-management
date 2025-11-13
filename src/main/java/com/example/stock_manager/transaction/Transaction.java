package com.example.stock_manager.transaction;

import com.example.stock_manager.common.BaseEntity;
import com.example.stock_manager.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "tbl_transaction")
@Data
public class Transaction extends BaseEntity {

    @NotNull
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @NotNull
    @JoinColumn(name = "product_id")
    @ManyToOne
    public Product product;

}
