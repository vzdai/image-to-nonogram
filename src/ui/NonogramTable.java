package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.image.BufferedImage;

public class NonogramTable extends JScrollPane {

    private JTable mTable;
    private BufferedImage mImage;
    private int mWidth;
    private int mHeight;

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

        mTable.setModel(new DefaultTableModel(lm.getSize(), lm.getSize()));
//        mBoard.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JList rowHeader = new JList(lm);
        rowHeader.setFixedCellWidth(20);
        rowHeader.setCellRenderer(new RowRenderer(mTable));

        setRowHeaderView(rowHeader);
    }

    public void setImage(BufferedImage image) {
        mImage = image;

        if (image != null) {
            mWidth = image.getWidth();
            mHeight = image.getHeight();
        }
    }
}
