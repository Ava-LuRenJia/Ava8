package view.Enroll_frame;

import util.CenterPanel;
import view.Loginframe.LoginFrame;
import view.listener.EnrolAdminMenuBarListener;
import view.panel.AboutUsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnrolAdminFrame extends JFrame {
    // 单例模式
    public static EnrolAdminFrame instance = new EnrolAdminFrame();

    // 菜单栏
    public JMenuBar menuBar = new JMenuBar();

    // 招考信息管理 菜单
    public JMenu enrollmentInfoMenu = new JMenu("招考信息管理");
    public JMenuItem maintainEnrollmentInfoItem = new JMenuItem("招考信息维护");
    public JMenuItem setAdmissionLineItem = new JMenuItem("设置录取分数线");

    // 考生流程管理 菜单
    public JMenu examineeProcessMenu = new JMenu("考生流程管理");
    public JMenuItem onSiteConfirmationItem = new JMenuItem("现场确认");
    public JMenuItem passwordManagementItem = new JMenuItem("学生密码管理");

    // 考试安排 菜单
    public JMenu examArrangementMenu = new JMenu("考试安排");
    public JMenuItem examRoomAllocationItem = new JMenuItem("考场分配");
    public JMenuItem scoreEntryItem = new JMenuItem("成绩录入");

    // 关于我们 菜单
    public JMenu aboutMenu = new JMenu("关于我们");
    public JMenuItem aboutEnrolSysItem = new JMenuItem("招考系统介绍");
    public JMenuItem aboutUsItem = new JMenuItem("关于我们");

    // 退出系统 菜单
    public JMenuItem exitMenu = new JMenuItem("退出系统");

    // 工作面板
    public CenterPanel workingPanel;

    public EnrolAdminFrame() {
        // 设置窗体信息
        setTitle("招生管理系统 - 招生管理员  当前管理员：" + LoginFrame.user.getId());
        setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置工作面板
        workingPanel = new CenterPanel(0.85, false);
        setContentPane(workingPanel);
        workingPanel.setSize(this.getWidth(), this.getHeight() - 50);
        workingPanel.show(AboutUsPanel.instance);

        // 设置图标
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/book.png")));
        // 设置菜单栏
        setJMenuBar(menuBar);

        // 添加菜单项
        enrollmentInfoMenu.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(enrollmentInfoMenu);
        examineeProcessMenu.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(examineeProcessMenu);
        examArrangementMenu.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(examArrangementMenu);
        aboutMenu.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(aboutMenu);
        exitMenu.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(exitMenu);

        // 添加子菜单项
        // 招考信息管理
        maintainEnrollmentInfoItem.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        enrollmentInfoMenu.add(maintainEnrollmentInfoItem);
        setAdmissionLineItem.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        enrollmentInfoMenu.add(setAdmissionLineItem);

        // 考生流程管理
        onSiteConfirmationItem.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        examineeProcessMenu.add(onSiteConfirmationItem);
        passwordManagementItem.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        examineeProcessMenu.add(passwordManagementItem);

        // 考试安排
        examRoomAllocationItem.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        examArrangementMenu.add(examRoomAllocationItem);
        scoreEntryItem.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        examArrangementMenu.add(scoreEntryItem);

        // 关于我们
        aboutEnrolSysItem.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        aboutMenu.add(aboutEnrolSysItem);
        aboutUsItem.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/aboutUs-icon.png")));
        aboutMenu.add(aboutUsItem);

        // 退出系统
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(EnrolAdminFrame.this, "是否退出系统？");
                if (choice == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        // 添加监听器
        addListener();

        // 设置可见性
        setVisible(true);
    }

    /**
     * 添加监听器
     */
    public void addListener() {
        EnrolAdminMenuBarListener listener = new EnrolAdminMenuBarListener();

        maintainEnrollmentInfoItem.addActionListener(listener);
        setAdmissionLineItem.addActionListener(listener);
        onSiteConfirmationItem.addActionListener(listener);
        passwordManagementItem.addActionListener(listener);
        examRoomAllocationItem.addActionListener(listener);
        scoreEntryItem.addActionListener(listener);
        aboutEnrolSysItem.addActionListener(listener);
        aboutUsItem.addActionListener(listener);
    }

    public static void main(String[] args) {

    }
}