package view.System_frame;

import dao.ScoreDAO;
import entity.Score;
import model.ScoreTableModel;
import service.ScoreService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ViewAllScoreInfoFrame extends JFrame {
    public static ViewAllScoreInfoFrame instance = new ViewAllScoreInfoFrame();

    private JTable scoreInfoTable;
    private ScoreService scoreInfoService = new ScoreService();
    private ScoreDAO scoreInfoDAO = new ScoreDAO();

    private ViewAllScoreInfoFrame() {
        setTitle("查看全部成绩信息");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setupLayout();
        loadData();

        setVisible(true);
    }

    private void initComponents() {
        scoreInfoTable = new JTable();
    }

    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(scoreInfoTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    public void loadData() {
        List<Score> scoreInfos = scoreInfoService.list();
        TableModel tableModel = new ScoreTableModel();
        ((ScoreTableModel) tableModel).setData(scoreInfos);
        scoreInfoTable.setModel(tableModel);
    }

    public void updateData() {
        loadData();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewAllScoreInfoFrame.instance = new ViewAllScoreInfoFrame();
        });
    }
}
