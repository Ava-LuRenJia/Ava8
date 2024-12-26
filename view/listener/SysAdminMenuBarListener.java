package view.listener;

import view.System_frame.*;
import view.panel.AboutUsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SysAdminMenuBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SysAdminFrame f = SysAdminFrame.instance;
        JMenuItem i = (JMenuItem) e.getSource();

        // 查看全部报名信息
        if (i == f.viewAllEnrollmentInfoItem) {
            ViewAllEnrollmentInfoFrame.instance.setVisible(true);
            ViewAllEnrollmentInfoFrame.instance.updateData();
        }

        // 查看全部成绩信息
        if (i == f.viewAllScoreInfoItem) {
            ViewAllScoreInfoFrame.instance.setVisible(true);
            ViewAllScoreInfoFrame.instance.updateData();
        }

        // 修改自身密码
        if (i == f.modifySelfPasswordItem) {
            ModifySelfPasswordFrame.instance.setVisible(true);
            ModifySelfPasswordFrame.instance.resetInput();
        }

        // 添加管理员
        if (i == f.addAdminItem) {
            AddAdminFrame.instance.setVisible(true);
            AddAdminFrame.instance.resetInput();
        }

        // 删除管理员
        if (i == f.deleteAdminItem) {
            DeleteAdminFrame.instance.setVisible(true);
            DeleteAdminFrame.instance.resetInput();
        }

        // 更新管理员信息
        if (i == f.updateAdminInfoItem) {
            UpdateAdminInfoFrame.instance.setVisible(true);
            UpdateAdminInfoFrame.instance.resetInput();
        }

        // 查看数据库视图信息
        if (i == f.backupDatabaseItem) {
            BackupDatabaseFrame.instance.setVisible(true);
        }


        // 关于我们
        if (i == f.aboutUsItem) {
            f.workingPanel.show(AboutUsPanel.instance);
        }

        // 这里可以添加类似原代码中弹出提示信息等其他逻辑，比如针对“关于系统”等相关菜单项
    }

    /**
     * 显示另一个窗体（这里可以根据实际情况完善逻辑，比如进行一些界面切换时的数据传递等操作，目前简单模仿原代码结构）
     */
    public void show(JFrame hideFrame, JFrame showFrame) {
        hideFrame.setVisible(false);
        showFrame.setVisible(true);
    }
}
