package dao;

import entity.SystemAdmin;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SystemAdminDAO {

    /**
     * 添加系统管理员信息
     *
     * @param systemAdmin 系统管理员对象
     * @return 是否添加成功
     */
    public boolean add(SystemAdmin systemAdmin) {
        String sql = "insert into system_admin values(?,?,?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, systemAdmin.getAdminId());
            ps.setString(2, systemAdmin.getAdminName());
            ps.setString(3, systemAdmin.getDepartment());

            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有系统管理员信息
     *
     * @return 系统管理员信息列表
     */
    public List<SystemAdmin> list() {
        return list("");
    }

    /**
     * 根据条件搜索系统管理员信息
     *
     * @param search 搜索条件
     * @return 符合条件的系统管理员信息列表
     */
    public List<SystemAdmin> list(String search) {
        List<SystemAdmin> systemAdmins = new ArrayList<>();

        String sql = "select * from system_admin";

        if (0!= search.length()) {
            sql += " where admin_name like '%" + search + "%'";
        }

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SystemAdmin systemAdmin = new SystemAdmin();
                systemAdmin.setAdminId(rs.getInt(1));
                systemAdmin.setAdminName(rs.getString(2));
                systemAdmin.setDepartment(rs.getString(3));
                systemAdmins.add(systemAdmin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return systemAdmins;
    }

    /**
     * 更新系统管理员信息
     *
     * @param systemAdmin 系统管理员对象，包含更新后的数据
     */
    public void update(SystemAdmin systemAdmin) {
        String sql = "update system_admin set admin_name =?, department =? where admin_id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, systemAdmin.getAdminName());
            ps.setString(2, systemAdmin.getDepartment());
            ps.setInt(3, systemAdmin.getAdminId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据系统管理员ID删除系统管理员信息
     *
     * @param id 系统管理员ID
     */
    public void delete(int id) {
        String sql = "delete from system_admin where admin_id =?";
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
     * 根据系统管理员姓名返回系统管理员对象
     *
     * @param name 系统管理员姓名
     * @return 对应的系统管理员对象，如果不存在则返回null
     */
    public SystemAdmin getByName(String name) {

        String sql = "select * from system_admin where admin_name =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SystemAdmin systemAdmin = new SystemAdmin();
                systemAdmin.setAdminId(rs.getInt(1));
                systemAdmin.setAdminName(rs.getString(2));
                systemAdmin.setDepartment(rs.getString(3));

                return systemAdmin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据系统管理员ID返回系统管理员对象
     *
     * @param id 系统管理员ID
     * @return 对应的系统管理员对象，如果不存在则返回null
     */
    public SystemAdmin getById(int id) {

        String sql = "select * from system_admin where admin_id =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SystemAdmin systemAdmin = new SystemAdmin();
                systemAdmin.setAdminId(rs.getInt(1));
                systemAdmin.setAdminName(rs.getString(2));
                systemAdmin.setDepartment(rs.getString(3));

                return systemAdmin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
