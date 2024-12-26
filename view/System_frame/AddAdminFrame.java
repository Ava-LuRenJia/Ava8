package view.System_frame;

import dao.EnrollmentAdminDAO;
import dao.UserDAO;
import entity.User;
import service.EnrollmentAdminService;
import service.UserService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAdminFrame extends JFrame {
    public static AddAdminFrame instance = new AddAdminFrame();

    private JTextField IdField;
    private JTextField realNameField;
    private JTextField responsibleAreaField;
    private JButton addButton;

    private EnrollmentAdminService enrollmentAdminService = new EnrollmentAdminService();
    private EnrollmentAdminDAO enrollmentAdminDAO = new EnrollmentAdminDAO();
    private UserService userService = new UserService();
    private UserDAO userDAO = new UserDAO();

    private AddAdminFrame() {
        setTitle("添加管理员");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setupLayout();
        addListeners();

        setVisible(true);
    }

    private void initComponents() {
        IdField = new JTextField(20);
        realNameField = new JTextField(20);
        responsibleAreaField = new JTextField(20);
        addButton = new JButton("添加");
    }

    private void setupLayout() {
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JPanel inputPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        contentPane.add(inputPanel, BorderLayout.CENTER);

        JLabel enrollmentAdminId = new JLabel("招生管理员id：");
        inputPanel.add(enrollmentAdminId);
        inputPanel.add(IdField);

        JLabel realNameLabel = new JLabel("真实姓名：");
        inputPanel.add(realNameLabel);
        inputPanel.add(realNameField);

        JLabel responsibleAreaLabel = new JLabel("负责区域：");
        inputPanel.add(responsibleAreaLabel);
        inputPanel.add(responsibleAreaField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(addButton);
    }

    private void addListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int enrollmentAdminId = Integer.parseInt(IdField.getText());
                String realName = realNameField.getText();
                String responsibleArea = responsibleAreaField.getText();

                // 输入验证
                if (realName.isEmpty() || responsibleArea.isEmpty()) {
                    JOptionPane.showMessageDialog(AddAdminFrame.this, "输入信息不能为空，请重新输入！");
                    return;
                }

                // 向user表添加对应的用户信息
                User newUser = new User();
                newUser.setId(enrollmentAdminId);
                newUser.setUserName(realName);
                newUser.setPassword("1111");
                newUser.setIdentity(2);

                boolean userResult = userService.add(newUser);
                if (!userResult) {
                    JOptionPane.showMessageDialog(AddAdminFrame.this, "添加用户信息到user表失败，请检查相关配置或联系系统管理员！");
                    return;
                }

                // 向enrollment_admin表添加招生管理员信息（调用相应服务层方法）
                boolean enrollmentResult = enrollmentAdminService.add(enrollmentAdminId, realName, responsibleArea);
                if (!enrollmentResult) {
                    JOptionPane.showMessageDialog(AddAdminFrame.this, "添加招生管理员信息到enrollment_admin表失败，请检查输入信息或联系系统管理员！");
                    return;
                }

                JOptionPane.showMessageDialog(AddAdminFrame.this, "管理员添加成功！");
                resetInput();
            }
        });
    }

    public void resetInput() {
        realNameField.setText("");
        responsibleAreaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddAdminFrame.instance = new AddAdminFrame();
        });
    }
}