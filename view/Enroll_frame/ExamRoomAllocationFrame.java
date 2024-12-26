package view.Enroll_frame;

import dao.ExamRoomAllocationDAO;
import dao.ExamineeDAO;
import entity.ExamRoomAllocation;
import entity.Examinee;
import service.ExamRoomAllocationService;
import service.ExamineeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExamRoomAllocationFrame extends JFrame {  //用于考场分配
    public static ExamRoomAllocationFrame instance = new ExamRoomAllocationFrame();

    private JComboBox<String> examineeComboBox;
    private JTextField roomNumberField;
    private JTextField seatNumberField;
    private JButton allocateButton;

    private ExamineeService examineeService = new ExamineeService();
    private ExamRoomAllocationService examRoomAllocationService = new ExamRoomAllocationService();
    private ExamRoomAllocationDAO examRoomAllocationDAO = new ExamRoomAllocationDAO();
    private ExamineeDAO examineeDAO = new ExamineeDAO();

    private ExamRoomAllocationFrame() {
        setTitle("考场分配");
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
        roomNumberField = new JTextField(10);
        seatNumberField = new JTextField(10);
        allocateButton = new JButton("分配");
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("考生姓名："));
        panel.add(examineeComboBox);
        panel.add(new JLabel("考场编号："));
        panel.add(roomNumberField);
        panel.add(new JLabel("座位号："));
        panel.add(seatNumberField);
        panel.add(new JLabel());
        panel.add(allocateButton);

        add(panel);
    }

    private void loadExaminees() {
        List<Examinee> examinees = examineeService.list();
        for (Examinee e : examinees) {
            examineeComboBox.addItem(e.getName());
        }
    }

    private void addListeners() {
        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedExaminee = (String) examineeComboBox.getSelectedItem();
                String roomNumber = roomNumberField.getText();
                String seatNumber = seatNumberField.getText();
                Examinee targetExaminee = null;
                for (Examinee a : examineeService.list()) {
                    if (a.getName().equals(selectedExaminee)) {
                        targetExaminee = a;
                        break;
                    }
                }
                if (targetExaminee!= null) {
                    ExamRoomAllocation allocation = new ExamRoomAllocation();
                    allocation.setExamineeId(targetExaminee.getExamineeId());
                    allocation.setExamRoomNumber(roomNumber);
                    allocation.setSeatNumber(seatNumber);
                    examRoomAllocationService.add(allocation.getExamineeId(), allocation.getExamRoomNumber(), allocation.getSeatNumber());
                }
            }
        });
    }

    public void resetInput() {
        roomNumberField.setText("");
        seatNumberField.setText("");
    }
}