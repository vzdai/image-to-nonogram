package ui;

import pixelart.ImageUtils;
import pixelart.PixelColor;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class NonogramTable extends JScrollPane {

    private JTable mTable;
    private BufferedImage mImage;
    private int mWidth;
    private int mHeight;
    private JList mRowList;
    private DefaultListModel<String> mListModel;
    private NonogramTableModel mTableModel;

    public NonogramTable(JTable table) {
        super(table);
        mTable = table;
        init();
    }

    private void init() {
        ListModel lm = new AbstractListModel() {
            String headers[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};

            public int getSize() {
                return headers.length;
            }

            public Object getElementAt(int index) {
                return headers[index];
            }
        };

//        mBoard.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//        mTable.setModel(new DefaultTableModel(2, 2));
        mTableModel = new NonogramTableModel();
        mListModel = new DefaultListModel<>();
        mListModel.addElement("a");
        mListModel.addElement("b");
        mRowList = new JList<>(mListModel);
        mRowList.setFixedCellWidth(20);
        mRowList.setCellRenderer(new RowRenderer(mTable));
//        mTable.setDefaultRenderer(String.class, new NonogramTableCellRenderer());
        setRowHeaderView(mRowList);
        mTable.setModel(mTableModel);

    }

    public void setImage(BufferedImage image) {
        mImage = image;

        if (image != null) {
            mWidth = image.getWidth();
            mHeight = image.getHeight();
        }

        updateTableModels(image);

        System.out.println("Set image");

        fillGrid();

        // TODO: fill in table from image
    }

    private void updateTableModels(BufferedImage image) {
        PixelColor[][] data = ImageUtils.toPixelColors(image);
        mTableModel.setData(data);

        mListModel.clear();
        mListModel.addElement("c");
        mListModel.addElement("d");
//        mTable.setColumnModel(new DefaultTableColumnModel() );

//        for (int i = 0; i < image.getWidth(); i++) {
//            mTable.getColumnModel().getColumn(0).setCellRenderer(new NonogramTableCellRenderer());
//        }

    }

    private void fillGrid() {

    }
}
