package model;

import entity.EnrollmentInfo;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import service.EnrollmentInfoService;

public class EnrollmentInfoTableModel extends AbstractTableModel {
    private List<EnrollmentInfo> enrollmentInfos = new ArrayList<>();
    private String[] columnNames = {"招考信息编号", "招生管理员编号", "考试名称", "报名开始时间", "报名结束时间", "考试日期", "录取分数线"};

    // 设置表头信息
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // 返回行数，即招考信息记录的数量
    @Override
    public int getRowCount() {
        return enrollmentInfos.size();
    }

    // 返回列数，对应招考信息的属性个数
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // 获取指定单元格的值
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EnrollmentInfo info = enrollmentInfos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return info.getEnrollmentInfoId();
            case 1:
                return info.getEnrollmentAdminId();
            case 2:
                return info.getExamName();
            case 3:
                return info.getStartTime();
            case 4:
                return info.getEndTime();
            case 5:
                return info.getExamDate();
            case 6:
                return info.getAdmissionLine();
            default:
                return null;
        }
    }

    // 根据考试名称搜索条件更新数据列表
    public void setDataBasedOnSearch(String searchText) {
        EnrollmentInfoService service = new EnrollmentInfoService();
        enrollmentInfos = service.searchByExamName(searchText);
        fireTableDataChanged();
    }

    // 获取指定索引位置对应的招考信息对象
    public EnrollmentInfo getDataAtIndex(int index) {
        if (index >= 0 && index < enrollmentInfos.size()) {
            return enrollmentInfos.get(index);
        }
        return null;
    }

    // 设置新的数据列表
    public void setData(List<EnrollmentInfo> newData) {
        this.enrollmentInfos = newData;
        fireTableDataChanged();
    }
}