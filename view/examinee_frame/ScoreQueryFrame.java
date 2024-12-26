package view.examinee_frame;

import dao.ScoreDAO;
import entity.Score;
import model.ScoreTableModel;
import service.ScoreService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ScoreQueryFrame extends JFrame {
    public static ScoreQueryFrame instance = new ScoreQueryFrame();

    private JTable scoreTable;
    private ScoreService scoreService = new ScoreService();
    private ScoreDAO scoreDAO = new ScoreDAO();

    private ScoreQueryFrame() {
        setTitle("成绩查询");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setupLayout();
        queryAndShowScores();

        setVisible(true);
    }

    private void initComponents() {
        scoreTable = new JTable();
    }

    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(scoreTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    public void queryAndShowScores() {
        List<Score> scores = scoreService.list();
        TableModel tableModel = new ScoreTableModel();
        ((ScoreTableModel) tableModel).setData(scores);
        scoreTable.setModel(tableModel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScoreQueryFrame.instance = new ScoreQueryFrame();
        });
    }
}
