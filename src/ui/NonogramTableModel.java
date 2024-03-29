package ui;

import pixelart.PixelColor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class NonogramTableModel extends AbstractTableModel {

    private PixelColor[][] mData;
    private List<Hint> mRowHints;
    private List<Hint> mColumnHints;

    public void setData(PixelColor[][] data) {
        mData = data;
        initRowHints();
        initColumnHints();
        fireTableDataChanged();
        fireTableStructureChanged();
    }

    private void initRowHints() {
        mRowHints = new ArrayList<>();
        for (PixelColor[] row : mData) {
            int numFilled = 0;
            Hint hint = new Hint();
            for (int j = 0; j < row.length; j++) {
                PixelColor color = row[j];
                if (!color.isSquareFilled()) {
                    if (numFilled != 0) {
                        hint.addValue(numFilled);
                        numFilled = 0;
                    }
                } else {
                    numFilled++;
                }

                if (j == row.length - 1 && numFilled > 0) {
                    hint.addValue(numFilled);
                }
            }
            mRowHints.add(hint);
        }
    }

    public List<Hint> getRowHints() {
        return mRowHints;
    }

    public List<Hint> getColumnHints() {
        return mColumnHints;
    }

    private void initColumnHints() {
        mColumnHints = new ArrayList<>();

        for (int i = 0; i < mData[0].length; i++) {
            int numFilled = 0;
            Hint hint = new Hint();
            for (int j = 0; j < mData.length; j++) {
                PixelColor color = mData[j][i];
                if (!color.isSquareFilled()) {
                    if (numFilled != 0) {
                        hint.addValue(numFilled);
                        numFilled = 0;
                    }
                } else {
                    numFilled++;
                }

                if (j == mData.length - 1 && numFilled > 0) {
                    hint.addValue(numFilled);
                }
            }
            mColumnHints.add(hint);
        }
    }

    @Override
    public int getRowCount() {
        if (mData == null) {
            return 0;
        }
        return mData.length;
    }

    @Override
    public int getColumnCount() {
        if (mData == null || mData.length == 0) {
            return 0;
        }
        return mData[0].length;
    }

    @Override
    public String getColumnName(int column) {
        return mColumnHints.get(column).toString();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return mData[rowIndex][columnIndex];
    }


}
