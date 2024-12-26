package view.Enroll_frame;

import dao.EnrollmentInfoDAO;
import entity.EnrollmentInfo;
import service.EnrollmentInfoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetAdmissionLineFrame extends JFrame {
    public static SetAdmissionLineFrame instance = new SetAdmissionLineFrame();

    private JComboBox<String> examNameComboBox;
    private JTextField lineTextField;
    private JButton setButton;

    private EnrollmentInfoService enrollmentInfoService = new EnrollmentInfoService();
    private EnrollmentInfoDAO enrollmentInfoDAO = new EnrollmentInfoDAO();

    private SetAdmissionLineFrame() {
        setTitle("设置录取分数线");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setupLayout();
        loadExamNames();
        addListeners();

        setVisible(true);
    }

    private void initComponents() {
        examNameComboBox = new JComboBox<>();
        lineTextField = new JTextField(10);
        setButton = new JButton("设置");
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("考试名称："));
        panel.add(examNameComboBox);
        panel.add(new JLabel("录取分数线："));
        panel.add(lineTextField);
        panel.add(new JLabel());
        panel.add(setButton);

        add(panel);
    }

    private void loadExamNames() {
        for (EnrollmentInfo ei : enrollmentInfoService.list()) {
            examNameComboBox.addItem(ei.getExamName());
        }
    }

    private void addListeners() {
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedExamName = (String) examNameComboBox.getSelectedItem();
                double newLine = Double.parseDouble(lineTextField.getText());
                EnrollmentInfo targetInfo = null;
                for (EnrollmentInfo ei : enrollmentInfoService.list()) {
                    if (ei.getExamName().equals(selectedExamName)) {
                        targetInfo = ei;
                        break;
                    }
                }
                if (targetInfo!= null) {
                    targetInfo.setAdmissionLine(newLine);
                    enrollmentInfoService.update(targetInfo.getEnrollmentInfoId(), targetInfo.getEnrollmentAdminId(), targetInfo.getExamName(), targetInfo.getStartTime(), targetInfo.getEndTime(), targetInfo.getExamDate(), targetInfo.getAdmissionLine());
                }
            }
        });
    }

    public void resetInput() {
        lineTextField.setText("");
    }
}
