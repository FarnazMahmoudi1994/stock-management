package com.example.stock_manager.reservation;

import com.example.stock_manager.common.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationResponseDTO extends BaseDTO {

    private Integer quantity;

    private Date reservationTime;

    private Date expirationTime;

    private Status status;

    private Long productId;
}
