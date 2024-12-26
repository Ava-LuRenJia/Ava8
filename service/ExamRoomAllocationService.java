package service;

import dao.ExamRoomAllocationDAO;
import entity.ExamRoomAllocation;

import java.util.List;

public class ExamRoomAllocationService {

    // dao层对象实例化
    ExamRoomAllocationDAO dao = new ExamRoomAllocationDAO();

    /**
     * 添加考场分配信息
     *
     * @param examineeId  考生ID
     * @param examRoomNumber 考场编号
     * @param seatNumber  座位号
     * @return 是否添加成功
     */
    public boolean add(int examineeId, String examRoomNumber, String seatNumber) {
        ExamRoomAllocation examRoomAllocation = new ExamRoomAllocation();

        examRoomAllocation.setExamineeId(examineeId);
        examRoomAllocation.setExamRoomNumber(examRoomNumber);
        examRoomAllocation.setSeatNumber(seatNumber);

        if (dao.add(examRoomAllocation)) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有考场分配信息
     *
     * @return 考场分配信息列表
     */
    public List<ExamRoomAllocation> list() {
        return dao.list();
    }

    /**
     * 修改考场分配信息
     *
     * @param id              考场分配信息ID
     * @param examineeId  考生ID
     * @param examRoomNumber 考场编号
     * @param seatNumber  座位号
     */
    public void update(int id, int examineeId, String examRoomNumber, String seatNumber) {
        ExamRoomAllocation examRoomAllocation = new ExamRoomAllocation();

        examRoomAllocation.setAllocationId(id);
        examRoomAllocation.setExamineeId(examineeId);
        examRoomAllocation.setExamRoomNumber(examRoomNumber);
        examRoomAllocation.setSeatNumber(seatNumber);

        dao.update(examRoomAllocation);
    }

    /**
     * 根据考场分配信息ID删除考场分配信息
     *
     * @param id 考场分配信息ID
     */
    public void delete(int id) {
        dao.delete(id);
    }
}
