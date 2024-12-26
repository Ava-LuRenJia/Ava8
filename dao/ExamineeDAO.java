package dao;

import entity.Examinee;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamineeDAO {

    /**
     * 添加考生信息
     *
     * @param examinee 考生对象
     * @return 是否添加成功
     */
    public boolean add(Examinee examinee) {
        String sql = "insert into examinee values(?,?,?,?,?,?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, examinee.getExamineeId());
            ps.setString(2, examinee.getName());
            ps.setString(3, examinee.getGender());
            ps.setString(4, examinee.getBirthDate());
            ps.setString(5, examinee.getContactNumber());
            ps.setString(6, examinee.getEmail());

            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有考生信息
     *
     * @return 考生信息列表
     */
    public List<Examinee> list() {
        return list("");
    }

    /**
     * 根据条件搜索考生信息
     *
     * @param search 搜索条件（可根据实际需求用于模糊查询等，这里暂简单示例）
     * @return 符合条件的考生信息列表
     */
    public List<Examinee> list(String search) {
        List<Examinee> examinees = new ArrayList<>();

        String sql = "select * from examinee";

        // 简单示例，可根据实际情况完善搜索逻辑，比如拼接更复杂的where条件
        if (0!= search.length()) {
            sql += " where name like '%" + search + "%'";
        }

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Examinee examinee = new Examinee();
                examinee.setExamineeId(rs.getInt(1));
                examinee.setName(rs.getString(2));
                examinee.setGender(rs.getString(3));
                examinee.setBirthDate(rs.getString(4));
                examinee.setContactNumber(rs.getString(5));
                examinee.setEmail(rs.getString(6));
                examinees.add(examinee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examinees;
    }

    /**
     * 更新考生信息
     *
     * @param examinee 考生对象，包含更新后的数据
     */
    public void update(Examinee examinee) {
        String sql = "update examinee set name =?, gender =?, birth_date =?, contact_number =?, email =? where examinee_id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, examinee.getName());
            ps.setString(2, examinee.getGender());
            ps.setString(3, examinee.getBirthDate());
            ps.setString(4, examinee.getContactNumber());
            ps.setString(5, examinee.getEmail());
            ps.setInt(6, examinee.getExamineeId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据考生ID删除考生信息
     *
     * @param id 考生ID
     */
    public void delete(int id) {
        String sql = "delete from examinee where examinee_id =?";
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
     * 根据考生姓名返回考生对象
     *
     * @param name 考生姓名
     * @return 对应的考生对象，如果不存在则返回null
     */
    public Examinee getByName(String name) {

        String sql = "select * from examinee where name =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Examinee examinee = new Examinee();
                examinee.setExamineeId(rs.getInt(1));
                examinee.setName(rs.getString(2));
                examinee.setGender(rs.getString(3));
                examinee.setBirthDate(rs.getString(4));
                examinee.setContactNumber(rs.getString(5));
                examinee.setEmail(rs.getString(6));

                return examinee;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据考生ID返回考生对象
     *
     * @param id 考生ID
     * @return 对应的考生对象，如果不存在则返回null
     */
    public Examinee getById(int id) {

        String sql = "select * from examinee where examinee_id =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Examinee examinee = new Examinee();
                examinee.setExamineeId(rs.getInt(1));
                examinee.setName(rs.getString(2));
                examinee.setGender(rs.getString(3));
                examinee.setBirthDate(rs.getString(4));
                examinee.setContactNumber(rs.getString(5));
                examinee.setEmail(rs.getString(6));

                return examinee;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
