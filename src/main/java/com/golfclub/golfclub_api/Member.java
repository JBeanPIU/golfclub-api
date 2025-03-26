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
@NoArgsConstructor // Lombok generates the no-args constructor automatically
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memName; // using mem because it helps specify what class/object it relates to, being part of membership
    private String memAddress;
    private String memEmail;
    private String memPhone;
    private LocalDate memStartDate;
    private int memDurationInMonths;

    /* ----------------------------------------- */
    @ManyToMany
    @JoinTable(
            name = "member_tournaments",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private Set<Tournament> tournaments = new HashSet<>();

    /* ----------------------------------------- */
    public Member(String memName, String memAddress, String memEmail, String memPhone,
                  LocalDate memStartDate, int memDurationInMonths) {
        this.memName = memName;
        this.memAddress = memAddress;
        this.memEmail = memEmail;
        this.memPhone = memPhone;
        this.memStartDate = memStartDate;
        this.memDurationInMonths = memDurationInMonths;
    }
}
