package model;

import entity.Examinee;
import service.ExamineeService;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class ExamineeTableModel implements TableModel {

    String[] columnNames = new String[]{"考生编号", "考生姓名", "考生性别", "出生日期", "联系电话", "电子邮箱"};
    public static List<Examinee> es = new ExamineeService().list();

    @Override
    public int getRowCount() {
        return es.size();
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
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Examinee e = es.get(rowIndex);
        if (columnIndex == 0) {
            return e.getExamineeId();
        }
        if (columnIndex == 1) {
            return e.getName();
        }
        if (columnIndex == 2) {
            return e.getGender();
        }
        if (columnIndex == 3) {
            return e.getBirthDate();
        }
        if (columnIndex == 4) {
            return e.getContactNumber();
        }
        if (columnIndex == 5) {
            return e.getEmail();
        }

        return null;
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
}
