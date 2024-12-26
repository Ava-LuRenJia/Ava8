package entity;

import java.util.Date;

public class AdmissionInfo {

    private int admissionId;
    private int examineeId;
    private boolean isAdmitted;
    private Date admissionTime;

    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public int getExamineeId() {
        return examineeId;
    }

    public void setExamineeId(int examineeId) {
        this.examineeId = examineeId;
    }

    public boolean isAdmitted() {
        return isAdmitted;
    }

    public void setAdmitted(boolean isAdmitted) {
        this.isAdmitted = isAdmitted;
    }

    public Date getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Date admissionTime) {
        this.admissionTime = admissionTime;
    }
}
