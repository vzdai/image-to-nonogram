package ui;

import javax.jnlp.FileContents;
import javax.jnlp.FileOpenService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class NonogramGenerator implements ActionListener {

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
    private File mFile;

    public NonogramGenerator() {

        setDefaultSizes();
        setupTable();
        setupButtons();
    }

    private void setDefaultSizes() {
        mWidthText.setText(String.valueOf(DEFAULT_BOARD_SIZE));
        mHeightText.setText(String.valueOf(DEFAULT_BOARD_SIZE));
    }

    private void setupButtons() {
        mSelectButton.addActionListener(this);
        mGenerateButton.addActionListener(this);
        mCheckButton.addActionListener(this);
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
        rowHeader.setFixedCellWidth(20);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mSelectButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int returnVal = fileChooser.showOpenDialog(mContentPane);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                mFile = fileChooser.getSelectedFile();
                //This is where a real application would open the file.
                    System.out.println("Opened file: " + mFile.getName());
                mFileText.setText(mFile.getName());
            } else {
                    System.out.println("Error opening file");
            }
        }
    }
}
