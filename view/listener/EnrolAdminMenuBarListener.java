package view.listener;

import view.Enroll_frame.*;
import view.panel.AboutUsPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnrolAdminMenuBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        EnrolAdminFrame f = EnrolAdminFrame.instance;
        JMenuItem i = (JMenuItem) e.getSource();

        // 招考信息维护
        if (i == f.maintainEnrollmentInfoItem) {
            EditEnrollmentInfoFrame.instance.setVisible(true);
            EditEnrollmentInfoFrame.instance.updateData();
        }

        // 设置录取分数线
        if (i == f.setAdmissionLineItem) {
            SetAdmissionLineFrame.instance.setVisible(true);
            SetAdmissionLineFrame.instance.resetInput();
        }

        // 现场确认
        if (i == f.onSiteConfirmationItem) {
            OnSiteConfirmationFrame.instance.setVisible(true);
            OnSiteConfirmationFrame.instance.resetInput();
        }

        // 考场分配
        if (i == f.examRoomAllocationItem) {
            ExamRoomAllocationFrame.instance.setVisible(true);
            ExamRoomAllocationFrame.instance.resetInput();
        }

        // 成绩录入
        if (i == f.scoreEntryItem) {
            ScoreEntryFrame.instance.setVisible(true);
            ScoreEntryFrame.instance.resetInput();
        }

        // 招考系统介绍
        if (i == f.aboutEnrolSysItem) {
            JOptionPane.showMessageDialog(f, "招考系统介绍");
        }

        // 学生密码管理
        if (i == f.passwordManagementItem) {
            PasswordFrame.instance.setVisible(true);
        }

        // 关于我们
        if (i == f.aboutUsItem) {
            f.workingPanel.show(AboutUsPanel.instance);
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