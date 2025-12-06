package com.example.stock_manager.stock;

import com.example.stock_manager.common.BaseEntity;
import com.example.stock_manager.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_stock")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Stock extends BaseEntity {

    @NotNull
    @Column(name = "stock_count")
    private Integer stockCount;

    @NotNull
    @Column(name = "reserved_count")
    private Integer reservedCount;

    @OneToOne
    @JoinColumn(name = "product_id",unique = true)
    private Product product;

}
