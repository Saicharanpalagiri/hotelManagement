package com.hotelmanagement.hotelmanagement.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Hotel hotel;
    private int bookingDays;
    private LocalDateTime localDateTime;
    @PrePersist
    public void prePersist() {
        this.localDateTime = LocalDateTime.now();
    }

    public Booking(){}
    private LocalDateTime checkinDate;
    private LocalDateTime checkoutDate;

    public LocalDateTime getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDateTime checkinDate) {
        this.checkinDate = checkinDate;
        updateCheckoutDate();
    }

    public LocalDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getBookingDays() {
        return bookingDays;
    }

    public void setBookingDays(int bookingDays) {
        this.bookingDays = bookingDays;
        updateCheckoutDate();
    }

    private void updateCheckoutDate() {
        if (checkinDate != null && bookingDays > 0) {
            this.checkoutDate = checkinDate.plusDays(bookingDays);
        }
    }
}
