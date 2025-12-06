package com.example.stock_manager.reservation;

import com.example.stock_manager.common.BaseEntity;
import com.example.stock_manager.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Entity
@Table(name = "tbl_reservation")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseEntity {

    @NotNull
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull
    @Column(name = "reservation_time")
    private Instant reservationTime;

    @NotNull
    @Column(name = "expiration_time")
    private Instant expirationTime;

    @NotNull
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @NotNull
    @JoinColumn(name = "product_id")
    @ManyToOne
    public Product product;
}
