package com.example.stock_manager.reservation;

import com.example.stock_manager.product.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ReservationMapper {

    @Mappings({@Mapping(source = "productId", target = "product.id")})
    Reservation toReservation(ReservationRequestDTO reservationRequestDTO);

    ReservationResponseDTO toReservationResponseDTO(Reservation reservation);

    List<ReservationResponseDTO> toReservationResponseDTOS(List<Reservation> reservations);

    @Mappings({@Mapping(source = "productId", target = "product.id")})
    List<Reservation> toReservations(List<ReservationRequestDTO> reservationRequestDTOS);

}
