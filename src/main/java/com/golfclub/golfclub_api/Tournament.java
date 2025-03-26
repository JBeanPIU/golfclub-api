package com.golfclub.golfclub_api;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor // Lombok will generate the no-argument constructor automatically
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tourneyStartDate;
    private LocalDate tourneyEndDate;
    private String tourneyLocal;
    private double entryFee;
    private double cashPrize;

    /* ----------------------------------------- */
    @ManyToMany(mappedBy = "tournaments")
    private Set<Member> participants = new HashSet<>();

    public Tournament(LocalDate tourneyStartDate, LocalDate tourneyEndDate, String tourneyLocal,
                      double entryFee, double cashPrize) {
        this.tourneyStartDate = tourneyStartDate;
        this.tourneyEndDate = tourneyEndDate;
        this.tourneyLocal = tourneyLocal;
        this.entryFee = entryFee;
        this.cashPrize = cashPrize;
    }
}
