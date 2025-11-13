package com.example.stock_manager.product;


import com.example.stock_manager.common.BaseEntity;
import com.example.stock_manager.stock.Stock;
import com.example.stock_manager.transaction.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_product")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 10000)
    private String description;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @OneToOne(fetch = FetchType.LAZY,mappedBy ="product",cascade = CascadeType.ALL)
    private Stock stock;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product",cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
