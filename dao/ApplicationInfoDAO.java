package dao;

import entity.ApplicationInfo;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationInfoDAO {

    // 添加报考信息到数据库的方法
    public boolean add(ApplicationInfo applicationInfo) {
        String sql = "INSERT INTO application_info (examinee_id, application_time, exam_type, confirmed) VALUES (?,?,?,?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, applicationInfo.getExamineeId());
            preparedStatement.setDate(2, new java.sql.Date(applicationInfo.getApplicationTime().getTime()));
            preparedStatement.setString(3, applicationInfo.getExamType());
            preparedStatement.setInt(4, applicationInfo.getConfirmed());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 查询所有报考信息的方法
    public List<ApplicationInfo> list() {
        List<ApplicationInfo> applicationInfos = new ArrayList<>();
        String sql = "SELECT * FROM application_info";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ApplicationInfo applicationInfo = new ApplicationInfo();
                applicationInfo.setApplicationId(resultSet.getInt("application_id"));
                applicationInfo.setExamineeId(resultSet.getInt("examinee_id"));
                applicationInfo.setApplicationTime(resultSet.getDate("application_time"));
                applicationInfo.setExamType(resultSet.getString("exam_type"));
                applicationInfo.setConfirmed(resultSet.getInt("confirmed"));
                applicationInfos.add(applicationInfo);
            }
            return applicationInfos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更新报考信息
     *
     * @param applicationInfo 报考信息对象，包含更新后的数据
     */
    public void update(ApplicationInfo applicationInfo) {
        String sql = "UPDATE application_info SET examinee_id =?, application_time =?, exam_type =?, confirmed =? WHERE application_id =?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, applicationInfo.getExamineeId());
            preparedStatement.setDate(2, new java.sql.Date(applicationInfo.getApplicationTime().getTime()));
            preparedStatement.setString(3, applicationInfo.getExamType());
            preparedStatement.setInt(4, applicationInfo.getConfirmed());
            preparedStatement.setInt(5, applicationInfo.getApplicationId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据报考信息ID删除报考信息的方法
    public void delete(int id) {
        String sql = "DELETE FROM application_info WHERE application_id =?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据报考信息ID获取报考信息的方法
    public ApplicationInfo getById(int id) {
        String sql = "SELECT * FROM application_info WHERE application_id =?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // 给占位符赋值，将传入的id参数设置给SQL语句中的占位符
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ApplicationInfo applicationInfo = new ApplicationInfo();
                applicationInfo.setApplicationId(resultSet.getInt("application_id"));
                applicationInfo.setExamineeId(resultSet.getInt("examinee_id"));
                applicationInfo.setApplicationTime(resultSet.getDate("application_time"));
                applicationInfo.setExamType(resultSet.getString("exam_type"));
                applicationInfo.setConfirmed(resultSet.getInt("confirmed"));
                return applicationInfo;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 根据考生ID查询报考信息列表的方法
    public List<ApplicationInfo> getByExamineeId(int examineeId) {
        List<ApplicationInfo> applicationInfos = new ArrayList<>();
        String sql = "SELECT * FROM application_info WHERE examinee_id =?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ApplicationInfo applicationInfo = new ApplicationInfo();
                applicationInfo.setApplicationId(resultSet.getInt("application_id"));
                applicationInfo.setExamineeId(examineeId);
                applicationInfo.setApplicationTime(resultSet.getDate("application_time"));
                applicationInfo.setExamType(resultSet.getString("exam_type"));
                applicationInfo.setConfirmed(resultSet.getInt("confirmed"));
                applicationInfos.add(applicationInfo);
            }
            return applicationInfos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 获取未确认的报考信息列表的方法
    public List<ApplicationInfo> getUnconfirmedList() {
        List<ApplicationInfo> applicationInfos = new ArrayList<>();
        String sql = "SELECT * FROM application_info WHERE confirmed = 0";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ApplicationInfo applicationInfo = new ApplicationInfo();
                applicationInfo.setApplicationId(resultSet.getInt("application_id"));
                applicationInfo.setExamineeId(resultSet.getInt("examinee_id"));
                applicationInfo.setApplicationTime(resultSet.getDate("application_time"));
                applicationInfo.setExamType(resultSet.getString("exam_type"));
                applicationInfo.setConfirmed(resultSet.getInt("confirmed"));
                applicationInfos.add(applicationInfo);
            }
            return applicationInfos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}