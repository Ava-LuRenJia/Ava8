package entity;

import java.util.Date;

public class EnrollmentInfo {

    private int enrollmentInfoId;
    private int enrollmentAdminId;
    private String examName;
    private Date startTime;
    private Date endTime;
    private Date examDate;
    private double admissionLine;

    public int getEnrollmentInfoId() {
        return enrollmentInfoId;
    }

    public void setEnrollmentInfoId(int enrollmentInfoId) {
        this.enrollmentInfoId = enrollmentInfoId;
    }

    public int getEnrollmentAdminId() {
        return enrollmentAdminId;
    }

    public void setEnrollmentAdminId(int enrollmentAdminId) {
        this.enrollmentAdminId = enrollmentAdminId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public double getAdmissionLine() {
        return admissionLine;
    }

    public void setAdmissionLine(double admissionLine) {
        this.admissionLine = admissionLine;
    }
}
