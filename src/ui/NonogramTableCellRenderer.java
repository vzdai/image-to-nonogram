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

        if (value instanceof PixelColor) {
            PixelColor color = (PixelColor) value;
            c.setBackground(color.getColor());
        }

        return c;
    }
}
