package thomas.bartel.chessPieces;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Test {

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridLayout(8, 8));
        int counter = 0;

        for (int i = 0; i < 64; i++) {
            if (i % 2 == 0) {
                JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("Images/KingWhite.png"))));
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                panel.add(label);
            } else {
                JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("Images/KingWhite.png"))));
                label.setOpaque(true);
                label.setBackground(Color.BLACK);
                panel.add(label);
            }
            
        }

        frame.add(panel);
        frame.setVisible(true);
        panel.setSize(500, 500);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
