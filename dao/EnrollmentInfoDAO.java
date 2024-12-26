package dao;

import entity.EnrollmentInfo;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentInfoDAO {

    /**
     * 添加招考信息
     *
     * @param enrollmentInfo 招考信息对象
     * @return 是否添加成功
     */
    public boolean add(EnrollmentInfo enrollmentInfo) {
        String sql = "insert into enrollment_info values(null,?,?,?,?,?,?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, enrollmentInfo.getEnrollmentAdminId());
            ps.setString(2, enrollmentInfo.getExamName());
            ps.setTimestamp(3, new java.sql.Timestamp(enrollmentInfo.getStartTime().getTime()));
            ps.setTimestamp(4, new java.sql.Timestamp(enrollmentInfo.getEndTime().getTime()));
            ps.setTimestamp(5, new java.sql.Timestamp(enrollmentInfo.getExamDate().getTime()));
            ps.setDouble(6, enrollmentInfo.getAdmissionLine());

            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有招考信息
     *
     * @return 招考信息列表
     */
    public List<EnrollmentInfo> list() {
        return list("");
    }

    /**
     * 根据条件搜索招考信息
     *
     * @param search 搜索条件（可按需完善搜索逻辑）
     * @return 符合条件的招考信息列表
     */
    public List<EnrollmentInfo> list(String search) {
        List<EnrollmentInfo> enrollmentInfos = new ArrayList<>();

        String sql = "select * from enrollment_info";

        if (0!= search.length()) {
            sql += " where exam_name like '%" + search + "%'";
        }

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
                enrollmentInfo.setEnrollmentInfoId(rs.getInt(1));
                enrollmentInfo.setEnrollmentAdminId(rs.getInt(2));
                enrollmentInfo.setExamName(rs.getString(3));
                enrollmentInfo.setStartTime(new java.util.Date(rs.getTimestamp(4).getTime()));
                enrollmentInfo.setEndTime(new java.util.Date(rs.getTimestamp(5).getTime()));
                enrollmentInfo.setExamDate(new java.util.Date(rs.getTimestamp(6).getTime()));
                enrollmentInfo.setAdmissionLine(rs.getDouble(7));
                enrollmentInfos.add(enrollmentInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollmentInfos;
    }

    /**
     * 更新招考信息
     *
     * @param enrollmentInfo 招考信息对象，包含更新后的数据
     */
    public void update(EnrollmentInfo enrollmentInfo) {
        String sql = "update enrollment_info set enrollment_admin_id =?, exam_name =?, start_time =?, end_time =?, exam_date =?, admission_line =? where enrollment_info_id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, enrollmentInfo.getEnrollmentAdminId());
            ps.setString(2, enrollmentInfo.getExamName());
            ps.setTimestamp(3, new java.sql.Timestamp(enrollmentInfo.getStartTime().getTime()));
            ps.setTimestamp(4, new java.sql.Timestamp(enrollmentInfo.getEndTime().getTime()));
            ps.setTimestamp(5, new java.sql.Timestamp(enrollmentInfo.getExamDate().getTime()));
            ps.setDouble(6, enrollmentInfo.getAdmissionLine());
            ps.setInt(7, enrollmentInfo.getEnrollmentInfoId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据招考信息ID删除招考信息
     *
     * @param id 招考信息ID
     */
    public void delete(int id) {
        String sql = "delete from enrollment_info where enrollment_info_id =?";
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
     * 根据招生管理员ID返回招考信息列表（一个招生管理员可能负责多条招考信息）
     *
     * @param enrollmentAdminId 招生管理员ID
     * @return 对应的招考信息列表，如果不存在则返回空列表
     */
    public List<EnrollmentInfo> getByEnrollmentAdminId(int enrollmentAdminId) {

        String sql = "select * from enrollment_info where enrollment_admin_id =?";

        List<EnrollmentInfo> enrollmentInfos = new ArrayList<>();

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, enrollmentAdminId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
                enrollmentInfo.setEnrollmentInfoId(rs.getInt(1));
                enrollmentInfo.setEnrollmentAdminId(rs.getInt(2));
                enrollmentInfo.setExamName(rs.getString(3));
                enrollmentInfo.setStartTime(new java.util.Date(rs.getTimestamp(4).getTime()));
                enrollmentInfo.setEndTime(new java.util.Date(rs.getTimestamp(5).getTime()));
                enrollmentInfo.setExamDate(new java.util.Date(rs.getTimestamp(6).getTime()));
                enrollmentInfo.setAdmissionLine(rs.getDouble(7));
                enrollmentInfos.add(enrollmentInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollmentInfos;
    }

    /**
     * 根据招考信息ID返回招考信息对象
     *
     * @param id 招考信息ID
     * @return 对应的招考信息对象，如果不存在则返回null
     */
    public EnrollmentInfo getById(int id) {

        String sql = "select * from enrollment_info where enrollment_info_id =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
                enrollmentInfo.setEnrollmentInfoId(rs.getInt(1));
                enrollmentInfo.setEnrollmentAdminId(rs.getInt(2));
                enrollmentInfo.setExamName(rs.getString(3));
                enrollmentInfo.setStartTime(new java.util.Date(rs.getTimestamp(4).getTime()));
                enrollmentInfo.setEndTime(new java.util.Date(rs.getTimestamp(5).getTime()));
                enrollmentInfo.setExamDate(new java.util.Date(rs.getTimestamp(6).getTime()));
                enrollmentInfo.setAdmissionLine(rs.getDouble(7));

                return enrollmentInfo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EnrollmentInfo> searchByExamName(String examName) {
        List<EnrollmentInfo> resultList = new ArrayList<>();
        String sql = "select * from enrollment_info where exam_name like?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + examName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
                enrollmentInfo.setEnrollmentInfoId(rs.getInt(1));
                enrollmentInfo.setEnrollmentAdminId(rs.getInt(2));
                enrollmentInfo.setExamName(rs.getString(3));
                enrollmentInfo.setStartTime(new java.util.Date(rs.getTimestamp(4).getTime()));
                enrollmentInfo.setEndTime(new java.util.Date(rs.getTimestamp(5).getTime()));
                enrollmentInfo.setExamDate(new java.util.Date(rs.getTimestamp(6).getTime()));
                enrollmentInfo.setAdmissionLine(rs.getDouble(7));
                resultList.add(enrollmentInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 根据招生管理员ID查询出相关的招考信息记录的IDs
     *
     * @param enrollmentAdminId 招生管理员ID
     * @return 相关记录的IDs列表
     */
    public List<Integer> findIdsByAdminId(int enrollmentAdminId) {
        List<Integer> result = new ArrayList<>();
        String sql = "select enrollment_info_id from enrollment_info where enrollment_admin_id =?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, enrollmentAdminId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt("enrollment_info_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
