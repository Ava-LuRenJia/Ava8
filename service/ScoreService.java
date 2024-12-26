package service;

import dao.ScoreDAO;
import entity.Score;

import java.util.Date;
import java.util.List;

public class ScoreService {

    // dao层对象实例化
    ScoreDAO dao = new ScoreDAO();

    /**
     * 添加成绩信息
     *
     * @param examineeId  考生ID
     * @param subject     考试科目
     * @param scoreValue  成绩分数
     * @param examTime    考试时间
     * @return 是否添加成功
     */
    public boolean add(int examineeId, String subject, double scoreValue, Date examTime) {
        Score score = new Score();

        score.setExamineeId(examineeId);
        score.setSubject(subject);
        score.setScoreValue(scoreValue);
        score.setExamTime(examTime);

        if (dao.add(score)) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有成绩信息
     *
     * @return 成绩信息列表
     */
    public List<Score> list() {
        return dao.list();
    }

    /**
     * 修改成绩信息
     *
     * @param id          成绩ID
     * @param examineeId  考生ID
     * @param subject     考试科目
     * @param scoreValue  成绩分数
     * @param examTime    考试时间
     */
    public void update(int id, int examineeId, String subject, double scoreValue, Date examTime) {
        Score score = new Score();

        score.setScoreId(id);
        score.setExamineeId(examineeId);
        score.setSubject(subject);
        score.setScoreValue(scoreValue);
        score.setExamTime(examTime);

        dao.update(score);
    }

    /**
     * 根据成绩ID删除成绩信息
     *
     * @param id 成绩ID
     */
    public void delete(int id) {
        dao.delete(id);
    }
}
