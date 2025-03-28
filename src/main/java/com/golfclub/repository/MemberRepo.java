package com.golfclub.repository;

import com.golfclub.golfclub_api.Member;  // Correct import statement
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    List<Member> findByMemNameContainingIgnoreCase(String memName);
    List<Member> findByMemPhone(String memPhone);
    List<Member> findByMemStartDate(LocalDate memStartDate);
}
