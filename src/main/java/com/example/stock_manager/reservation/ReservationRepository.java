package com.example.stock_manager.reservation;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends ListCrudRepository<Reservation, Long> {

    List<Reservation> findAllByStatusAndExpirationTimeBefore(Status status, Instant now);

    Reservation findByStatusAndProductId(Status status, Long productId);

}