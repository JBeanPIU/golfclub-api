package com.golfclub.golfclub_api;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memName;
    private String memAddress;
    private String memEmail;
    private String memPhone;
    private LocalDate memStartDate;
    private int memDurationInMonths;

    /* ----------------------------------------- */
    // CONSTRUCTORS
    public Member() {}

    public Member(String memName, String memAddress, String memEmail, String memPhone,
                  LocalDate memStartDate, int memDurationInMonths) {
        this.memName = memName;
        this.memAddress = memAddress;
        this.memEmail = memEmail;
        this.memPhone = memPhone;
        this.memStartDate = memStartDate;
        this.memDurationInMonths = memDurationInMonths;
    }

    // Normally I'd put mutators, but Lombok will be used to reduce boilerplate code later
}