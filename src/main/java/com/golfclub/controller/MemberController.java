package com.golfclub.controller;

// IMPORTS (only added due to length/size of import section)
import com.golfclub.ResourceNotFoundException;
import com.golfclub.golfclub_api.Member;
import com.golfclub.golfclub_api.Tournament;
import com.golfclub.repository.MemberRepo;
import com.golfclub.repository.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Member> createMem(@RequestBody Member member) { // creates member
        Member savedMember = memRepo.save(member);
        return ResponseEntity.ok(savedMember);
    }

    /* --------------- */
    @GetMapping
    public ResponseEntity<List<Member>> getAllMems() {
        List<Member> members = memRepo.findAll();
        return ResponseEntity.ok(members);
    }

    /* --------------- */
    @GetMapping("/search-by-name")
    public ResponseEntity<List<Member>> searchByName(@RequestParam String name) {
        List<Member> members = memRepo.findByMemNameContainingIgnoreCase(name);
        return ResponseEntity.ok(members);
    }

    /* --------------- */
    @GetMapping("/search") // searches for membs by name, number or start date (these are optional query params)
    public ResponseEntity<List<Member>> searchMems(
            @RequestParam(required = false) Optional<String> memName,
            @RequestParam(required = false) Optional<String> memPhone,
            @RequestParam(required = false) Optional<String> memStartDate
    ) {
        List<Member> result;
        if (memName.isPresent()) {
            result = memRepo.findByMemNameContainingIgnoreCase(memName.get());
        } else if (memPhone.isPresent()) {
            result = memRepo.findByMemPhone(memPhone.get());
        } else if (memStartDate.isPresent()) {
            result = memRepo.findByMemStartDate(LocalDate.parse(memStartDate.get()));
        } else {
            result = memRepo.findAll();
        }
        return ResponseEntity.ok(result);
    }

    /* --------------- */
    // Add members to tournament via their ID's
    @PostMapping("/{memberId}/tournaments/{tournamentId}")
    public ResponseEntity<Member> addMemToTourney(@PathVariable Long memberId, @PathVariable Long tournamentId) {
        Member member = memRepo.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + memberId));
        Tournament tournament = tourneyRepo.findById(tournamentId)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with ID: " + tournamentId));

        member.getTournaments().add(tournament);
        tournament.getParticipants().add(member);

        tourneyRepo.save(tournament);
        Member updatedMember = memRepo.save(member);
        return ResponseEntity.ok(updatedMember);
    }
}
