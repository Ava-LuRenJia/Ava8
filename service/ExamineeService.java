package service;

import dao.ExamineeDAO;
import entity.Examinee;

import java.util.List;

public class ExamineeService {

    // dao层对象实例化
    ExamineeDAO dao = new ExamineeDAO();

    /**
     * 添加考生信息
     *
     * @param name        考生姓名
     * @param gender      考生性别
     * @param birthDate   考生出生日期
     * @param contactNumber 考生联系电话
     * @param email       考生电子邮箱
     * @return 是否添加成功
     */
    public boolean add(int id ,String name, String gender, String birthDate, String contactNumber, String email) {
        Examinee examinee = new Examinee();

        examinee.setExamineeId(id);
        examinee.setName(name);
        examinee.setGender(gender);
        examinee.setBirthDate(birthDate);
        examinee.setContactNumber(contactNumber);
        examinee.setEmail(email);

        if (dao.add(examinee)) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有考生信息
     *
     * @return 考生信息列表
     */
    public List<Examinee> list() {
        return dao.list();
    }

    /**
     * 修改考生信息
     *
     * @param id          考生ID
     * @param name        考生姓名
     * @param gender      考生性别
     * @param birthDate   考生出生日期
     * @param contactNumber 考生联系电话
     * @param email       考生电子邮箱
     */
    public void update(int id, String name, String gender, String birthDate, String contactNumber, String email) {
        Examinee examinee = new Examinee();

        examinee.setExamineeId(id);
        examinee.setName(name);
        examinee.setGender(gender);
        examinee.setBirthDate(birthDate);
        examinee.setContactNumber(contactNumber);
        examinee.setEmail(email);

        dao.update(examinee);
    }

    /**
     * 根据考生ID删除考生信息
     *
     * @param id 考生ID
     */
    public void delete(int id) {
        dao.delete(id);
    }
}
