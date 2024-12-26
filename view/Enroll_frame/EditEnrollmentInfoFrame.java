package view.Enroll_frame;

import entity.EnrollmentInfo;
import model.EnrollmentInfoTableModel;
import service.EnrollmentInfoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditEnrollmentInfoFrame extends JFrame {
    public static EditEnrollmentInfoFrame instance = new EditEnrollmentInfoFrame();

    // 创建service层对象
    private EnrollmentInfoService enrollmentInfoService = new EnrollmentInfoService();

    // 创建表格
    private JTable infoTable;

    // 创建搜索域文本框
    private JTextField searchField;

    // 创建显示区域文本框，对应招考信息各属性
    private JTextField infoIdField;
    private JTextField adminIdField;
    private JTextField examNameField;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private JTextField examDateField;
    private JTextField admissionLineField;

    // 创建按钮
    private JButton updateButton;
    private JButton deleteButton;

    public EditEnrollmentInfoFrame() {
        // 设置窗体信息
        setTitle("招考信息维护");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        // 设置窗口图标（假设存在对应的图标资源路径，需根据实际调整）
        setIconImage(Toolkit.getDefaultToolkit().getImage(EditEnrollmentInfoFrame.class.getResource("/images/BookiconMax.png")));

        // 初始化面板
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(20, 30, 10, 30));

        // 标题面板
        JPanel titleSearchPanel = new JPanel();
        titleSearchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        contentPane.add(titleSearchPanel);


        // 创建表格面板
        JPanel tablePanel = new JPanel();
        contentPane.add(tablePanel);

        // 创建表格及设置滚动面板
        EnrollmentInfoTableModel tableModel = new EnrollmentInfoTableModel();
        infoTable = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(infoTable);
        sp.setPreferredSize(new Dimension(600, 200));
        tablePanel.add(sp);

        // 数据显示编辑面板
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        contentPane.add(editPanel);

        // 创建编号标签、文本框
        JLabel labelInfoId = new JLabel("招考信息编号：");
        editPanel.add(labelInfoId);
        infoIdField = new JTextField();
        infoIdField.setEditable(false);
        infoIdField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(infoIdField);

        // 创建招生管理员编号标签、文本框
        JLabel labelAdminId = new JLabel("招生管理员编号：");
        editPanel.add(labelAdminId);
        adminIdField = new JTextField();
        adminIdField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(adminIdField);

        // 创建考试名称标签、文本框
        JLabel labelExamName = new JLabel("考试名称：");
        editPanel.add(labelExamName);
        examNameField = new JTextField();
        examNameField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(examNameField);

        // 创建报名开始时间标签、文本框
        JLabel labelStartTime = new JLabel("报名开始时间：");
        editPanel.add(labelStartTime);
        startTimeField = new JTextField();
        startTimeField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(startTimeField);

        // 创建报名结束时间标签、文本框
        JLabel labelEndTime = new JLabel("报名结束时间：");
        editPanel.add(labelEndTime);
        endTimeField = new JTextField();
        endTimeField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(endTimeField);

        // 创建考试日期标签、文本框
        JLabel labelExamDate = new JLabel("考试日期：");
        editPanel.add(labelExamDate);
        examDateField = new JTextField();
        examDateField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(examDateField);

        // 创建录取分数线标签、文本框
        JLabel labelAdmissionLine = new JLabel("录取分数线：");
        editPanel.add(labelAdmissionLine);
        admissionLineField = new JTextField();
        admissionLineField.setPreferredSize(new Dimension(200, 30));
        editPanel.add(admissionLineField);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 80, 0));
        contentPane.add(buttonPanel);

        // 创建更新按钮
        updateButton = new JButton("更新");
        updateButton.setIcon(new ImageIcon(EditEnrollmentInfoFrame.class.getResource("/images/BookiconMax.png")));
        buttonPanel.add(updateButton);

        // 创建删除按钮
        deleteButton = new JButton("删除");
        deleteButton.setIcon(new ImageIcon(EditEnrollmentInfoFrame.class.getResource("/images/BookiconMax.png")));
        buttonPanel.add(deleteButton);


        infoTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = infoTable.getSelectedRow();
                if (index!= -1) {
                    EnrollmentInfoTableModel tableModel = (EnrollmentInfoTableModel) infoTable.getModel();
                    EnrollmentInfo selectedInfo = tableModel.getDataAtIndex(index);
                    infoIdField.setText(String.valueOf(selectedInfo.getEnrollmentInfoId()));
                    adminIdField.setText(String.valueOf(selectedInfo.getEnrollmentAdminId()));
                    examNameField.setText(selectedInfo.getExamName());
                    // 格式化日期后设置到文本框
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    startTimeField.setText(sdf.format(selectedInfo.getStartTime()));
                    endTimeField.setText(sdf.format(selectedInfo.getEndTime()));
                    examDateField.setText(sdf.format(selectedInfo.getExamDate()));
                    admissionLineField.setText(String.valueOf(selectedInfo.getAdmissionLine()));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (infoIdField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(EditEnrollmentInfoFrame.this, "请先选择一条记录！");
                    return;
                }
                // 新增对adminIdField的非空判断
                if (adminIdField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(EditEnrollmentInfoFrame.this, "招生管理员编号不能为空！");
                    return;
                }
                // 新增对examNameField的非空判断
                if (examNameField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(EditEnrollmentInfoFrame.this, "考试名称不能为空！");
                    return;
                }
                int infoId = Integer.parseInt(infoIdField.getText());
                int adminId = Integer.parseInt(adminIdField.getText());
                String examName = examNameField.getText();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startTime = null;
                try {
                    startTime = sdf.parse(startTimeField.getText());
                } catch (java.text.ParseException ex) {
                    ex.printStackTrace();
                }
                Date endTime = null;
                try {
                    endTime = sdf.parse(endTimeField.getText());
                } catch (java.text.ParseException ex) {
                    ex.printStackTrace();
                }
                Date examDate = null;
                try {
                    examDate = sdf.parse(examDateField.getText());
                } catch (java.text.ParseException ex) {
                    ex.printStackTrace();
                }

                // 新增对admissionLineField的非空判断和格式判断
                if (admissionLineField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(EditEnrollmentInfoFrame.this, "录取分数线不能为空！");
                    return;
                }
                double admissionLine;
                try {
                    admissionLine = Double.parseDouble(admissionLineField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EditEnrollmentInfoFrame.this, "录取分数线格式不正确！");
                    return;
                }

                enrollmentInfoService.update(infoId, adminId, examName, startTime, endTime, examDate, admissionLine);
                updateData();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (infoIdField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(EditEnrollmentInfoFrame.this, "请先选择一条记录！");
                    return;
                }
                int choice = JOptionPane.showConfirmDialog(EditEnrollmentInfoFrame.this, "是否删除？");
                if (choice!= JOptionPane.OK_OPTION) {
                    return;
                }
                int infoId = Integer.parseInt(infoIdField.getText());
                enrollmentInfoService.delete(infoId);
                updateData();
            }
        });

        // 设置窗体可见性
        setVisible(true);
    }

    public void updateData() {
        EnrollmentInfoTableModel tableModel = (EnrollmentInfoTableModel) infoTable.getModel();
        tableModel.setData(enrollmentInfoService.list());
        resetInput();
        infoTable.updateUI();
    }

    public void resetInput() {
        infoIdField.setText("");
        adminIdField.setText("");
        examNameField.setText("");
        startTimeField.setText("");
        endTimeField.setText("");
        examDateField.setText("");
        admissionLineField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditEnrollmentInfoFrame.instance = new EditEnrollmentInfoFrame();
        });
    }
}