package service;

import dao.SystemAdminDAO;
import entity.SystemAdmin;

import java.util.List;

public class SystemAdminService {

    // dao层对象实例化
    SystemAdminDAO dao = new SystemAdminDAO();

    /**
     * 添加系统管理员信息
     *
     * @param adminName   系统管理员姓名
     * @param department  系统管理员所属部门
     * @return 是否添加成功
     */
    public boolean add(String adminName, String department) {
        SystemAdmin systemAdmin = new SystemAdmin();

        systemAdmin.setAdminName(adminName);
        systemAdmin.setDepartment(department);

        if (dao.add(systemAdmin)) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有系统管理员信息
     *
     * @return 系统管理员信息列表
     */
    public List<SystemAdmin> list() {
        return dao.list();
    }

    /**
     * 修改系统管理员信息
     *
     * @param id          系统管理员ID
     * @param adminName   系统管理员姓名
     * @param department  系统管理员所属部门
     */
    public void update(int id, String adminName, String department) {
        SystemAdmin systemAdmin = new SystemAdmin();

        systemAdmin.setAdminId(id);
        systemAdmin.setAdminName(adminName);
        systemAdmin.setDepartment(department);

        dao.update(systemAdmin);
    }

    /**
     * 根据系统管理员ID删除系统管理员信息
     *
     * @param id 系统管理员ID
     */
    public void delete(int id) {
        dao.delete(id);
    }
}
