package ui;

import pixelart.PixelArtConverter;

import javax.imageio.ImageIO;
import javax.jnlp.FileContents;
import javax.jnlp.FileOpenService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
    private NonogramTable mTable;

    public NonogramGenerator() {

        setDefaultSizes();
        setupTable();
        setupButtons();
        mStatus.setText("Select an image and click Generate");
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
        mTable = new NonogramTable(mBoard);

        if (mContentPane == null) {
            System.out.println("content pane null");
        }
        mContentPane.add(mTable, 0);

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
                System.out.println("Opened file: " + mFile.getName());
                mFileText.setText(mFile.getName());
            } else {
                System.out.println("Error opening file");
            }
        } else if (e.getSource() == mGenerateButton) {
            if (mFile == null) {
                mStatus.setText("Please select an image file first");
            } else if (Integer.parseInt(mWidthText.getText()) <= 0 || Integer.parseInt(mHeightText.getText()) <= 0) {
                mStatus.setText("Width and height should be > 0");
            } else {
                mStatus.setText("Generating...");
                generate();
            }
        }
    }

    private void generate() {
        try {
            BufferedImage image = new PixelArtConverter(mFile.getAbsolutePath())
                .width(Integer.parseInt(mWidthText.getText()))
                .height(Integer.parseInt(mHeightText.getText()))
                .convert();

            File outputfile = new File("saved.png");
            ImageIO.write(image, "png", outputfile);

            mTable.setImage(image);
        } catch (IOException e) {
            System.out.println("Error generating image: " + e);
        }
    }
}
