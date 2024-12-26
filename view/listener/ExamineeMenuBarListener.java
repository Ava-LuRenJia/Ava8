package view.listener;

import view.examinee_frame.*;
import view.examinee_frame.LoginHistoryFrame;
import view.System_frame.*;
import view.panel.AboutUsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExamineeMenuBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ExamineeFrame f = ExamineeFrame.instance;
        JMenuItem i = (JMenuItem) e.getSource();

        // 考生注册
        if (i == f.registerItem) {
            RegisterFrame.instance.setVisible(true);
            RegisterFrame.instance.resetInput();
        }

        // 查看全部登录历史
        if (i == f.viewLoginHistoryItem) {
            LoginHistoryFrame.instance.setVisible(true);
        }

        // 修改自身密码
        if (i == f.modifySelfPasswordItem) {
            ModifySelfPasswordFrame.instance.setVisible(true);
            ModifySelfPasswordFrame.instance.resetInput();
        }

        // 查看报考须知
        if (i == f.viewEnrollmentNoticeItem) {
            EnrollmentNoticeFrame.instance.showEnrollmentNoticeContent();
        }

        // 成绩查询
        if (i == f.scoreQueryItem) {
            ScoreQueryFrame.instance.setVisible(true);
            ScoreQueryFrame.instance.queryAndShowScores();
        }

        // 录取查询
        if (i == f.admissionQueryItem) {
            AdmissionQueryFrame.instance.setVisible(true);
            AdmissionQueryFrame.instance.queryAndShowAdmissionStatus();
        }

        // 关于我们
        if (i == f.aboutUsItem) {
            f.workingPanel.show(AboutUsPanel.instance);
        }

        // 退出系统
        if (i == f.exitMenu) {
            int choice = JOptionPane.showConfirmDialog(ExamineeFrame.instance, "是否退出系统？");
            if (choice == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }

    /**
     * 显示另一个窗体
     */
    public void show(JFrame hideFrame, JFrame showFrame) {
        hideFrame.setVisible(false);
        showFrame.setVisible(true);
    }
}
