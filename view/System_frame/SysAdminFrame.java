package view.System_frame;

import util.CenterPanel;
import view.Enroll_frame.EnrolAdminFrame;
import view.Loginframe.LoginFrame;
import view.listener.SysAdminMenuBarListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SysAdminFrame extends JFrame {
    // 单例模式
    public static SysAdminFrame instance = new SysAdminFrame();

    // 菜单栏
    public JMenuBar menuBar = new JMenuBar();


    // 查看报名信息 菜单
    public JMenu viewEnrollmentInfoMenu = new JMenu("查看报名信息");
    public JMenuItem viewAllEnrollmentInfoItem = new JMenuItem("查看全部报名信息");

    // 查看成绩信息 菜单
    public JMenu viewScoreInfoMenu = new JMenu("查看成绩信息");
    public JMenuItem viewAllScoreInfoItem = new JMenuItem("查看全部成绩信息");

    // 修改密码 菜单
    public JMenu modifyPasswordMenu = new JMenu("修改密码");
    public JMenuItem modifySelfPasswordItem = new JMenuItem("修改自身密码");

    // 管理员维护 菜单
    public JMenu adminMaintenanceMenu = new JMenu("管理员维护");
    public JMenuItem addAdminItem = new JMenuItem("添加管理员");
    public JMenuItem deleteAdminItem = new JMenuItem("删除管理员");
    public JMenuItem updateAdminInfoItem = new JMenuItem("更新管理员信息");

    // 数据库维护 菜单
    public JMenu databaseMaintenanceMenu = new JMenu("数据库维护");
    public JMenuItem backupDatabaseItem = new JMenuItem("查看视图信息");
    // 关于我们 菜单
    public JMenu aboutMenu = new JMenu("关于我们");
    public JMenuItem aboutUsItem = new JMenuItem("关于我们");


    // 退出系统 菜单
    public JMenuItem exitMenu = new JMenuItem("退出系统");

    // 工作面板
    public CenterPanel workingPanel;

    public SysAdminFrame() {
        // 设置窗体信息
        setTitle("招生管理系统 - 系统管理员  当前管理员：" + LoginFrame.user.getId());
        setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置工作面板
        workingPanel = new CenterPanel(0.85, false);
        setContentPane(workingPanel);
        workingPanel.setSize(this.getWidth(), this.getHeight() - 50);

        // 设置图标
        setIconImage(Toolkit.getDefaultToolkit().getImage(SysAdminFrame.class.getResource("/images/AI_icon.png")));
        // 设置菜单栏
        setJMenuBar(menuBar);

        // 添加菜单项
        viewEnrollmentInfoMenu.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(viewEnrollmentInfoMenu);
        viewScoreInfoMenu.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(viewScoreInfoMenu);
        modifyPasswordMenu.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(modifyPasswordMenu);
        adminMaintenanceMenu.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(adminMaintenanceMenu);
        databaseMaintenanceMenu.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(databaseMaintenanceMenu);
        aboutMenu.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(aboutMenu);
        exitMenu.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        menuBar.add(exitMenu);

        // 添加子菜单项

        // 查看报名信息
        viewAllEnrollmentInfoItem.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        viewEnrollmentInfoMenu.add(viewAllEnrollmentInfoItem);

        // 查看成绩信息
        viewAllScoreInfoItem.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        viewScoreInfoMenu.add(viewAllScoreInfoItem);

        // 修改密码
        modifySelfPasswordItem.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        modifyPasswordMenu.add(modifySelfPasswordItem);

        // 管理员维护
        addAdminItem.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        adminMaintenanceMenu.add(addAdminItem);
        deleteAdminItem.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        adminMaintenanceMenu.add(deleteAdminItem);
        updateAdminInfoItem.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        adminMaintenanceMenu.add(updateAdminInfoItem);

        // 数据库维护
        backupDatabaseItem.setIcon(new ImageIcon(SysAdminFrame.class.getResource("/images/add-icon.png")));
        databaseMaintenanceMenu.add(backupDatabaseItem);
        // 关于我们
        aboutUsItem.setIcon(new ImageIcon(EnrolAdminFrame.class.getResource("/images/aboutUs-icon.png")));
        aboutMenu.add(aboutUsItem);

        // 退出系统
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(SysAdminFrame.this, "是否退出系统？");
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
        SysAdminMenuBarListener listener = new SysAdminMenuBarListener();

        viewAllEnrollmentInfoItem.addActionListener(listener);
        viewAllScoreInfoItem.addActionListener(listener);
        modifySelfPasswordItem.addActionListener(listener);
        addAdminItem.addActionListener(listener);
        deleteAdminItem.addActionListener(listener);
        updateAdminInfoItem.addActionListener(listener);
        backupDatabaseItem.addActionListener(listener);
        aboutUsItem.addActionListener(listener);
    }

    public static void main(String[] args) {

    }
}
