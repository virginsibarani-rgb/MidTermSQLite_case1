package com.example.midtermsqlite;

public class StudentModel {

    Integer id;
    String studentName;
    String studentID;
    String major;

    public StudentModel(String studentName, String studentID, String major) {
        this.studentName = studentName;
        this.studentID = studentID;
        this.major = major;
    }

    public StudentModel(Integer id, String studentName, String studentID, String major) {
        this.id = id;
        this.studentName = studentName;
        this.studentID = studentID;
        this.major = major;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}