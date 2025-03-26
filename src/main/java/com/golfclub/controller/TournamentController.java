package com.golfclub.golfclub_api.controller;

import com.golfclub.golfclub_api.Tournament;
import com.golfclub.repository.MemberRepo;
import com.golfclub.repository.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/* ------------------------------------------- */
// Controller settings
@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentRepo tourneyRepo; // injects repo for accessing tourneys

    /* --------------- */
    // create new tournament
    @PostMapping
    public Tournament createTourney(@RequestBody Tournament tournament) {
        return tourneyRepo.save(tournament);
    }

    /* --------------- */
    // get all tournaments
    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tourneyRepo.findAll();
    }

    /* --------------- */
    // search for tournaments by the start date or location
    @GetMapping("/search")
    public List<Tournament> searchTournaments(
            @RequestParam(required = false) String tourneyLocal,
            @RequestParam(required = false) String tourneyStartDate
    ) {
        if (tourneyLocal != null) {
            return tourneyRepo.findByTourneyLocal(tourneyLocal);
        } else if (tourneyStartDate != null) {
            return tourneyRepo.findByTourneyStartDate(LocalDate.parse(tourneyStartDate));
        }
        return tourneyRepo.findAll();
    }
}
