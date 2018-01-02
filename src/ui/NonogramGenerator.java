package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NonogramGenerator {

    private static final int DEFAULT_BOARD_SIZE = 15;

    private JTable mBoard;
    private JPanel mContentPane;
    private JLabel mOptions;
    private JTextField mWidthText;
    private JTextField mHeightText;
    private JLabel mWidthLabel;
    private JLabel mHeightLabel;
    private JLabel mFileLabel;
    private JTextField mFileText;
    private JButton mSelectButton;
    private JButton mGenerateButton;
    private JButton mCheckButton;
    private JLabel mStatus;

    private DefaultTableModel mTableModel;

    public NonogramGenerator() {

        setDefaultSizes();
        setupTable();
    }

    private void setDefaultSizes() {
        mWidthText.setText(String.valueOf(DEFAULT_BOARD_SIZE));
        mHeightText.setText(String.valueOf(DEFAULT_BOARD_SIZE));
    }

    private void setupTable() {
        ListModel lm = new AbstractListModel() {
            String headers[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i" };

            public int getSize() {
                return headers.length;
            }

            public Object getElementAt(int index) {
                return headers[index];
            }
        };

        mBoard.setModel(new DefaultTableModel(lm.getSize(), lm.getSize()));
        mBoard.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JList rowHeader = new JList(lm);
        rowHeader.setFixedCellWidth(50);
        rowHeader.setCellRenderer(new RowRenderer(mBoard));

        JScrollPane scroll = new JScrollPane(mBoard);
        scroll.setRowHeaderView(rowHeader);
        if (mContentPane == null) {
            System.out.println("content pane null");
        }
        mContentPane.add(scroll, 0);

//        mBoard.setModel(new DefaultTableModel(5, 5));

//        for (int i = 0; i < mBoard.getRowCount(); i++) {
//            headerTable.setValueAt("Row " + (i + 1), i, 0);
//        }
    }


    public JPanel getContentPane() {
        return mContentPane;
    }
}
