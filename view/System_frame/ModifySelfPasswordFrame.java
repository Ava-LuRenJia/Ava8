package view.System_frame;

import service.UserService;
import view.Loginframe.LoginFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifySelfPasswordFrame extends JFrame {
    public static ModifySelfPasswordFrame instance = new ModifySelfPasswordFrame();

    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton confirmButton;

    private ModifySelfPasswordFrame() {
        setTitle("修改自身密码");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setupLayout();
        addListeners();

        setVisible(true);
    }

    private void initComponents() {
        oldPasswordField = new JPasswordField(20);
        newPasswordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        confirmButton = new JButton("确认修改");
    }

    private void setupLayout() {
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        contentPane.add(inputPanel, BorderLayout.CENTER);

        JLabel oldPasswordLabel = new JLabel("原密码：");
        inputPanel.add(oldPasswordLabel);
        inputPanel.add(oldPasswordField);

        JLabel newPasswordLabel = new JLabel("新密码：");
        inputPanel.add(newPasswordLabel);
        inputPanel.add(newPasswordField);

        JLabel confirmPasswordLabel = new JLabel("确认新密码：");
        inputPanel.add(confirmPasswordLabel);
        inputPanel.add(confirmPasswordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(confirmButton);
    }

    private void addListeners() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = new String(oldPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // 更完善的密码验证逻辑，可进一步添加长度、复杂度等要求
                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(ModifySelfPasswordFrame.this, "密码不能为空，请重新输入！");
                    return;
                }
                if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(ModifySelfPasswordFrame.this, "新密码与确认密码不一致，请重新输入！");
                    return;
                }

                // 获取当前登录用户的ID
                int currentUserId = LoginFrame.user.getId();
                try {
                    // 调用服务层方法来修改密码，传入旧密码进行验证
                    boolean result = UserService.changePassword(currentUserId, oldPassword, newPassword);
                    if (result) {
                        JOptionPane.showMessageDialog(ModifySelfPasswordFrame.this, "密码修改成功！");
                        resetInput();
                    } else {
                        JOptionPane.showMessageDialog(ModifySelfPasswordFrame.this, "原密码错误，修改失败，请重新输入！");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ModifySelfPasswordFrame.this, "修改密码出现问题，请稍后再试！");
                    ex.printStackTrace();
                }
            }
        });
    }

    public void resetInput() {
        oldPasswordField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModifySelfPasswordFrame.instance = new ModifySelfPasswordFrame();
        });
    }
}