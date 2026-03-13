import javax.swing.*;
import java.awt.*;

public class Startpanel extends JPanel {
    private static final Color BACKGROUND = new Color(0, 3, 47);
    private static final Color TEXT = new Color(181, 199, 235);

    public Startpanel() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND);

        JPanel backgroundLabel = new JPanel();
        backgroundLabel.setBackground(BACKGROUND);
        backgroundLabel.setLayout(new GridLayout(3, 1, 10, 10));

        JPanel slime = new JPanel();
        slime.setBackground(BACKGROUND);
        slime.setLayout(new GridLayout(2, 1, 10, 10));

        ImageIcon icon = new ImageIcon("costume1.png");
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel sigma = new JLabel("Made by Charles Price and Raiyan Saaim.");
        sigma.setFont(new Font("Clear Sans", Font.ITALIC, 20));
        sigma.setForeground(TEXT);
        sigma.setHorizontalAlignment(SwingConstants.CENTER);

        JButton startButton = new JButton("Start");
        startButton.setSize(400, 100);
        startButton.setFont(new Font("Clear Sans", Font.BOLD, 24));
        startButton.setBackground(BACKGROUND);
        startButton.setForeground(TEXT);
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);
        startButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new PanelWa(1));
            frame.revalidate();
            frame.repaint();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 24));
        exitButton.setSize(400, 100);
        exitButton.setBackground(BACKGROUND);
        exitButton.setForeground(TEXT);
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(e -> System.exit(0));

        JButton randomButton = new JButton("Random");
        randomButton.setFont(new Font("Arial", Font.BOLD, 24));
        randomButton.setSize(400, 100);
        randomButton.setBackground(BACKGROUND);
        randomButton.setForeground(TEXT);
        randomButton.setBorderPainted(false);
        randomButton.setOpaque(true);
        randomButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new PanelWa(2));
            frame.revalidate();
            frame.repaint();
        });

        backgroundLabel.add(startButton);
        backgroundLabel.add(randomButton);
        backgroundLabel.add(exitButton);
        slime.add(imageLabel);
        slime.add(sigma);
        add(slime, BorderLayout.CENTER);
        add(backgroundLabel, BorderLayout.SOUTH);
    }

}
