package com.drugstore.Model;

import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private Duration totalHours;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getCheckIn() { return checkIn; }
    public void setCheckIn(LocalTime checkIn) { this.checkIn = checkIn; }

    public LocalTime getCheckOut() { return checkOut; }
    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
        if (this.checkIn != null && this.checkOut != null) {
            this.totalHours = Duration.between(this.checkIn, this.checkOut);
        }
    }

    public Duration getTotalHours() { return totalHours; }
    public void setTotalHours(Duration totalHours) { this.totalHours = totalHours; }
}
