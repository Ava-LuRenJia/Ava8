package model;

import entity.SystemAdmin;
import service.SystemAdminService;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class SystemAdminTableModel implements TableModel {

    String[] columnNames = new String[]{"系统管理员编号", "系统管理员姓名", "所属部门"};
    public static List<SystemAdmin> sas = new SystemAdminService().list();

    @Override
    public int getRowCount() {
        return sas.size();
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
        SystemAdmin sa = sas.get(rowIndex);
        if (columnIndex == 0) {
            return sa.getAdminId();
        }
        if (columnIndex == 1) {
            return sa.getAdminName();
        }
        if (columnIndex == 2) {
            return sa.getDepartment();
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