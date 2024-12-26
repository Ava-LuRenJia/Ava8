package view.Enroll_frame;

import util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordFrame extends JFrame {

    // 单例模式（可根据实际需求决定是否保留单例模式，这里模仿原代码添加）
    public static PasswordFrame instance = new PasswordFrame();

    // 用于存储表格数据
    private DefaultTableModel tableModel;
    private JTable table;

    // 下拉列表，用于选择要展示的视图
    private JComboBox<String> viewComboBox;

    public PasswordFrame() {
        // 设置窗体信息
        setTitle("查看学生密码信息");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // 初始化表格模型和表格组件
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        // 初始化下拉列表，添加视图名称选项（这里假设你后续可能会有更多视图，目前先包含要展示的 'examinee_info_view'）
        viewComboBox = new JComboBox<>(new String[]{
                "examinee_info_view"
        });

        // 创建按钮，用于触发加载并显示所选视图数据的操作
        JButton loadButton = new JButton("加载视图数据");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedView = (String) viewComboBox.getSelectedItem();
                try {
                    loadViewData(selectedView);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PasswordFrame.this, "加载视图数据出错：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 使用布局管理器进行界面布局（这里使用 BorderLayout 来进行更清晰的分区布局）
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("选择要查看的视图："));
        panel.add(viewComboBox);
        panel.add(loadButton);

        // 将表格添加到滚动面板，再和操作面板一起添加到窗体
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 设置窗体可见性
        setVisible(true);
    }

    private void loadViewData(String viewName) throws SQLException {
        // 先清空之前表格模型中的数据（如果有），避免重复加载数据时出现混乱
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + viewName)) {
            // 获取结果集的元数据，用于获取列名等信息来初始化表格模型
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 添加列名到表格模型
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }
            // 遍历结果集，将每行数据添加到表格模型
            while (resultSet.next()) {
                List<Object> rowData = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.add(resultSet.getObject(i));
                }
                tableModel.addRow(rowData.toArray());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordFrame.instance = new PasswordFrame();
        });
    }
}
