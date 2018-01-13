package ui;

import pixelart.PixelColor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class NonogramTableCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
        Component c = super.getTableCellRendererComponent(
             table, value, isSelected, hasFocus, row, column);

//        System.out.println("Object: " + value);
        if (value instanceof PixelColor) {
            PixelColor color = (PixelColor) value;
//            c.setForeground(color.getColor());
            c.setBackground(color.getColor());
//            System.out.println("row: " + row + ", column: " + column + ", color " + color.getColor());
        }

        // Only for specific cell
//        if (row == SPECIAL_ROW && column == SPECIAL_COULMN) {
//            c.setFont(/* special font*/);
//            // you may want to address isSelected here too
//            c.setForeground(/*special foreground color*/);
//            c.setBackground(/*special background color*/);
//        }
        return c;
    }
}
