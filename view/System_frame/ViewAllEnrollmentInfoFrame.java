package view.System_frame;

import model.ApplicationInfoTableModel;
import service.ApplicationInfoService;

import javax.swing.*;

public class ViewAllEnrollmentInfoFrame extends JFrame {
    public static ViewAllEnrollmentInfoFrame instance = new ViewAllEnrollmentInfoFrame();
    private JTable table;

    private ViewAllEnrollmentInfoFrame() {
        setTitle("查看全部报考信息");
        setSize(800, 600);
        setLocationRelativeTo(null);

        // 使用之前定义的TableModel来创建表格
        ApplicationInfoTableModel tableModel = new ApplicationInfoTableModel();
        table = new JTable(tableModel);

        // 将表格添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        setVisible(true);
    }

    public void updateData() {
        ApplicationInfoTableModel model = (ApplicationInfoTableModel) table.getModel();
        model.ais = new ApplicationInfoService().list();
        model.fireTableDataChanged();
    }
}
