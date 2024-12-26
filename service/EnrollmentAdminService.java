package service;

import dao.EnrollmentAdminDAO;
import entity.EnrollmentAdmin;
import util.DBUtil;

import java.sql.*;
import java.util.List;

public class EnrollmentAdminService {

    // dao层对象实例化
    EnrollmentAdminDAO dao = new EnrollmentAdminDAO();

    /**
     * 添加招生管理员信息
     *
     * @param adminRealName 招生管理员真实姓名
     * @param responsibleArea 招生管理员负责区域
     * @return 是否添加成功
     */
    public boolean add(int enrollmentAdminId,String adminRealName, String responsibleArea) {
        EnrollmentAdmin enrollmentAdmin = new EnrollmentAdmin();

        enrollmentAdmin.setEnrollmentAdminId(enrollmentAdminId);
        enrollmentAdmin.setAdminRealName(adminRealName);
        enrollmentAdmin.setResponsibleArea(responsibleArea);

        if (dao.add(enrollmentAdmin)) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有招生管理员信息
     *
     * @return 招生管理员信息列表
     */
    public List<EnrollmentAdmin> list() {
        return dao.list();
    }

    /**
     * 修改招生管理员信息
     *
     * @param id              招生管理员ID
     * @param adminRealName   招生管理员真实姓名
     * @param responsibleArea 招生管理员负责区域
     */
    public void update(int id, String adminRealName, String responsibleArea) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id value, it should be greater than 0");
        }
        if (adminRealName == null || responsibleArea == null) {
            throw new IllegalArgumentException("adminRealName and responsibleArea cannot be null");
        }

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            // 使用DBUtil获取数据库连接
            connection = DBUtil.getConnection();

            // 获取原来的招生管理员信息
            EnrollmentAdmin oldEnrollmentAdmin = getById(id);
            if (oldEnrollmentAdmin == null) {
                throw new IllegalArgumentException("No enrollment admin found with the given id: " + id);
            }

            // 调用存储过程
            String sql = "{call update_enrollment_admin_and_user(?,?,?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, adminRealName);
            callableStatement.setString(3, oldEnrollmentAdmin.getAdminRealName());
            callableStatement.execute();

            // 更新其他相关属性
            EnrollmentAdmin enrollmentAdmin = new EnrollmentAdmin();
            enrollmentAdmin.setEnrollmentAdminId(id);
            enrollmentAdmin.setAdminRealName(adminRealName);
            enrollmentAdmin.setResponsibleArea(responsibleArea);

            // 调用DAO层的更新方法
            dao.update(enrollmentAdmin);

        } catch (SQLException e) {
            e.printStackTrace();
            // 可以在这里添加更完善的异常处理逻辑，比如记录日志、抛出自定义业务异常等
        } finally {
            // 关闭资源，调用DBUtil的方法关闭连接
            try {
                if (callableStatement!= null) {
                    callableStatement.close();
                }
                if (connection!= null) {
                    DBUtil.closeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 获取招生管理员信息的方法，用于获取旧的记录信息
    private EnrollmentAdmin getById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        EnrollmentAdmin enrollmentAdmin = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM enrollment_admin WHERE enrollment_admin_id =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                enrollmentAdmin = new EnrollmentAdmin();
                enrollmentAdmin.setEnrollmentAdminId(resultSet.getInt("enrollment_admin_id"));
                enrollmentAdmin.setAdminRealName(resultSet.getString("admin_real_name"));
                enrollmentAdmin.setResponsibleArea(resultSet.getString("responsible_area"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet!= null) {
                    resultSet.close();
                }
                if (preparedStatement!= null) {
                    preparedStatement.close();
                }
                if (connection!= null) {
                    DBUtil.closeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return enrollmentAdmin;
    }

    /**
     * 根据招生管理员ID删除招生管理员信息
     *
     * @param id 招生管理员ID
     */
    public void delete(int id) {
        dao.delete(id);
    }
}
