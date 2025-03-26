package com.golfclub.golfclub_api;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/* ----------------------------------------- */
// Entity class representing a Tournament
@Entity
@Getter
@Setter
@NoArgsConstructor // Lombok will generate the no-argument constructor automatically
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate tourneyStartDate;

    @Column(nullable = false)
    private LocalDate tourneyEndDate;

    @Column(nullable = false)
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

    /* ----------------------------------------- */
    public void addParticipant(Member member) {
        participants.add(member);
        member.getTournaments().add(this);
    }

    public void removeParticipant(Member member) {
        participants.remove(member);
        member.getTournaments().remove(this);
    }
}
