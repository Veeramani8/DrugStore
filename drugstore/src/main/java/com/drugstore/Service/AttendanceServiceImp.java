package com.drugstore.Service;

import com.drugstore.DTO.AttendanceRequestDTO;
import com.drugstore.DTO.AttendanceResponseDTO;
import com.drugstore.Model.Attendance;
import com.drugstore.Model.User;
import com.drugstore.Repository.AttendanceRepository;
import com.drugstore.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImp implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AttendanceResponseDTO markAttendance(AttendanceRequestDTO dto) {
        Optional<User> userOpt = userRepository.findById(dto.getUserId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOpt.get();

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(dto.getDate());
        attendance.setCheckIn(dto.getCheckIn());
        attendance.setCheckOut(dto.getCheckOut());

        if (dto.getCheckIn() != null && dto.getCheckOut() != null) {
            Duration total = Duration.between(dto.getCheckIn(), dto.getCheckOut());
            attendance.setTotalHours(total);
        }

        Attendance saved = attendanceRepository.save(attendance);
        return convertToDTO(saved);
    }

    @Override
    public List<AttendanceResponseDTO> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceByUserId(Long userId) {
        return attendanceRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AttendanceResponseDTO convertToDTO(Attendance attendance) {
        AttendanceResponseDTO dto = new AttendanceResponseDTO();
        dto.setId(attendance.getId());
        dto.setUserId(attendance.getUser().getId());
        dto.setFullName(attendance.getUser().getFullName());
        dto.setDate(attendance.getDate());
        dto.setCheckIn(attendance.getCheckIn());
        dto.setCheckOut(attendance.getCheckOut());
        dto.setTotalHours(attendance.getTotalHours());
        return dto;
    }
    @Override
    public void checkIn(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate today = LocalDate.now();
        Optional<User> existing = attendanceRepository.findByUserIdAndDate(userId, today);
        if (existing.isPresent()) {
            throw new RuntimeException("Already checked in today");
        }

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(today);
        attendance.setCheckIn(LocalTime.now());
        attendanceRepository.save(attendance);
    }

    @Override
    public void checkOut(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Attendance attendance = new Attendance();
        attendance.setUser(user);                
        attendance.setDate(LocalDate.now());
        attendance.setCheckIn(LocalTime.now());

        if (attendance.getCheckOut() != null) {
            throw new RuntimeException("Already checked out today");
        }

        LocalTime now = LocalTime.now();
        attendance.setCheckOut(now);
        Duration total = Duration.between(attendance.getCheckIn(), now);
        attendance.setTotalHours(total);

        attendanceRepository.save(attendance);
    }
}
