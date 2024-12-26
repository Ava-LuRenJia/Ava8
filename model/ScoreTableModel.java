package model;

import entity.Score;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ScoreTableModel extends AbstractTableModel {
    private List<Score> data = new ArrayList<>();
    private String[] columnNames = new String[]{"成绩编号", "考生编号", "考试科目", "成绩分数", "考试时间"};


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 4) {
            return java.util.Date.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Score s = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getScoreId();
            case 1:
                return s.getExamineeId();
            case 2:
                return s.getSubject();
            case 3:
                return s.getScoreValue();
            case 4:
                return s.getExamTime();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }

    /**
     * 根据传入的成绩信息列表更新表格模型的数据，并触发界面更新
     *
     * @param scores 成绩信息列表
     */
    public void updateData(List<Score> scores) {
        data = scores;
        fireTableDataChanged();
    }

    /**
     * 设置表格数据（外部可调用此方法来直接更新表格展示的数据）
     *
     * @param scores 成绩信息列表
     */
    public void setData(List<Score> scores) {
        data = scores;
        fireTableDataChanged();
    }

    public Score getRowData(int rowIndex) {
        return data.get(rowIndex);
    }
}