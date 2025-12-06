package com.example.stock_manager.reservation;

import com.example.stock_manager.common.ResponseHandler;
import com.example.stock_manager.transaction.Transaction;
import com.example.stock_manager.transaction.TransactionDTO;
import com.example.stock_manager.transaction.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;
    private final ReservationMapper reservationMapper;
    private final TransactionMapper transactionMapper;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReservationRequestDTO reservationRequestDTO){

        Reservation reservation = reservationMapper.toReservation(reservationRequestDTO);
        reservationService.save(reservation);

        return ResponseHandler.generateResponseMessage(HttpStatus.OK, "success", false, "1000");
    }


    @PatchMapping("/{reservationId}/complete")
    public ResponseEntity<TransactionDTO> completeReservationPurchase(@PathVariable Long reservationId){

        Transaction transaction = reservationService.completeReservationPurchase(reservationId);
        TransactionDTO transactionDTO = transactionMapper.toTransactionDTO(transaction);

        return ResponseHandler.generateResponse(HttpStatus.OK, false, "1000", transactionDTO);
    }


}
