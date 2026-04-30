package com.yashodhan.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yashodhan.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer > {

    List<Attendance> findByEmployeeId(int employeeId);

    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId AND MONTH(a.attendanceDate) = :month")
    List<Attendance> findAttendanceByEmployeeAndMonth(
            @Param("employeeId") Long employeeId,
            @Param("month") int month);
    

    @Query(value = """
    	    SELECT 
    	        u.firstname || ' ' ||
    	        NVL(u.middlename, '') || ' ' ||
    	        u.lastname AS employeeName,

    	        a.attendance_date,
    	        a.check_in_time,
    	        a.check_out_time,

    	        a.check_in_latitude,
    	        a.check_in_longitude,
    	        a.check_out_latitude,
    	        a.check_out_longitude

    	    FROM attendance a
    	    JOIN users u ON a.employee_id = u.id
    	""", nativeQuery = true)
    	List<Object[]> getAllAttendanceRaw();
    	
    	 @Query("SELECT COUNT(a) FROM Attendance a WHERE a.attendanceDate = :today")
    	    long countPresentToday(@Param("today") LocalDate today);

    	    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.attendanceDate = :today AND a.checkInTime IS NOT NULL")
    	    long countCheckedInToday(@Param("today") LocalDate today);

    	    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.attendanceDate = :today AND a.checkOutTime IS NOT NULL")
    	    long countCheckedOutToday(@Param("today") LocalDate today);
    	
    	    Optional<Attendance> findByEmployeeIdAndAttendanceDate(
    	            int employeeId,
    	            LocalDate attendanceDate
    	    );
}