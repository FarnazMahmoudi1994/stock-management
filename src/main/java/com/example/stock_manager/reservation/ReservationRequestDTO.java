package com.example.stock_manager.reservation;

import com.example.stock_manager.common.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ReservationRequestDTO extends BaseDTO {

    @NotBlank
    private Integer quantity;

    @NotNull
    private Long productId;
}
