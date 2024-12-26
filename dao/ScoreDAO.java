package dao;

import entity.Score;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {

    /**
     * 添加成绩信息
     *
     * @param score 成绩对象
     * @return 是否添加成功
     */
    public boolean add(Score score) {
        String sql = "insert into score values(null,?,?,?,?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, score.getExamineeId());
            ps.setString(2, score.getSubject());
            ps.setDouble(3, score.getScoreValue());
            ps.setTimestamp(4, new java.sql.Timestamp(score.getExamTime().getTime()));

            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有成绩信息
     *
     * @return 成绩信息列表
     */
    public List<Score> list() {
        return list("");
    }

    /**
     * 根据条件搜索成绩信息
     *
     * @param search 搜索条件
     * @return 符合条件的成绩信息列表
     */
    public List<Score> list(String search) {
        List<Score> scores = new ArrayList<>();

        String sql = "select * from score";

        if (0!= search.length()) {
            sql += " where subject like '%" + search + "%'";
        }

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Score score = new Score();
                score.setScoreId(rs.getInt(1));
                score.setExamineeId(rs.getInt(2));
                score.setSubject(rs.getString(3));
                score.setScoreValue(rs.getDouble(4));
                score.setExamTime(new java.util.Date(rs.getTimestamp(5).getTime()));
                scores.add(score);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }

    /**
     * 更新成绩信息
     *
     * @param score 成绩对象，包含更新后的数据
     */
    public void update(Score score) {
        String sql = "update score set examinee_id =?, subject =?, score_value =?, exam_time =? where score_id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, score.getExamineeId());
            ps.setString(2, score.getSubject());
            ps.setDouble(3, score.getScoreValue());
            ps.setTimestamp(4, new java.sql.Timestamp(score.getExamTime().getTime()));
            ps.setInt(5, score.getScoreId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据成绩ID删除成绩信息
     *
     * @param id 成绩ID
     */
    public void delete(int id) {
        String sql = "delete from score where score_id =?";
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
     * 根据考生ID返回成绩信息列表（因为一个考生可能有多门成绩）
     *
     * @param examineeId 考生ID
     * @return 对应的成绩信息列表，如果不存在则返回空列表
     */
    public List<Score> getByExamineeId(int examineeId) {

        String sql = "select * from score where examinee_id =?";

        List<Score> scores = new ArrayList<>();

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, examineeId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Score score = new Score();
                score.setScoreId(rs.getInt(1));
                score.setExamineeId(rs.getInt(2));
                score.setSubject(rs.getString(3));
                score.setScoreValue(rs.getDouble(4));
                score.setExamTime(new java.util.Date(rs.getTimestamp(5).getTime()));
                scores.add(score);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }

    /**
     * 根据成绩ID返回成绩信息对象
     *
     * @param id 成绩ID
     * @return 对应的成绩信息对象，如果不存在则返回null
     */
    public Score getById(int id) {

        String sql = "select * from score where score_id =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Score score = new Score();
                score.setScoreId(rs.getInt(1));
                score.setExamineeId(rs.getInt(2));
                score.setSubject(rs.getString(3));
                score.setScoreValue(rs.getDouble(4));
                score.setExamTime(new java.util.Date(rs.getTimestamp(5).getTime()));

                return score;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
