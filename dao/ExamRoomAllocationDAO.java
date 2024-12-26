package dao;

import entity.ExamRoomAllocation;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamRoomAllocationDAO {

    /**
     * 添加考场分配信息
     *
     * @param examRoomAllocation 考场分配信息对象
     * @return 是否添加成功
     */
    public boolean add(ExamRoomAllocation examRoomAllocation) {
        String sql = "insert into exam_room_allocation values(null,?,?,?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, examRoomAllocation.getExamineeId());
            ps.setString(2, examRoomAllocation.getExamRoomNumber());
            ps.setString(3, examRoomAllocation.getSeatNumber());

            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有考场分配信息
     *
     * @return 考场分配信息列表
     */
    public List<ExamRoomAllocation> list() {
        return list("");
    }

    /**
     * 根据条件搜索考场分配信息
     *
     * @param search 搜索条件（可按需完善搜索逻辑）
     * @return 符合条件的考场分配信息列表
     */
    public List<ExamRoomAllocation> list(String search) {
        List<ExamRoomAllocation> examRoomAllocations = new ArrayList<>();

        String sql = "select * from exam_room_allocation";

        if (0!= search.length()) {
            sql += " where exam_room_number like '%" + search + "%'";
        }

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ExamRoomAllocation examRoomAllocation = new ExamRoomAllocation();
                examRoomAllocation.setAllocationId(rs.getInt(1));
                examRoomAllocation.setExamineeId(rs.getInt(2));
                examRoomAllocation.setExamRoomNumber(rs.getString(3));
                examRoomAllocation.setSeatNumber(rs.getString(4));
                examRoomAllocations.add(examRoomAllocation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examRoomAllocations;
    }

    /**
     * 更新考场分配信息
     *
     * @param examRoomAllocation 考场分配信息对象，包含更新后的数据
     */
    public void update(ExamRoomAllocation examRoomAllocation) {
        String sql = "update exam_room_allocation set examinee_id =?, exam_room_number =?, seat_number =? where allocation_id =?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, examRoomAllocation.getExamineeId());
            ps.setString(2, examRoomAllocation.getExamRoomNumber());
            ps.setString(3, examRoomAllocation.getSeatNumber());
            ps.setInt(4, examRoomAllocation.getAllocationId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据考场分配信息ID删除考场分配信息
     *
     * @param id 考场分配信息ID
     */
    public void delete(int id) {
        String sql = "delete from exam_room_allocation where allocation_id =?";
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
     * 根据考生ID返回考场分配信息对象（通常一个考生对应一个考场分配记录）
     *
     * @param examineeId 考生ID
     * @return 对应的考场分配信息对象，如果不存在则返回null
     */
    public ExamRoomAllocation getByExamineeId(int examineeId) {

        String sql = "select * from exam_room_allocation where examinee_id =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, examineeId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ExamRoomAllocation examRoomAllocation = new ExamRoomAllocation();
                examRoomAllocation.setAllocationId(rs.getInt(1));
                examRoomAllocation.setExamineeId(rs.getInt(2));
                examRoomAllocation.setExamRoomNumber(rs.getString(3));
                examRoomAllocation.setSeatNumber(rs.getString(4));

                return examRoomAllocation;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据考场分配信息ID返回考场分配信息对象
     *
     * @param id 考场分配信息ID
     * @return 对应的考场分配信息对象，如果不存在则返回null
     */
    public ExamRoomAllocation getById(int id) {

        String sql = "select * from exam_room_allocation where allocation_id =?";

        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ExamRoomAllocation examRoomAllocation = new ExamRoomAllocation();
                examRoomAllocation.setAllocationId(rs.getInt(1));
                examRoomAllocation.setExamineeId(rs.getInt(2));
                examRoomAllocation.setExamRoomNumber(rs.getString(3));
                examRoomAllocation.setSeatNumber(rs.getString(4));

                return examRoomAllocation;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
