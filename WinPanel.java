import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {
    private static final Color BACKGROUND = new Color(0, 3, 47);
    private static final Color TEXT = new Color(181, 199, 235);
    private final String answer;

    public WinPanel(JFrame frame, String answer) {
        this.answer = answer;
        JPanel pp = new JPanel();
        pp.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        setBackground(BACKGROUND);
        ImageIcon icon = new ImageIcon("congrats.jpeg");
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        JLabel congratsLabel = new JLabel("Congratulations!", SwingConstants.CENTER);
        congratsLabel.setFont(new Font("Clear Sans", Font.BOLD, 36));
        congratsLabel.setForeground(TEXT);
        pp.add(congratsLabel, BorderLayout.NORTH);
        pp.setBackground(BACKGROUND);
        pp.setOpaque(true);

        JLabel answerLabel = new JLabel("The answer was: " + answer, SwingConstants.CENTER);
        answerLabel.setFont(new Font("Clear Sans", Font.BOLD, 24));
        answerLabel.setForeground(TEXT);
        pp.add(answerLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(BACKGROUND);
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(BACKGROUND);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Clear Sans", Font.BOLD, 18));
        playAgainButton.setForeground(TEXT);
        playAgainButton.setBackground(BACKGROUND);
        playAgainButton.setBorderPainted(false);
        playAgainButton.setOpaque(true);
        playAgainButton.addActionListener(e -> {
            int state = PanelWa.getState();
            frame.getContentPane().removeAll();
            frame.add(state == 1 ? new PanelWa(1) : new PanelWa(2));
            frame.revalidate();
            frame.repaint();
        });
        buttonPanel.add(playAgainButton);

        JButton exitButton = new JButton("Exit to Home Screen");
        exitButton.setFont(new Font("Clear Sans", Font.BOLD, 18));
        exitButton.setForeground(TEXT);
        exitButton.setOpaque(true);
        exitButton.setBackground(BACKGROUND);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(new Startpanel());
            frame.revalidate();
            frame.repaint();
        });
        buttonPanel.add(exitButton);
        add(pp, BorderLayout.NORTH);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}