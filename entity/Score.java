package entity;
import java.util.*;
public class Score {

    private int scoreId;
    private int examineeId;
    private String subject;
    private double scoreValue;
    private Date examTime;

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getExamineeId() {
        return examineeId;
    }

    public void setExamineeId(int examineeId) {
        this.examineeId = examineeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(double scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }
}
