package view.System_frame;

import entity.EnrollmentAdmin;
import service.EnrollmentAdminService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UpdateAdminInfoFrame extends JFrame {
    public static UpdateAdminInfoFrame instance = new UpdateAdminInfoFrame();

    private JComboBox<String> adminComboBox;
    private JTextField realNameField;
    private JTextField responsibleAreaField;
    private JButton updateButton;

    private EnrollmentAdminService enrollmentAdminService = new EnrollmentAdminService();

    private UpdateAdminInfoFrame() {
        setTitle("更新管理员信息");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setupLayout();
        loadAdminData();
        addListeners();

        setVisible(true);
    }

    private void initComponents() {
        adminComboBox = new JComboBox<>();
        realNameField = new JTextField(20);
        responsibleAreaField = new JTextField(20);
        updateButton = new JButton("更新");
    }

    private void setupLayout() {
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        contentPane.add(inputPanel, BorderLayout.CENTER);

        JLabel selectAdminLabel = new JLabel("选择要更新的管理员：");
        inputPanel.add(selectAdminLabel);
        inputPanel.add(adminComboBox);

        JLabel realNameLabel = new JLabel("真实姓名：");
        inputPanel.add(realNameLabel);
        inputPanel.add(realNameField);

        JLabel responsibleAreaLabel = new JLabel("负责区域：");
        inputPanel.add(responsibleAreaLabel);
        inputPanel.add(responsibleAreaField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(updateButton);
    }

    private void loadAdminData() {
        List<EnrollmentAdmin> adminInfos = enrollmentAdminService.list();
        List<String> adminNames = new ArrayList<>();
        for (EnrollmentAdmin info : adminInfos) {
            adminNames.add(info.getAdminRealName());
        }
        adminComboBox.setModel(new DefaultComboBoxModel<>(adminNames.toArray(new String[0])));
    }

    private void addListeners() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAdmin = (String) adminComboBox.getSelectedItem();
                if (selectedAdmin == null) {
                    JOptionPane.showMessageDialog(UpdateAdminInfoFrame.this, "请选择要更新的管理员！");
                    return;
                }

                String newRealName = realNameField.getText();
                String newResponsibleArea = responsibleAreaField.getText();

                // 输入验证
                if (newRealName.isEmpty() || newResponsibleArea.isEmpty()) {
                    JOptionPane.showMessageDialog(UpdateAdminInfoFrame.this, "输入信息不能为空，请重新输入！");
                    return;
                }

                // 根据真实姓名查找对应管理员的ID（这里假设真实姓名唯一，可根据实际情况调整查找逻辑）
                EnrollmentAdmin oldAdmin = findAdminByRealName(selectedAdmin);
                if (oldAdmin!= null) {
                    int adminId = oldAdmin.getEnrollmentAdminId();
                    // 调用服务层方法更新管理员信息
                    enrollmentAdminService.update(adminId, newRealName, newResponsibleArea);
                    JOptionPane.showMessageDialog(UpdateAdminInfoFrame.this, "管理员信息更新成功！");
                    resetInput();
                    loadAdminData();
                } else {
                    JOptionPane.showMessageDialog(UpdateAdminInfoFrame.this, "更新管理员信息失败，未找到对应管理员信息，请重新选择！");
                }
            }
        });
    }

    public void resetInput() {
        adminComboBox.setSelectedIndex(-1);
        realNameField.setText("");
        responsibleAreaField.setText("");
    }

    private EnrollmentAdmin findAdminByRealName(String realName) {
        List<EnrollmentAdmin> adminInfos = enrollmentAdminService.list();
        for (EnrollmentAdmin info : adminInfos) {
            if (info.getAdminRealName().equals(realName)) {
                return info;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateAdminInfoFrame.instance = new UpdateAdminInfoFrame();
        });
    }
}
