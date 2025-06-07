package com.drugstore.Service;

import com.drugstore.DTO.AttendanceRequestDTO;
import com.drugstore.DTO.AttendanceResponseDTO;

import java.util.List;

public interface AttendanceService {
    AttendanceResponseDTO markAttendance(AttendanceRequestDTO requestDTO);
    List<AttendanceResponseDTO> getAllAttendance();
    List<AttendanceResponseDTO> getAttendanceByUserId(Long userId);
	void checkOut(Long userId);
	void checkIn(Long userId);
}
