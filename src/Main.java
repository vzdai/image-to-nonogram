import ui.NonogramGenerator;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Nonogram Generator");
            frame.setContentPane(new NonogramGenerator().getContentPane());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
