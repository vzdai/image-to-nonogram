package ui;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class NonogramTableRowRenderer extends JLabel implements ListCellRenderer {

    public NonogramTableRowRenderer(JTable table) {
        JTableHeader header = table.getTableHeader();
        setOpaque(true);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(CENTER);
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText((value == null) ? "" : value.toString());
        return this;
    }

    public void setSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }
}
