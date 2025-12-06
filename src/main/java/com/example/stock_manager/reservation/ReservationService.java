package com.example.stock_manager.reservation;

import com.example.stock_manager.common.exception.IllegalStateException;
import com.example.stock_manager.common.exception.InsufficientStockException;
import com.example.stock_manager.common.exception.NotFoundException;
import com.example.stock_manager.product.Product;
import com.example.stock_manager.product.ProductService;
import com.example.stock_manager.stock.Stock;
import com.example.stock_manager.stock.StockRepository;
import com.example.stock_manager.stock.StockService;
import com.example.stock_manager.transaction.Transaction;
import com.example.stock_manager.transaction.TransactionService;
import com.example.stock_manager.transaction.Type;
import com.example.stock_manager.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {

    private final ReservationRepository repository;
    private final StockRepository stockRepository;
    private final TransactionService transactionService;
    private final ProductService productService;

    private final StockService stockService;

    @Transactional
    @Override
    public Reservation save(Reservation reservation) {
        Long productId = reservation.getProduct().getId();
        Product product = productService.getById(productId);
        if(reservation.getQuantity() > stockService.getSalableInventory(productId)){

            throw new InsufficientStockException("", "موجودی کافی برای محصول " + product.getName() + " وجود ندارد.");

        }

        Instant now = Instant.now();
        Instant expirationTime = now.plus(Duration.ofMinutes(Utility.RESERVATION_DURATION_MINUTES));

        reservation.setProduct(product);
        reservation.setReservationTime(now);
        reservation.setExpirationTime(expirationTime);
        reservation.setStatus(Status.PENDING);
        Reservation newReservation = repository.save(reservation);

        Stock stock = newReservation.getProduct().getStock();
        stock.setReservedCount(stock.getReservedCount()+ newReservation.getQuantity());
        stockRepository.save(stock);
       return newReservation;

    }

    @Scheduled(fixedRate = 300000)
    @Transactional
    public void releaseExpiredReservations() {
        Instant now = Instant.now();

        List<Reservation> expiredReservations = repository
                .findAllByStatusAndExpirationTimeBefore(Status.PENDING, now);

        if (expiredReservations.isEmpty()) {
            return;
        }

        for (Reservation reservation : expiredReservations) {

            reservation.setStatus(Status.EXPIRED);
            repository.save(reservation);

            Stock stock = reservation.getProduct().getStock();

            stock.setReservedCount(stock.getReservedCount() - reservation.getQuantity());

            stockRepository.save(stock);

            System.out.println(expiredReservations.size() + " رزرو منقضی شده آزاد شد.");
        }
    }


    @Transactional
    @Override
    public Transaction completeReservationPurchase(Long reservationId) {

        Reservation reservation = repository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("", "رزرو پیدا نشد."));

        if (reservation.getStatus() != Status.PENDING) {
            throw new IllegalStateException("", "فقط رزروهای معلق قابل تکمیل هستند.");
        }


        Transaction transaction = Transaction.builder()
                .quantity(reservation.getQuantity())
                .product(reservation.getProduct())
                .type(Type.OUT)
                .build();

        Transaction savedTransaction = transactionService.purchaseProduct(transaction, true);
        Stock stock = reservation.getProduct().getStock();
        stock.setReservedCount(stock.getReservedCount() - reservation.getQuantity());
        stockRepository.save(stock);

        reservation.setStatus(Status.COMPLETED);
        repository.save(reservation);

        return savedTransaction;
    }

    @Override
    public Reservation getById(Long id) {
       return repository.findById(id)
               .orElseThrow(() -> new NotFoundException("","reservation not found"));
    }


}
