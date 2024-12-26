package dao;

import entity.EnrollmentAdmin;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentAdminDAO {

    /**
     * 添加招生管理员信息
     *
     * @param enrollmentAdmin 招生管理员对象
     * @return 是否添加成功
     */
    public boolean add(EnrollmentAdmin enrollmentAdmin) {
        String sql = "insert into enrollment_admin values(?,?,?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, enrollmentAdmin.getEnrollmentAdminId());
            ps.setString(2, enrollmentAdmin.getAdminRealName());
            ps.setString(3, enrollmentAdmin.getResponsibleArea());

            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有招生管理员信息
     *
     * @return 招生管理员信息列表
     */
    public List<EnrollmentAdmin> list() {
        return list("");
    }

    /**
     * 根据条件搜索招生管理员信息
     *
     * @param search 搜索条件
     * @return 符合条件的招生管理员信息列表
     */
    public List<EnrollmentAdmin> list(String search) {
        List<EnrollmentAdmin> enrollmentAdmins = new ArrayList<>();

        String sql = "select * from enrollment_admin";

        if (0!= search.length()) {
            sql += " where admin_real_name like '%" + search + "%'";
        }

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EnrollmentAdmin enrollmentAdmin = new EnrollmentAdmin();
                enrollmentAdmin.setEnrollmentAdminId(rs.getInt(1));
                enrollmentAdmin.setAdminRealName(rs.getString(2));
                enrollmentAdmin.setResponsibleArea(rs.getString(3));
                enrollmentAdmins.add(enrollmentAdmin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollmentAdmins;
    }

    /**
     * 更新招生管理员信息
     *
     * @param enrollmentAdmin 招生管理员对象，包含更新后的数据
     */
    public void update(EnrollmentAdmin enrollmentAdmin) {
        String sql = "update enrollment_admin set admin_real_name =?, responsible_area =? where enrollment_admin_id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, enrollmentAdmin.getAdminRealName());
            ps.setString(2, enrollmentAdmin.getResponsibleArea());
            ps.setInt(3, enrollmentAdmin.getEnrollmentAdminId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据招生管理员ID删除招生管理员信息
     *
     * @param id 招生管理员ID
     */
    public void delete(int id) {
        String sql = "delete from enrollment_admin where enrollment_admin_id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据招生管理员姓名返回招生管理员对象
     *
     * @param name 招生管理员姓名
     * @return 对应的招生管理员对象，如果不存在则返回null
     */
    public EnrollmentAdmin getByName(String name) {

        String sql = "select * from enrollment_admin where admin_real_name =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EnrollmentAdmin enrollmentAdmin = new EnrollmentAdmin();
                enrollmentAdmin.setEnrollmentAdminId(rs.getInt(1));
                enrollmentAdmin.setAdminRealName(rs.getString(2));
                enrollmentAdmin.setResponsibleArea(rs.getString(3));

                return enrollmentAdmin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据招生管理员ID返回招生管理员对象
     *
     * @param id 招生管理员ID
     * @return 对应的招生管理员对象，如果不存在则返回null
     */
    public EnrollmentAdmin getById(int id) {

        String sql = "select * from enrollment_admin where enrollment_admin_id =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EnrollmentAdmin enrollmentAdmin = new EnrollmentAdmin();
                enrollmentAdmin.setEnrollmentAdminId(rs.getInt(1));
                enrollmentAdmin.setAdminRealName(rs.getString(2));
                enrollmentAdmin.setResponsibleArea(rs.getString(3));

                return enrollmentAdmin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
