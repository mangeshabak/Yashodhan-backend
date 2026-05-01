package com.yashodhan.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

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
	    
	    private byte[] checkInSelfie;
	    private byte[] checkOutSelfie;

	    

	    public AttendanceDTO(String employeeName, LocalDate attendanceDate, LocalDateTime checkInTime,
				LocalDateTime checkOutTime, Double checkInLatitude, Double checkInLongitude, Double checkOutLatitude,
				Double checkOutLongitude, byte[] checkInSelfie, byte[] checkOutSelfie) {
			super();
			this.employeeName = employeeName;
			this.attendanceDate = attendanceDate;
			this.checkInTime = checkInTime;
			this.checkOutTime = checkOutTime;
			this.checkInLatitude = checkInLatitude;
			this.checkInLongitude = checkInLongitude;
			this.checkOutLatitude = checkOutLatitude;
			this.checkOutLongitude = checkOutLongitude;
			this.checkInSelfie = checkInSelfie;
			this.checkOutSelfie = checkOutSelfie;
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

		public String getCheckInSelfieBase64() {
		    if (checkInSelfie == null) return null;
		    return Base64.getEncoder().encodeToString(checkInSelfie);
		}

		public void setCheckInSelfie(byte[] checkInSelfie) {
			this.checkInSelfie = checkInSelfie;
		}


		public String getCheckOutSelfieBase64() {
		    if (checkOutSelfie == null) return null;
		    return Base64.getEncoder().encodeToString(checkOutSelfie);
		}

		public void setCheckOutSelfie(byte[] checkOutSelfie) {
			this.checkOutSelfie = checkOutSelfie;
		}		
	    
}