package view.Enroll_frame;

import dao.ScoreDAO;
import entity.Score;
import service.ScoreService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScoreEntryFrame extends JFrame {
    public static ScoreEntryFrame instance = new ScoreEntryFrame();

    private JComboBox<String> examineeComboBox;
    private JTextField subjectField;
    private JTextField scoreField;
    private JButton submitButton;

    private ScoreService scoreService = new ScoreService();
    private ScoreDAO scoreDAO = new ScoreDAO();

    private ScoreEntryFrame() {
        setTitle("成绩录入");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setupLayout();
        loadExaminees();
        addListeners();

        setVisible(true);
    }

    private void initComponents() {
        examineeComboBox = new JComboBox<>();
        subjectField = new JTextField(10);
        scoreField = new JTextField(10);
        submitButton = new JButton("提交");
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("考试科目："));
        panel.add(examineeComboBox);
        panel.add(new JLabel("考生id："));
        panel.add(subjectField);
        panel.add(new JLabel("成绩："));
        panel.add(scoreField);
        panel.add(new JLabel());
        panel.add(submitButton);

        add(panel);
    }

    private void loadExaminees() {
        List<Score> scores = scoreService.list();
        for (Score s : scores) {
            examineeComboBox.addItem(s.getExamineeId() + " - " + s.getSubject());
        }
    }

    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedExamineeSubject = (String) examineeComboBox.getSelectedItem();
                String[] parts = selectedExamineeSubject.split(" - ");
                int examineeId = Integer.parseInt(parts[0]);
                String subject = parts[1];
                double score = Double.parseDouble(scoreField.getText());

                Score scoreToUpdate = new Score();
                scoreToUpdate.setExamineeId(examineeId);
                scoreToUpdate.setSubject(subject);
                scoreToUpdate.setScoreValue(score);


                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());


                scoreService.add(scoreToUpdate.getExamineeId(), scoreToUpdate.getSubject(), scoreToUpdate.getScoreValue(), date);
            }
        });
    }

    public void resetInput() {
        subjectField.setText("");
        scoreField.setText("");
    }
}
