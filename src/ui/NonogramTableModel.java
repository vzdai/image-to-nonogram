package ui;

import pixelart.PixelColor;

import javax.swing.table.AbstractTableModel;

public class NonogramTableModel extends AbstractTableModel {

    private PixelColor[][] mData;

    public void setData(PixelColor[][] data) {
        mData = data;
        fireTableDataChanged();
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        System.out.println("getting row count");
        if (mData == null) {
            return 0;
        }
        return mData.length;
    }

    @Override
    public int getColumnCount() {
        System.out.println("getting column count");
        if (mData == null || mData.length == 0) {
            return 0;
        }
        return mData[0].length;
    }

    @Override
    public String getColumnName(int column) {
        System.out.println("getting column name: " + column);
        return String.valueOf(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return mData[rowIndex][columnIndex];
    }


}
