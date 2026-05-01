package com.yashodhan.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yashodhan.dto.AttendanceDTO;
import com.yashodhan.dto.LocationRequest;
import com.yashodhan.entity.Attendance;
import com.yashodhan.repository.AttendanceRepository;
import com.yashodhan.repository.UserRepository;


@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private UserRepository userRepository;
	
	public Attendance checkIn(int employeeId, Double latitude, Double longitude, MultipartFile selfie) {

		 LocalDate today = LocalDate.now();

		    Optional<Attendance> existingAttendance =
		            attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);

		    // Already checked in today
		    if (existingAttendance.isPresent()) {
		        throw new RuntimeException("Already checked in today");
		    }
		    
	    Attendance attendance = new Attendance();

	    attendance.setEmployeeId(employeeId);
	    attendance.setCheckInTime(LocalDateTime.now());
	    attendance.setAttendanceDate(LocalDate.now());
	    attendance.setStatus("Present");

	    // SAVE LOCATION
	    attendance.setCheckInLatitude(latitude);
	    attendance.setCheckInLongitude(longitude);

	    try {
	        attendance.setCheckInSelfie(selfie.getBytes());
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to save selfie");
	    }
	    return attendanceRepository.save(attendance);
	}
	
	public Attendance checkOut(int attendanceId, Double latitude, Double longitude, MultipartFile selfie) {

		  Attendance attendance = attendanceRepository.findById(attendanceId)
		            .orElseThrow(() -> new RuntimeException("Attendance not found"));

		    // Already checked out
		    if (attendance.getCheckOutTime() != null) {
		        throw new RuntimeException("Already checked out today");
		    }
		    
	    attendance.setCheckOutTime(LocalDateTime.now());

	    // SAVE LOCATION
	    attendance.setCheckOutLatitude(latitude);
	    attendance.setCheckOutLongitude(longitude);

	    try {
	        attendance.setCheckOutSelfie(selfie.getBytes());
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to save selfie");
	    }
	    return attendanceRepository.save(attendance);
	}

	public List<Attendance> getAttendanceByEmployeeAndMonth(Long employeeId, int month) {
		return attendanceRepository.findAttendanceByEmployeeAndMonth(employeeId, month);
	}

	
	public List<AttendanceDTO> getAllAttendance() {

	    List<Object[]> results = attendanceRepository.getAllAttendanceRaw();
	    List<AttendanceDTO> list = new ArrayList<>();

	    for (Object[] row : results) {

	        if (row == null) continue;

	        // ---------------- EMPLOYEE NAME ----------------
	        String employeeName = row[0] != null ? String.valueOf(row[0]) : null;

	        // ---------------- DATE ----------------
	        LocalDate date = null;
	        try {
	            if (row[1] instanceof java.sql.Date) {
	                date = ((java.sql.Date) row[1]).toLocalDate();
	            } else if (row[1] instanceof java.sql.Timestamp) {
	                date = ((java.sql.Timestamp) row[1]).toLocalDateTime().toLocalDate();
	            } else if (row[1] instanceof java.time.LocalDate) {
	                date = (LocalDate) row[1];
	            } else if (row[1] instanceof java.time.LocalDateTime) {
	                date = ((LocalDateTime) row[1]).toLocalDate();
	            } else if (row[1] != null) {
	                date = LocalDate.parse(row[1].toString());
	            }
	        } catch (Exception e) {
	            date = null;
	        }

	        // ---------------- CHECK-IN ----------------
	        LocalDateTime checkIn = null;
	        try {
	            if (row[2] instanceof LocalDateTime) {
	                checkIn = (LocalDateTime) row[2];
	            } else if (row[2] instanceof java.sql.Timestamp) {
	                checkIn = ((java.sql.Timestamp) row[2]).toLocalDateTime();
	            } else if (row[2] != null) {
	                checkIn = LocalDateTime.parse(row[2].toString());
	            }
	        } catch (Exception e) {
	            checkIn = null;
	        }

	        // ---------------- CHECK-OUT ----------------
	        LocalDateTime checkOut = null;
	        try {
	            if (row[3] instanceof LocalDateTime) {
	                checkOut = (LocalDateTime) row[3];
	            } else if (row[3] instanceof java.sql.Timestamp) {
	                checkOut = ((java.sql.Timestamp) row[3]).toLocalDateTime();
	            } else if (row[3] != null) {
	                checkOut = LocalDateTime.parse(row[3].toString());
	            }
	        } catch (Exception e) {
	            checkOut = null;
	        }

	        // ---------------- LAT/LNG SAFE CONVERSION ----------------
	        Double checkInLat = toDouble(row[4]);
	        Double checkInLng = toDouble(row[5]);
	        Double checkOutLat = toDouble(row[6]);
	        Double checkOutLng = toDouble(row[7]);
	        byte[] checkInSelfie = null;
	        byte[] checkOutSelfie = null;

	        try {
	            if (row[8] != null) {
	                if (row[8] instanceof byte[]) {
	                    checkInSelfie = (byte[]) row[8];
	                } else if (row[8] instanceof java.sql.Blob) {
	                    java.sql.Blob blob = (java.sql.Blob) row[8];
	                    checkInSelfie = blob.getBytes(1, (int) blob.length());
	                }
	            }

	            if (row[9] != null) {
	                if (row[9] instanceof byte[]) {
	                    checkOutSelfie = (byte[]) row[9];
	                } else if (row[9] instanceof java.sql.Blob) {
	                    java.sql.Blob blob = (java.sql.Blob) row[9];
	                    checkOutSelfie = blob.getBytes(1, (int) blob.length());
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        list.add(new AttendanceDTO(
	                employeeName,
	                date,
	                checkIn,
	                checkOut,
	                checkInLat,
	                checkInLng,
	                checkOutLat,
	                checkOutLng,
	                checkInSelfie,
	                checkOutSelfie
	        ));
	    }

	    return list;
	}
	private Double toDouble(Object obj) {
	    if (obj == null) return null;

	    try {
	        if (obj instanceof Number) {
	            return ((Number) obj).doubleValue();
	        }
	        return Double.parseDouble(obj.toString());
	    } catch (Exception e) {
	        return null;
	    }
	}
	 public Map<String, Object> getDashboardStats() {

	        Map<String, Object> stats = new HashMap<>();

	        LocalDate today = LocalDate.now();
	        
	        long totalEmployees = userRepository.count();

	        long presentToday = attendanceRepository.countPresentToday(today);

	        long checkedIn = attendanceRepository.countCheckedInToday(today);

	        long checkedOut = attendanceRepository.countCheckedOutToday(today);

	        long absentToday = totalEmployees - presentToday;

	        stats.put("totalEmployees", totalEmployees);
	        stats.put("presentToday", presentToday);
	        stats.put("absentToday", absentToday);
	        stats.put("checkedIn", checkedIn);
	        stats.put("checkedOut", checkedOut);

	        return stats;
	    }
	 
	   // ---------------- TODAY ATTENDANCE ----------------
	    public Attendance getTodayAttendance(int employeeId) {
	        return attendanceRepository
	                .findByEmployeeIdAndAttendanceDate(employeeId, LocalDate.now())
	                .orElse(null);
	    }
	    
	    public void deleteAttendance(Integer attendanceId) {
	        attendanceRepository.deleteById(attendanceId);
	    }

		
}