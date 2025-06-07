package com.drugstore.Controller;

import com.drugstore.DTO.AttendanceRequestDTO;
import com.drugstore.DTO.AttendanceResponseDTO;
import com.drugstore.Model.User;
import com.drugstore.Service.AttendanceService;
import com.drugstore.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllAttendance(Model model) {
        List<AttendanceResponseDTO> list = attendanceService.getAllAttendance();
        model.addAttribute("attendances", list);
        return "attendance-list"; 
    }

    @GetMapping("/my")
    public String getUserAttendance(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        List<AttendanceResponseDTO> list = attendanceService.getAttendanceByUserId(user.getId());
        model.addAttribute("attendances", list);
        return "attendance/my";
    }

    @PostMapping("/checkin")
    public String checkIn(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        attendanceService.checkIn(user.getId());
        return "redirect:/attendance/my";
    }

    @PostMapping("/checkout")
    public String checkOut(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        attendanceService.checkOut(user.getId());
        return "redirect:/attendance/my";
    }
    @GetMapping("/mark")
    public String showMarkAttendanceForm(Model model) {
        model.addAttribute("attendanceRequestDTO", new AttendanceRequestDTO());
        return "attendance-form";
    }

    @PostMapping("/save")
    public String saveAttendance(@ModelAttribute("attendanceRequest") AttendanceRequestDTO dto) {
        attendanceService.markAttendance(dto);
        return "redirect:/attendance"; 
    }

}
