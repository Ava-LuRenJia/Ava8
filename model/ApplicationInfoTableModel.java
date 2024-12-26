package model;

import entity.ApplicationInfo;
import service.ApplicationInfoService;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Date;
import java.util.List;

public class ApplicationInfoTableModel implements TableModel {

    String[] columnNames = new String[]{"报考信息编号", "考生编号", "报考时间", "考试类型"};
    public static List<ApplicationInfo> ais = new ApplicationInfoService().list();

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
        ApplicationInfo ai = ais.get(rowIndex);
        if (columnIndex == 0) {
            return ai.getApplicationId();
        }
        if (columnIndex == 1) {
            return ai.getExamineeId();
        }
        if (columnIndex == 2) {
            return ai.getApplicationTime();
        }
        if (columnIndex == 3) {
            return ai.getExamType();
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

    public void fireTableDataChanged() {
    }
}
