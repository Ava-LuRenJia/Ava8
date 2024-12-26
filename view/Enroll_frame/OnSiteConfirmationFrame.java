package view.Enroll_frame;

import dao.ApplicationInfoDAO;
import entity.ApplicationInfo;
import service.ApplicationInfoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OnSiteConfirmationFrame extends JFrame {
    public static OnSiteConfirmationFrame instance = new OnSiteConfirmationFrame();

    private JTable applicationTable;
    private JButton confirmButton;

    private ApplicationInfoService applicationInfoService = new ApplicationInfoService();
    private ApplicationInfoDAO applicationInfoDAO = new ApplicationInfoDAO();

    private OnSiteConfirmationFrame() {
        setTitle("现场确认");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setupLayout();
        loadData();
        addListeners();

        setVisible(true);
    }

    private void initComponents() {
        applicationTable = new JTable();
        confirmButton = new JButton("确认");
    }

    private void setupLayout() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(applicationTable), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadData() {
        List<ApplicationInfo> applicationInfos = applicationInfoService.list();
        String[] columnNames = {"报考信息编号", "考生编号", "报考时间", "考试类型"};
        Object[][] data = new Object[applicationInfos.size()][4];
        for (int i = 0; i < applicationInfos.size(); i++) {
            ApplicationInfo ai = applicationInfos.get(i);
            data[i][0] = ai.getApplicationId();
            data[i][1] = ai.getExamineeId();
            data[i][2] = ai.getApplicationTime();
            data[i][3] = ai.getExamType();
        }
        applicationTable.setModel(new DefaultTableModel(data, columnNames));
    }

    private void addListeners() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = applicationTable.getSelectedRow();
                if (selectedRow!= -1) {
                    int applicationId = (int) applicationTable.getValueAt(selectedRow, 0);
                    // 弹出提示信息的弹窗
                    JOptionPane.showMessageDialog(OnSiteConfirmationFrame.this, "已对报考信息编号为 " + applicationId + " 的记录进行现场确认");
                    // 这里可以添加具体的现场确认业务逻辑，比如更新相关状态等
                    // 以下是简单示例，实际需根据具体数据库操作完善
                    ApplicationInfo applicationInfo = applicationInfoService.getById(applicationId);
                    if (applicationInfo!= null) {
                        // 假设数据库中有个字段表示是否已现场确认，这里更新为已确认状态（示例代码，字段名等按实际情况调整）
                        applicationInfo.setConfirmed(1);
                    }
                }
            }
        });
    }

    public void resetInput() {
        // 此处可以根据实际需求重置界面相关输入内容，目前暂无需操作
    }
}