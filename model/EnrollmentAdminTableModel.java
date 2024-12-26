package model;

import entity.EnrollmentAdmin;
import service.EnrollmentAdminService;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class EnrollmentAdminTableModel implements TableModel {

    String[] columnNames = new String[]{"招生管理员编号", "招生管理员姓名", "负责区域"};
    public static List<EnrollmentAdmin> eas = new EnrollmentAdminService().list();

    @Override
    public int getRowCount() {
        return eas.size();
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
        EnrollmentAdmin ea = eas.get(rowIndex);
        if (columnIndex == 0) {
            return ea.getEnrollmentAdminId();
        }
        if (columnIndex == 1) {
            return ea.getAdminRealName();
        }
        if (columnIndex == 2) {
            return ea.getResponsibleArea();
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
