package view.System_frame;

import entity.EnrollmentAdmin;
import service.EnrollmentAdminService;
import service.EnrollmentInfoService;
import service.UserService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DeleteAdminFrame extends JFrame {
    public static DeleteAdminFrame instance = new DeleteAdminFrame();

    private JComboBox<String> adminComboBox;
    private JButton deleteButton;

    private EnrollmentAdminService enrollmentAdminService = new EnrollmentAdminService();
    private EnrollmentInfoService enrollmentInfoService = new EnrollmentInfoService();
    private UserService userService = new UserService();

    private DeleteAdminFrame() {
        setTitle("删除管理员");
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
        deleteButton = new JButton("删除");
    }

    private void setupLayout() {
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        contentPane.add(inputPanel, BorderLayout.CENTER);

        JLabel selectAdminLabel = new JLabel("选择要删除的管理员：");
        inputPanel.add(selectAdminLabel);
        inputPanel.add(adminComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(deleteButton);
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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAdmin = (String) adminComboBox.getSelectedItem();
                if (selectedAdmin == null) {
                    JOptionPane.showMessageDialog(DeleteAdminFrame.this, "请选择要删除的管理员！");
                    return;
                }

                // 根据真实姓名查找对应管理员的ID（这里假设真实姓名唯一，可根据实际情况调整查找逻辑）
                EnrollmentAdmin adminToDelete = findAdminByRealName(selectedAdmin);
                if (adminToDelete!= null) {
                    int adminId = adminToDelete.getEnrollmentAdminId();

                    try {
                        // 先删除 enrollment_info 表中与该管理员相关的所有信息（调用修改后的方法按adminId删除多条记录）
                        enrollmentInfoService.deleteByAdminId(adminId);

                        // 删除 enrollment_admin 表中的管理员信息
                        enrollmentAdminService.delete(adminId);


                        // 删除 user 表中的对应用户信息（这里假设按业务逻辑先处理user表相关关联，具体按实际情况调整）
                        boolean userDeleteResult = userService.delete(adminId);
                        if (!userDeleteResult) {
                            JOptionPane.showMessageDialog(DeleteAdminFrame.this, "删除 user 表中用户信息失败，请检查相关配置或联系系统管理员！");
                            return;
                        }

                        JOptionPane.showMessageDialog(DeleteAdminFrame.this, "管理员删除成功！");
                        loadAdminData();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DeleteAdminFrame.this, "删除操作失败，请检查相关配置或联系系统管理员！错误信息：" + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(DeleteAdminFrame.this, "删除管理员失败，未找到对应管理员信息，请重新选择！");
                }
            }
        });
    }


    public void resetInput() {
        adminComboBox.setSelectedIndex(-1);
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
            DeleteAdminFrame.instance = new DeleteAdminFrame();
        });
    }
}