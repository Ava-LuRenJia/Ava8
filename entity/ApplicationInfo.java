package entity;

import java.util.Date;

public class ApplicationInfo {
    private int applicationId;
    private int examineeId;
    private Date applicationTime;
    private String examType;
    private int confirmed;

    // 生成对应的Getter和Setter方法
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getExamineeId() {
        return examineeId;
    }

    public void setExamineeId(int examineeId) {
        this.examineeId = examineeId;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }
}
