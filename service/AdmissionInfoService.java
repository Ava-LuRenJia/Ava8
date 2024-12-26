package service;

import dao.AdmissionInfoDAO;
import entity.AdmissionInfo;

import java.util.Date;
import java.util.List;

public class AdmissionInfoService {

    // dao层对象实例化
    AdmissionInfoDAO dao = new AdmissionInfoDAO();

    /**
     * 添加录取信息
     *
     * @param examineeId  考生ID
     * @param isAdmitted  是否被录取
     * @param admissionTime 录取时间
     * @return 是否添加成功
     */
    public boolean add(int examineeId, boolean isAdmitted, Date admissionTime) {
        AdmissionInfo admissionInfo = new AdmissionInfo();

        admissionInfo.setExamineeId(examineeId);
        admissionInfo.setAdmitted(isAdmitted);
        admissionInfo.setAdmissionTime(admissionTime);

        if (dao.add(admissionInfo)) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有录取信息
     *
     * @return 录取信息列表
     */
    public List<AdmissionInfo> list() {
        return dao.list();
    }

    /**
     * 修改录取信息
     *
     * @param id          录取信息ID
     * @param examineeId  考生ID
     * @param isAdmitted  是否被录取
     * @param admissionTime 录取时间
     */
    public void update(int id, int examineeId, boolean isAdmitted, Date admissionTime) {
        AdmissionInfo admissionInfo = new AdmissionInfo();

        admissionInfo.setAdmissionId(id);
        admissionInfo.setExamineeId(examineeId);
        admissionInfo.setAdmitted(isAdmitted);
        admissionInfo.setAdmissionTime(admissionTime);

        dao.update(admissionInfo);
    }

    /**
     * 根据录取信息ID删除录取信息
     *
     * @param id 录取信息ID
     */
    public void delete(int id) {
        dao.delete(id);
    }
}