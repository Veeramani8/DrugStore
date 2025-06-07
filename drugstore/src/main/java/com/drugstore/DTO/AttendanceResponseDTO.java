package com.drugstore.DTO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceResponseDTO {

    private Long id;
    private Long userId;
    private String fullName;
    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private Duration totalHours;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    public Duration getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Duration totalHours) {
        this.totalHours = totalHours;
    }
}
