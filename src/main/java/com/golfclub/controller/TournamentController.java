package com.golfclub.controller;

import com.golfclub.ResourceNotFoundException;
import com.golfclub.golfclub_api.Member;
import com.golfclub.golfclub_api.Tournament;
import com.golfclub.repository.MemberRepo;
import com.golfclub.repository.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

/* ------------------------------------------- */
// Controller settings
@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentRepo tourneyRepo;

    /* ----------------------------------------- */
    @Autowired
    private MemberRepo memberRepo;

    /* ----------------------------------------- */
    // POST method to create a tournament
    @PostMapping
    public Tournament createTourney(@RequestBody Tournament tournament) {
        return tourneyRepo.save(tournament);
    }

    /* ----------------------------------------- */
    // GET method to get all tournaments
    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tourneyRepo.findAll();
    }

    /* ----------------------------------------- */
    // GET method to search tournaments by location or start date
    @GetMapping("/search")
    public List<Tournament> searchTournaments(
            @RequestParam(required = false) String tourneyLocal,
            @RequestParam(required = false) LocalDate tourneyStartDate
    ) {
        if (tourneyLocal != null) {
            return tourneyRepo.findByTourneyLocal(tourneyLocal);
        } else if (tourneyStartDate != null) {
            return tourneyRepo.findByTourneyStartDate(tourneyStartDate);
        }
        return tourneyRepo.findAll();
    }

    /* ----------------------------------------- */
    // GET method to fetch all members in a specific tournament
    @GetMapping("/{tournamentId}/members")
    public List<Member> getMembersInTournament(@PathVariable Long tournamentId) {
        Tournament tournament = tourneyRepo.findById(tournamentId)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with ID: " + tournamentId));

        return List.copyOf(tournament.getParticipants());
    }

    /* ----------------------------------------- */
    // POST method to add a member to a tournament
    @PostMapping("/{tournamentId}/add-member/{memberId}")
    public Tournament addMemberToTournament(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {
        Tournament tournament = tourneyRepo.findById(tournamentId)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with ID: " + tournamentId));
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + memberId));
        tournament.addParticipant(member);
        return tourneyRepo.save(tournament);
    }
}