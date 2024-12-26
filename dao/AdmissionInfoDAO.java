package dao;

import entity.AdmissionInfo;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdmissionInfoDAO {

    /**
     * 添加录取信息
     *
     * @param admissionInfo 录取信息对象
     * @return 是否添加成功
     */
    public boolean add(AdmissionInfo admissionInfo) {
        String sql = "insert into admission_info values(null,?,?,?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, admissionInfo.getExamineeId());
            ps.setBoolean(2, admissionInfo.isAdmitted());
            ps.setTimestamp(3, new java.sql.Timestamp(admissionInfo.getAdmissionTime().getTime()));

            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有录取信息
     *
     * @return 录取信息列表
     */
    public List<AdmissionInfo> list() {
        return list("");
    }

    /**
     * 根据条件搜索录取信息
     *
     * @param search 搜索条件
     * @return 符合条件的录取信息列表
     */
    public List<AdmissionInfo> list(String search) {
        List<AdmissionInfo> admissionInfos = new ArrayList<>();

        String sql = "select * from admission_info";

        if (0 != search.length()) {
            sql += " where is_admitted = " + search;
        }

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AdmissionInfo admissionInfo = new AdmissionInfo();
                admissionInfo.setAdmissionId(rs.getInt(1));
                admissionInfo.setExamineeId(rs.getInt(2));
                admissionInfo.setAdmitted(rs.getBoolean(3));
                admissionInfo.setAdmissionTime(new java.util.Date(rs.getTimestamp(4).getTime()));
                admissionInfos.add(admissionInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admissionInfos;
    }

    /**
     * 更新录取信息
     *
     * @param admissionInfo 录取信息对象，包含更新后的数据
     */
    public void update(AdmissionInfo admissionInfo) {
        String sql = "update admission_info set examinee_id =?, is_admitted =?, admission_time =? where admission_id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, admissionInfo.getExamineeId());
            ps.setBoolean(2, admissionInfo.isAdmitted());
            ps.setTimestamp(3, new java.sql.Timestamp(admissionInfo.getAdmissionTime().getTime()));
            ps.setInt(4, admissionInfo.getAdmissionId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据录取信息ID删除录取信息
     *
     * @param id 录取信息ID
     */
    public void delete(int id) {
        String sql = "delete from admission_info where admission_id =?";
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
     * 根据考生ID返回录取信息对象
     *
     * @param examineeId 考生ID
     * @return 对应的录取信息对象，如果不存在则返回null
     */
    public AdmissionInfo getByExamineeId(int examineeId) {

        String sql = "select * from admission_info where examinee_id =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, examineeId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AdmissionInfo admissionInfo = new AdmissionInfo();
                admissionInfo.setAdmissionId(rs.getInt(1));
                admissionInfo.setExamineeId(rs.getInt(2));
                admissionInfo.setAdmitted(rs.getBoolean(3));
                admissionInfo.setAdmissionTime(new java.util.Date(rs.getTimestamp(4).getTime()));

                return admissionInfo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据录取信息ID返回录取信息对象
     *
     * @param id 录取信息ID
     * @return 对应的录取信息对象，如果不存在则返回null
     */
    public AdmissionInfo getById(int id) {

        String sql = "select * from admission_info where admission_id =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AdmissionInfo admissionInfo = new AdmissionInfo();
                admissionInfo.setAdmissionId(rs.getInt(1));
                admissionInfo.setExamineeId(rs.getInt(2));
                admissionInfo.setAdmitted(rs.getBoolean(3));
                admissionInfo.setAdmissionTime(new java.util.Date(rs.getTimestamp(4).getTime()));

                return admissionInfo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
