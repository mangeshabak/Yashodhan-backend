package com.yashodhan.dto;

public class EmployeeSummaryDTO {

    private int id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String status;

    public EmployeeSummaryDTO() {
    }

    public EmployeeSummaryDTO(
            int id,
            String firstname,
            String middlename,
            String lastname,
            String status
    ) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}