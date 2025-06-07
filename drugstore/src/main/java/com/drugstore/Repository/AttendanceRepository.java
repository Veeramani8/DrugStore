package com.drugstore.Repository;

import com.drugstore.Model.Attendance;
import com.drugstore.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByUserId(Long userId);

	Optional<User> findByUserIdAndDate(Long userId, LocalDate today);
}
