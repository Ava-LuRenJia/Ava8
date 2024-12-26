package service;

import dao.ApplicationInfoDAO;
import entity.ApplicationInfo;
import java.util.Date;
import java.util.List;

public class ApplicationInfoService {

    private ApplicationInfoDAO dao = new ApplicationInfoDAO();

    /**
     * 添加报考信息
     *
     * @param examineeId 报考考生ID
     * @param applicationTime 报考时间
     * @param examType 报考考试类型
     * @return 是否添加成功
     */
    public boolean add(int examineeId, Date applicationTime, String examType) {
        ApplicationInfo applicationInfo = new ApplicationInfo();


        applicationInfo.setExamineeId(examineeId);
        applicationInfo.setApplicationTime(applicationTime);
        applicationInfo.setExamType(examType);
        applicationInfo.setConfirmed(0);

        // 此处可以添加一些业务逻辑校验，比如考试类型是否合法等
        if (dao.add(applicationInfo)) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有报考信息
     *
     * @return 报考信息列表
     */
    public List<ApplicationInfo> list() {
        return dao.list();
    }

    /**
     * 修改报考信息
     *
     * @param id 报考信息ID
     * @param examineeId 报考考生ID
     * @param applicationTime 报考时间
     * @param examType 报考考试类型
     */
    public void update(int id, int examineeId, Date applicationTime, String examType) {
        ApplicationInfo applicationInfo = dao.getById(id);
        if (applicationInfo!= null) {
            applicationInfo.setExamineeId(examineeId);
            applicationInfo.setApplicationTime(applicationTime);
            applicationInfo.setExamType(examType);
            // 只有当获取到的确认状态不为null时，才更新确认状态，避免覆盖已有确认状态
            if (applicationInfo.getConfirmed()!= 0) {
                applicationInfo.setConfirmed(applicationInfo.getConfirmed());
            }
            dao.update(applicationInfo);
        }
    }

    /**
     * 根据报考信息ID删除报考信息
     *
     * @param id 报考信息ID
     */
    public void delete(int id) {
        dao.delete(id);
    }

    /**
     * 根据报考信息ID获取报考信息
     *
     * @param id 报考信息ID
     * @return 对应的报考信息对象，如果不存在则返回null
     */
    public ApplicationInfo getById(int id) {
        return dao.getById(id);
    }

    /**
     * 根据考生ID查询报考信息列表
     *
     * @param examineeId 考生ID
     * @return 该考生的报考信息列表
     */
    public List<ApplicationInfo> getByExamineeId(int examineeId) {
        return dao.getByExamineeId(examineeId);
    }

    /**
     * 批量确认报考信息
     *
     * @param applicationIds 需要确认的报考信息ID列表
     */
    public void batchConfirm(List<Integer> applicationIds) {
        for (Integer id : applicationIds) {
            ApplicationInfo applicationInfo = dao.getById(id);
            if (applicationInfo!= null) {
                applicationInfo.setConfirmed(1);
                dao.update(applicationInfo);
            }
        }
    }

    /**
     * 获取未确认的报考信息列表
     *
     * @return 未确认的报考信息列表
     */
    public List<ApplicationInfo> getUnconfirmedList() {
        return dao.getUnconfirmedList();
    }
}