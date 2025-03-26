package com.golfclub.repository;

import com.golfclub.golfclub_api.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, Long> {
    List<Tournament> findByTourneyStartDate(LocalDate tourneyStartDate);
    List<Tournament> findByTourneyLocal(String tourneyLocal);
}
