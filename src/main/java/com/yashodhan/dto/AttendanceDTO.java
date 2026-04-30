package com.yashodhan.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceDTO {
	    private String employeeName;
	    private LocalDate attendanceDate;
	    private LocalDateTime checkInTime;
	    private LocalDateTime checkOutTime;

	    // NEW FIELDS
	    private Double checkInLatitude;
	    private Double checkInLongitude;
	    private Double checkOutLatitude;
	    private Double checkOutLongitude;

	    public AttendanceDTO(String employeeName,
	                         LocalDate attendanceDate,
	                         LocalDateTime checkInTime,
	                         LocalDateTime checkOutTime,
	                         Double checkInLatitude,
	                         Double checkInLongitude,
	                         Double checkOutLatitude,
	                         Double checkOutLongitude) {

	        this.employeeName = employeeName;
	        this.attendanceDate = attendanceDate;
	        this.checkInTime = checkInTime;
	        this.checkOutTime = checkOutTime;

	        this.checkInLatitude = checkInLatitude;
	        this.checkInLongitude = checkInLongitude;
	        this.checkOutLatitude = checkOutLatitude;
	        this.checkOutLongitude = checkOutLongitude;
	    }

	    // getters & setters
	
		public String getEmployeeName() {
			return employeeName;
		}

		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}

		public LocalDate getAttendanceDate() {
			return attendanceDate;
		}

		public void setAttendanceDate(LocalDate attendanceDate) {
			this.attendanceDate = attendanceDate;
		}

		public LocalDateTime getCheckInTime() {
			return checkInTime;
		}

		public void setCheckInTime(LocalDateTime checkInTime) {
			this.checkInTime = checkInTime;
		}

		public LocalDateTime getCheckOutTime() {
			return checkOutTime;
		}

		public void setCheckOutTime(LocalDateTime checkOutTime) {
			this.checkOutTime = checkOutTime;
		}

		public Double getCheckInLatitude() {
			return checkInLatitude;
		}

		public void setCheckInLatitude(Double checkInLatitude) {
			this.checkInLatitude = checkInLatitude;
		}

		public Double getCheckInLongitude() {
			return checkInLongitude;
		}

		public void setCheckInLongitude(Double checkInLongitude) {
			this.checkInLongitude = checkInLongitude;
		}

		public Double getCheckOutLatitude() {
			return checkOutLatitude;
		}

		public void setCheckOutLatitude(Double checkOutLatitude) {
			this.checkOutLatitude = checkOutLatitude;
		}

		public Double getCheckOutLongitude() {
			return checkOutLongitude;
		}

		public void setCheckOutLongitude(Double checkOutLongitude) {
			this.checkOutLongitude = checkOutLongitude;
		}		
	    
}