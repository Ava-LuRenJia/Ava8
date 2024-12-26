package view.examinee_frame;

import util.CenterPanel;
import view.Enroll_frame.EnrolAdminFrame;
import view.Loginframe.LoginFrame;
import view.listener.ExamineeMenuBarListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExamineeFrame extends JFrame {
    // 单例模式
    public static ExamineeFrame instance = new ExamineeFrame();

    // 菜单栏
    public JMenuBar menuBar = new JMenuBar();

    // 注册 菜单
    public JMenu registerMenu = new JMenu("注册");
    public JMenuItem registerItem = new JMenuItem("考生注册");

    // 查看登录历史 菜单
    public JMenu loginHistoryMenu = new JMenu("查看登录历史");
    public JMenuItem viewLoginHistoryItem = new JMenuItem("查看全部登录历史");

    // 修改密码 菜单
    public JMenu modifyPasswordMenu = new JMenu("修改密码");
    public JMenuItem modifySelfPasswordItem = new JMenuItem("修改自身密码");

    // 报考相关 菜单
    public JMenu enrollmentRelatedMenu = new JMenu("报考相关");
    public JMenuItem viewEnrollmentNoticeItem = new JMenuItem("查看报考须知");

    // 成绩相关 菜单
    public JMenu scoreRelatedMenu = new JMenu("成绩相关");
    public JMenuItem scoreQueryItem = new JMenuItem("成绩查询");

    // 录取相关 菜单
    public JMenu admissionRelatedMenu = new JMenu("录取相关");
    public JMenuItem admissionQueryItem = new JMenuItem("录取查询");

    // 关于我们 菜单
    public JMenu aboutMenu = new JMenu("关于我们");
    public JMenuItem aboutUsItem = new JMenuItem("关于我们");

    // 退出系统 菜单
    public JMenuItem exitMenu = new JMenuItem("退出系统");

    // 工作面板
    public CenterPanel workingPanel;

    public ExamineeFrame() {
        // 设置窗体信息
        setTitle("招生管理系统 - 考生  当前考生：" + LoginFrame.user.getId());
        setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置工作面板
        workingPanel = new CenterPanel(0.85, false);
        setContentPane(workingPanel);
        workingPanel.setSize(this.getWidth(), this.getHeight() - 50);

        // 设置图标（这里假设图标路径等需按实际情况调整，暂用和原代码类似写法示例）
        setIconImage(Toolkit.getDefaultToolkit().getImage(ExamineeFrame.class.getResource("/images/AI_icon.png")));
        // 设置菜单栏
        setJMenuBar(menuBar);

        // 添加菜单项
        registerMenu.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(registerMenu);
        loginHistoryMenu.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(loginHistoryMenu);
        modifyPasswordMenu.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(modifyPasswordMenu);
        enrollmentRelatedMenu.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(enrollmentRelatedMenu);
        scoreRelatedMenu.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(scoreRelatedMenu);
        admissionRelatedMenu.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(admissionRelatedMenu);
        aboutMenu.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/weihu-icon.png")));
        menuBar.add(aboutMenu);
        exitMenu.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(exitMenu);

        // 添加子菜单项

        // 注册
        registerItem.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        registerMenu.add(registerItem);

        // 查看登录历史
        viewLoginHistoryItem.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        loginHistoryMenu.add(viewLoginHistoryItem);

        // 修改密码
        modifySelfPasswordItem.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        modifyPasswordMenu.add(modifySelfPasswordItem);

        // 报考相关
        viewEnrollmentNoticeItem.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        enrollmentRelatedMenu.add(viewEnrollmentNoticeItem);

        // 成绩相关
        scoreQueryItem.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        scoreRelatedMenu.add(scoreQueryItem);

        // 录取相关
        admissionQueryItem.setIcon(new ImageIcon(ExamineeFrame.class.getResource("/images/add-icon.png")));
        admissionRelatedMenu.add(admissionQueryItem);

        // 关于我们
        aboutUsItem.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/aboutUs-icon.png")));
        aboutMenu.add(aboutUsItem);

        // 退出系统
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(ExamineeFrame.this, "是否退出系统？");
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
        ExamineeMenuBarListener listener = new ExamineeMenuBarListener();

        registerItem.addActionListener(listener);
        viewLoginHistoryItem.addActionListener(listener);
        modifySelfPasswordItem.addActionListener(listener);
        viewEnrollmentNoticeItem.addActionListener(listener);
        scoreQueryItem.addActionListener(listener);
        admissionQueryItem.addActionListener(listener);
        aboutUsItem.addActionListener(listener);
    }

    public static void main(String[] args) {
        // 可以在这里进行一些测试相关的启动操作等，暂时留空示例
    }
}
