package com.golfclub.golfclub_api.controller;

// IMPORTS (only added due to length/size of import section
import com.golfclub.golfclub_api.Member;
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
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepo memRepo; // use this to inject repos for accessing database

    /* --------------- */
    @Autowired
    private TournamentRepo tourneyRepo;

    /* --------------- */
    @PostMapping
    public Member createMem(@RequestBody Member member) { // creates member
        return memRepo.save(member);
    }

    /* --------------- */
    @GetMapping
    public List<Member> getAllMems() { // obtain all members in tournament
        return memRepo.findAll();
    }

    /* --------------- */
    @GetMapping("/search") // searches for membs by name, number or start date (these are optional query params)
    public List<Member> searchMems(
            @RequestParam(required = false) String memName,
            @RequestParam(required = false) String memPhone,
            @RequestParam(required = false) String memStartDate
    ) {
        if (memName != null) {
            return memRepo.findByMemNameContainingIgnoreCase(memName);
        } else if (memPhone != null) {
            return memRepo.findByMemPhone(memPhone);
        } else if (memStartDate != null) {
            return memRepo.findByMemStartDate(LocalDate.parse(memStartDate));
        }
        return memRepo.findAll();
    }

    /* --------------- */
    // Add members to tournament via their ID's
    @PostMapping("/{memberId}/tournaments/{tournamentId}")
    public Member addMemToTourney(@PathVariable Long memberId, @PathVariable Long tournamentId) {
        Optional<Member> memberOpt = memRepo.findById(memberId);
        Optional<Tournament> tourneyOpt = tourneyRepo.findById(tournamentId);

        if (memberOpt.isPresent() && tourneyOpt.isPresent()) {
            Member member = memberOpt.get();
            Tournament tournament = tourneyOpt.get();

            // establish a two way ManyToMany relationship
            member.getTournaments().add(tournament);
            tournament.getParticipants().add(member);

            // save updates on both ends
            tourneyRepo.save(tournament);
            return memRepo.save(member);
        } else {
            throw new RuntimeException("Member or Tournament not found");
        }
    }
}
