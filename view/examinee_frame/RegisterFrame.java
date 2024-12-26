package view.examinee_frame;

import dao.AdmissionInfoDAO;
import dao.ApplicationInfoDAO;
import dao.ExamineeDAO;
import dao.ScoreDAO;
import entity.*;
import service.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterFrame extends JFrame {
    public static RegisterFrame instance = new RegisterFrame();

    private JTextField IDField;
    private JTextField nameField;
    private JComboBox<String> genderComboBox;
    private JTextField birthDateField;
    private JTextField contactNumberField;
    private JTextField emailField;
    private JTextField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;

    private ExamineeService examineeService = new ExamineeService();
    private ExamineeDAO examineeDAO = new ExamineeDAO();
    private ExamRoomAllocationService examRoomAllocationService = new ExamRoomAllocationService();
    private AdmissionInfoService admissionInfoService = new AdmissionInfoService();
    private AdmissionInfoDAO admissionInfoDAO = new AdmissionInfoDAO();
    private ApplicationInfoService applicationInfoService = new ApplicationInfoService();
    private ApplicationInfoDAO applicationInfoDAO = new ApplicationInfoDAO();
    private ScoreDAO scoreDAO = new ScoreDAO();
    private ScoreService scoreService = new ScoreService();

    private RegisterFrame() {
        setTitle("考生注册");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setupLayout();
        addListeners();

        setVisible(true);
    }

    private void initComponents() {
        IDField = new JTextField(20);
        nameField = new JTextField(20);
        genderComboBox = new JComboBox<>(new String[]{"男", "女"});
        birthDateField = new JTextField(20);
        contactNumberField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JTextField(20);
        confirmPasswordField = new JPasswordField(20);
        registerButton = new JButton("注册");
    }

    private void setupLayout() {
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        contentPane.add(inputPanel, BorderLayout.CENTER);

        JLabel IDLabel = new JLabel("ID：");
        inputPanel.add(IDLabel);
        inputPanel.add(IDField);

        JLabel nameLabel = new JLabel("姓名：");
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel genderLabel = new JLabel("性别：");
        inputPanel.add(genderLabel);
        inputPanel.add(genderComboBox);

        JLabel birthDateLabel = new JLabel("出生日期（格式：yyyy-MM-dd）：");
        inputPanel.add(birthDateLabel);
        inputPanel.add(birthDateField);

        JLabel contactNumberLabel = new JLabel("联系电话：");
        inputPanel.add(contactNumberLabel);
        inputPanel.add(contactNumberField);

        JLabel emailLabel = new JLabel("邮箱：");
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        JLabel passwordLabel = new JLabel("密码：");
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("确认密码：");
        inputPanel.add(confirmPasswordLabel);
        inputPanel.add(confirmPasswordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(registerButton);
    }

    private void addListeners() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(IDField.getText());
                String name = nameField.getText();
                String gender = (String) genderComboBox.getSelectedItem();
                String birthDate = birthDateField.getText();
                String contactNumber = contactNumberField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // 验证日期格式是否符合要求
                if (!isValidDateFormat(birthDate)) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "出生日期格式不正确，请重新输入（格式：yyyy-MM-dd）！");
                    return;
                }

                // 输入验证
                if (name.isEmpty() || gender == null || birthDate.isEmpty() ||
                        contactNumber.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "所有信息都不能为空，请重新输入！");
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "密码与确认密码不一致，请重新输入！");
                    return;
                }

                // 创建User对象并添加到user表
                User newUser = new User();
                newUser.setId(id);
                newUser.setUserName(name);
                newUser.setPassword(password);
                newUser.setIdentity(0);

                boolean userResult = UserService.register(newUser);
                if (!userResult) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "用户信息添加失败，请重新输入！");
                    return;
                }

                // 创建Examinee对象
                Examinee newExaminee = new Examinee();
                newExaminee.setExamineeId(id);
                newExaminee.setName(name);
                newExaminee.setGender(gender);
                newExaminee.setBirthDate(birthDate);
                newExaminee.setContactNumber(contactNumber);
                newExaminee.setEmail(email);

                // 调用服务层方法添加考生信息到examinee表
                boolean examineeResult = examineeService.add(newExaminee.getExamineeId() , newExaminee.getName() , newExaminee.getGender() ,
                        newExaminee.getBirthDate(),  newExaminee.getContactNumber() , newExaminee.getEmail())  ;
                if (!examineeResult) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "考生信息添加失败，请重新输入！");
                    return;
                }

                // 向admission_info表插入数据
                AdmissionInfo newAdmissionInfo = new AdmissionInfo();
                newAdmissionInfo.setExamineeId(id);
                newAdmissionInfo.setAdmitted(false);
                newAdmissionInfo.setAdmissionTime(new Date(2001 - 1900, 0, 1)); // 将时间转换为Date类型，这里表示2001-01-01
                boolean AdmissionInfoResult = admissionInfoService.add(newAdmissionInfo.getExamineeId() , false , newAdmissionInfo.getAdmissionTime());
                if (!AdmissionInfoResult) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "录取信息添加失败，请重新输入！");
                    return;
                }

                // 向application_info表插入数据
                ApplicationInfo newApplicationInfo = new ApplicationInfo();
                newApplicationInfo.setExamineeId(id);
                newApplicationInfo.setApplicationTime(new Date(2001 - 1900, 0, 1));
                boolean ApplicationInfoResult = applicationInfoService.add(newApplicationInfo.getExamineeId() , newApplicationInfo.getApplicationTime() ,
                        "未指定考试类型" );
                if (!ApplicationInfoResult) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "报考信息添加失败，请重新输入！");
                    return;
                }

                // 向exam_room_allocation表插入数据（示例，根据实际需求完善具体逻辑和字段值）
                ExamRoomAllocation newExamRoomAllocation = new ExamRoomAllocation();
                newExamRoomAllocation.setExamineeId(id);
                boolean ExamRoomAllocationResult = examRoomAllocationService.add(newExamRoomAllocation.getExamineeId() , "未分配", "未分配");
                if (!ExamRoomAllocationResult) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "考场分配信息添加失败，请重新输入！");
                    return;
                }

                // 向score表插入数据
                Score newscore = new Score();
                newscore.setExamineeId(id);
                boolean scoreResult = scoreService.add(newscore.getExamineeId(), "未指定科目", 0, new Date(2001 - 1900, 0, 1));
                if (!scoreResult) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "添加成绩信息失败，请重新输入！");
                    return;
                }

                JOptionPane.showMessageDialog(RegisterFrame.this, "考生注册成功！");
                resetInput();
            }
        });
    }

    private boolean isValidDateFormat(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // 设置严格模式，不允许不符合格式的日期通过
        try {
            sdf.parse(date);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void resetInput() {
        IDField.setText("");
        nameField.setText("");
        genderComboBox.setSelectedIndex(-1);
        birthDateField.setText("");
        contactNumberField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterFrame.instance = new RegisterFrame();
        });
    }
}