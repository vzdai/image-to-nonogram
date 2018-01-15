package ui;

import pixelart.ImageUtils;
import pixelart.PixelColor;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class NonogramTable extends JScrollPane {

    private JTable mTable;
    private JList mRowList;
    private DefaultListModel<Hint> mListModel;
    private NonogramTableModel mTableModel;
    private NonogramTableRowRenderer mRowRenderer;

    public NonogramTable(JTable table) {
        super(table);
        mTable = table;
        init();
    }

    private void init() {
        mTableModel = new NonogramTableModel();
        mListModel = new DefaultListModel<>();
        mRowList = new JList<>(mListModel);
        mRowRenderer = new NonogramTableRowRenderer(mTable);
        mRowList.setCellRenderer(mRowRenderer);
        mTable.setDefaultRenderer(PixelColor.class, new NonogramTableCellRenderer());
        setRowHeaderView(mRowList);
        mTable.setModel(mTableModel);
        mTable.setFillsViewportHeight(true);

    }

    public void setImage(BufferedImage image) {
        PixelColor[][] data = ImageUtils.toPixelColors(image);
        mTableModel.setData(data);

        mListModel.clear();

        List<Hint> rowHints = mTableModel.getRowHints();
        for (Hint hint : rowHints) {
            mListModel.addElement(hint);
        }

        for (int i = 0; i < image.getWidth(); i++) {
            mTable.getColumnModel().getColumn(i).setCellRenderer(new NonogramTableCellRenderer());
        }
        mRowRenderer.setSize(50, mTable.getRowHeight());
    }

    public NonogramTableModel getTableModel() {
        return mTableModel;
    }

    private void updateTableModels(BufferedImage image) {


    }
}
