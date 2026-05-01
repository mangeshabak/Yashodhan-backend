package com.yashodhan.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_seq")
    @SequenceGenerator(
        name = "attendance_seq",
        sequenceName = "attendance_seq",
        allocationSize = 1
    )
    private int id;

    private int employeeId;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private LocalDate attendanceDate;

    private String status;
    
    // NEW FIELDS
    private Double checkInLatitude;
    private Double checkInLongitude;

    private Double checkOutLatitude;
    private Double checkOutLongitude;
    
    
    private byte[] checkInSelfie;

    private byte[] checkOutSelfie;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public byte[] getCheckInSelfie() {
		return checkInSelfie;
	}

	public void setCheckInSelfie(byte[] checkInSelfie) {
		this.checkInSelfie = checkInSelfie;
	}

	public byte[] getCheckOutSelfie() {
		return checkOutSelfie;
	}

	public void setCheckOutSelfie(byte[] checkOutSelfie) {
		this.checkOutSelfie = checkOutSelfie;
	}



    // getters setters
    
    
}