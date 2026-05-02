package com.yashodhan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yashodhan.dto.AttendanceDTO;
import com.yashodhan.dto.EmployeeSummaryDTO;
import com.yashodhan.entity.Attendance;
import com.yashodhan.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
//@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping(
    	    value = "/checkin/{employeeId}",
    	    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    	)
    	public Attendance checkIn(
    	        @PathVariable int employeeId,
    	        @RequestParam("latitude") Double latitude,
    	        @RequestParam("longitude") Double longitude,
    	        @RequestParam("selfie") MultipartFile selfie
    	) {

    	    return attendanceService.checkIn(employeeId, latitude, longitude, selfie);
    	}

    @PutMapping(value="/checkout/{attendanceId}",
    	    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Attendance checkOut(
            @PathVariable int attendanceId,
            @RequestParam("latitude") Double latitude,
	        @RequestParam("longitude") Double longitude,
            @RequestParam MultipartFile selfie) {

        return attendanceService.checkOut(attendanceId, latitude, longitude, selfie);
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
    
    @PostMapping("/delete/{attendanceId}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Integer attendanceId) {

        attendanceService.deleteAttendance(attendanceId);

        return ResponseEntity.ok("Attendance deleted successfully");
    }
    
    @GetMapping("/stats/details/{type}")
    public List<EmployeeSummaryDTO> getStatsDetails(
            @PathVariable String type) {

        return attendanceService.getEmployeesByType(type);
    }
}