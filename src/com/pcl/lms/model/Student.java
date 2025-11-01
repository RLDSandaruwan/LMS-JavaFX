package com.pcl.lms.model;

import java.util.Date;

public class Student {
    private String id;
    private String name;
    private String studentAddress;
    private Date DOB;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", studentAddress='" + getStudentAddress() + '\'' +
                ", DOB=" + getDOB() +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public Date getDOB() {
        return DOB;
    }

    public Student(String id, String name, String studentAddress, Date DOB) {
        this.setId(id);
        this.setName(name);
        this.setStudentAddress(studentAddress);
        this.setDOB(DOB);
    }

    public Student() {
    }
}
