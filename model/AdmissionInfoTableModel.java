package model;

import entity.AdmissionInfo;
import service.AdmissionInfoService;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Date;
import java.util.List;

public class AdmissionInfoTableModel implements TableModel {

    String[] columnNames = new String[]{"录取信息编号", "考生编号", "是否被录取", "录取时间"};
    public static List<AdmissionInfo> ais = new AdmissionInfoService().list();

    @Override
    public int getRowCount() {
        return ais.size();
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
        if (columnIndex == 2) {
            return Boolean.class;
        }
        if (columnIndex == 3) {
            return Date.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AdmissionInfo ai = ais.get(rowIndex);
        if (columnIndex == 0) {
            return ai.getAdmissionId();
        }
        if (columnIndex == 1) {
            return ai.getExamineeId();
        }
        if (columnIndex == 2) {
            return ai.isAdmitted();
        }
        if (columnIndex == 3) {
            return ai.getAdmissionTime();
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
