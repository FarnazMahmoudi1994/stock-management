package com.example.stock_manager.reservation;

import com.example.stock_manager.transaction.Transaction;

public interface IReservationService {

    Reservation save(Reservation reservation);

    Transaction completeReservationPurchase(Long reservationId);

    Reservation getById(Long id);


}
