package com.example.researchcentersystem.Backend;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    private LocalDate day;

    public void reserveMachine(Machine machine1, LocalDateTime timeFrom, LocalDateTime timeTo){}

    public Reservation(LocalDateTime timeFrom, LocalDateTime timeTo, LocalDate day) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.day = day;
    }

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }
}
