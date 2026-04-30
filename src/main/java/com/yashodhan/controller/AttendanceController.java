package com.yashodhan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yashodhan.dto.AttendanceDTO;
import com.yashodhan.dto.LocationRequest;
import com.yashodhan.entity.Attendance;
import com.yashodhan.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
//@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/checkin/{employeeId}")
    public Attendance checkIn(
            @PathVariable int employeeId,
            @RequestBody LocationRequest location) {

        return attendanceService.checkIn(employeeId, location);
    }

    @PutMapping("/checkout/{attendanceId}")
    public Attendance checkOut(
            @PathVariable int attendanceId,
            @RequestBody LocationRequest location) {

        return attendanceService.checkOut(attendanceId, location);
    }
    
    @GetMapping("/history/{employeeId}")
    public List<Attendance> getAttendanceHistory(
            @PathVariable Long employeeId,
            @RequestParam int month) {

        return attendanceService.getAttendanceByEmployeeAndMonth(employeeId, month);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }
    
    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        return attendanceService.getDashboardStats();
    }
    
    // TODAY STATUS
    @GetMapping("/today/{employeeId}")
    public Attendance getToday(@PathVariable int employeeId) {
        return attendanceService.getTodayAttendance(employeeId);
    }
}