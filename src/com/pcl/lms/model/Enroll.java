package com.pcl.lms.model;

public class Enroll {
    private String student;
    private String progarm;
    private Boolean isPaid;

    @Override
    public String toString() {
        return "Enroll{" +
                "student='" + student + '\'' +
                ", progarm='" + progarm + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }

    public Enroll() {
    }

    public Enroll(String student, String progarm, Boolean isPaid) {
        this.student = student;
        this.progarm = progarm;
        this.isPaid = isPaid;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getProgarm() {
        return progarm;
    }

    public void setProgarm(String progarm) {
        this.progarm = progarm;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
