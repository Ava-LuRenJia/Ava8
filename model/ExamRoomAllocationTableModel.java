package model;

import entity.ExamRoomAllocation;
import service.ExamRoomAllocationService;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class ExamRoomAllocationTableModel implements TableModel {

    String[] columnNames = new String[]{"考场分配编号", "考生编号", "考场编号", "座位号"};
    public static List<ExamRoomAllocation> eras = new ExamRoomAllocationService().list();

    @Override
    public int getRowCount() {
        return eras.size();
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
        ExamRoomAllocation era = eras.get(rowIndex);
        if (columnIndex == 0) {
            return era.getAllocationId();
        }
        if (columnIndex == 1) {
            return era.getExamineeId();
        }
        if (columnIndex == 2) {
            return era.getExamRoomNumber();
        }
        if (columnIndex == 3) {
            return era.getSeatNumber();
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
