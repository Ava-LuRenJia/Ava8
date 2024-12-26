package service;

import dao.EnrollmentInfoDAO;
import entity.EnrollmentInfo;

import java.util.Date;
import java.util.List;

public class EnrollmentInfoService {

    // dao层对象实例化
    EnrollmentInfoDAO dao = new EnrollmentInfoDAO();

    /**
     * 添加招考信息
     *
     * @param enrollmentAdminId 招生管理员ID
     * @param examName          考试名称
     * @param startTime         报名开始时间
     * @param endTime           报名结束时间
     * @param examDate          考试日期
     * @param admissionLine     录取分数线
     * @return 是否添加成功
     */
    public boolean add(int enrollmentAdminId, String examName, Date startTime, Date endTime, Date examDate, double admissionLine) {
        EnrollmentInfo enrollmentInfo = new EnrollmentInfo();

        enrollmentInfo.setEnrollmentAdminId(enrollmentAdminId);
        enrollmentInfo.setExamName(examName);
        enrollmentInfo.setStartTime(startTime);
        enrollmentInfo.setEndTime(endTime);
        enrollmentInfo.setExamDate(examDate);
        enrollmentInfo.setAdmissionLine(admissionLine);

        if (dao.add(enrollmentInfo)) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有招考信息
     *
     * @return 招考信息列表
     */
    public List<EnrollmentInfo> list() {
        return dao.list();
    }

    /**
     * 修改招考信息
     *
     * @param id                招考信息ID
     * @param enrollmentAdminId 招生管理员ID
     * @param examName          考试名称
     * @param startTime         报名开始时间
     * @param endTime           报名结束时间
     * @param examDate          考试日期
     * @param admissionLine     录取分数线
     */
    public void update(int id, int enrollmentAdminId, String examName, Date startTime, Date endTime, Date examDate, double admissionLine) {
        EnrollmentInfo enrollmentInfo = new EnrollmentInfo();

        enrollmentInfo.setEnrollmentInfoId(id);
        enrollmentInfo.setEnrollmentAdminId(enrollmentAdminId);
        enrollmentInfo.setExamName(examName);
        enrollmentInfo.setStartTime(startTime);
        enrollmentInfo.setEndTime(endTime);
        enrollmentInfo.setExamDate(examDate);
        enrollmentInfo.setAdmissionLine(admissionLine);

        dao.update(enrollmentInfo);
    }

    /**
     * 根据招考信息ID删除招考信息
     *
     * @param id 招考信息ID
     */
    public void delete(int id) {
        dao.delete(id);
    }

    public List<EnrollmentInfo> searchByExamName(String examName) {
        return dao.searchByExamName(examName);
    }

    public void deleteByAdminId(int enrollmentAdminId) {
        List<Integer> relatedIds = dao.findIdsByAdminId(enrollmentAdminId);
        for (Integer id : relatedIds) {
            dao.delete(id);
        }

    }
}
