package koles.Private.liebrelibdesktop;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CheckboxedTableModel extends AbstractTableModel {

    Object[][] data;
    String[] headers;

    public CheckboxedTableModel(Object[][] newData, String[] newHeaders)
    {
        super();
        data = newData;
        headers = newHeaders;
    }

    public CheckboxedTableModel()
    {
        data = new Object[][]{ { "1", Boolean.TRUE }, { "2", Boolean.TRUE }, { "3", Boolean.FALSE },
                { "4", Boolean.TRUE }, { "5", Boolean.FALSE }, };

        headers = new String[]{"Les Requéte","selectionné"};
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    public Class getColumnClass(int column) {
        return (getValueAt(0, column).getClass());
    }

    public void setValueAt(Object value, int row, int column) {
        data[row][column] = value;
    }

    public boolean isCellEditable(int row, int column) {
        return (column != 0);
    }
}
