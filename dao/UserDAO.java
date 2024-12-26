package dao;

import entity.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public static boolean login(String userName, String password) {
        String sql = "select * from user where id = ? and password = ?";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ) {

            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 判断是否为系统管理员
     * @param userName
     * @return
     */
    public static boolean isSysAdmin(String userName) {
        String sql = "select * from user where id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int isAdmin = rs.getInt("identity");
                if (isAdmin == 1) {
                    return true;
                }
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 判断是否为报名管理员（enrol_admin）
     *
     * @param userName
     * @return true表示是报名管理员，false表示不是
     */
    public static boolean isEnrolAdmin(String userName) {
        String sql = "select * from user where id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int isAdmin = rs.getInt("identity");
                if (isAdmin == 2) {
                    return true;
                }
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    public static User getUser(int id) {
        String sql = "select * from user where id = ?";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                User user = new User();

                user.setId(id);
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setIdentity((rs.getInt(4)));

                return user;
            }

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据用户ID获取用户密码
     *
     * @param id 用户ID
     * @return 对应的用户密码，如果用户不存在则返回null
     */
    public static String getPasswordById(int id) {
        String sql = "select password from user where id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除user表中指定ID的记录的DAO方法
     *
     * @param id 用户ID
     * @return 删除成功返回true，失败返回false
     */
    public static boolean delete(int id) {
        String sql = "delete from user where id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
