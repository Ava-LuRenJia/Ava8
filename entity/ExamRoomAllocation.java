package entity;

public class ExamRoomAllocation {

    private int allocationId;
    private int examineeId;
    private String examRoomNumber;
    private String seatNumber;

    public int getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(int allocationId) {
        this.allocationId = allocationId;
    }

    public int getExamineeId() {
        return examineeId;
    }

    public void setExamineeId(int examineeId) {
        this.examineeId = examineeId;
    }

    public String getExamRoomNumber() {
        return examRoomNumber;
    }

    public void setExamRoomNumber(String examRoomNumber) {
        this.examRoomNumber = examRoomNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
