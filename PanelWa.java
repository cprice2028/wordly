import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PanelWa extends JPanel implements ActionListener {
    private static final Color BACKGROUND = new Color(0, 3, 47);
    private static final Color TEXT = new Color(181, 199, 235);
    private static final Color CORRECT = new Color(130, 200, 229);
    private static final Color MEH = new Color(127, 0, 255);
    private static final Color INCORRECT = new Color(0, 0, 0);
    private final JTextField[][] grid = new JTextField[6][5];
    private final String[] keys = {
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L",
            "Z", "Enter", "X", "C", "V", "B", "N", "M", "Delete"
    };
    private final JPanel gridPanel = new JPanel(new GridLayout(6, 5, 5, 5));
    private final JPanel keyboardPanel = new JPanel(new GridLayout(3, 9, 5, 5));
    private int row = 0;
    private int col = 0;
    private static int state = 0;
    private Wordguess word;

    public PanelWa(int arg) {
        state = arg;
        word = new Wordguess(state);
        setLayout(new BorderLayout(10, 10));
        setBackground(BACKGROUND);
        initGrid();
        initKeyboard();
        add(gridPanel, BorderLayout.NORTH);
        add(keyboardPanel, BorderLayout.CENTER);
        JButton exit = new JButton("Exit to Home Screen");
        exit.setOpaque(true);
        exit.setBackground(BACKGROUND);
        exit.setForeground(TEXT);
        exit.setBorderPainted(false);
        add(exit, BorderLayout.SOUTH);
        exit.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanelWa.this);
            frame.getContentPane().removeAll();
            frame.add(new Startpanel());
            frame.revalidate();
            frame.repaint();
        });
    }

    private void initGrid() {
        gridPanel.setOpaque(true);
        gridPanel.setBackground(BACKGROUND);
        Border LineBorder = BorderFactory.createLineBorder(TEXT);
        gridPanel.setBounds(100, 100, 500, 500);
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                JTextField tf = new JTextField();
                tf.setFont(new Font("Clear Sans", Font.BOLD, 24));
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setEditable(false);
                tf.setBorder(LineBorder);
                tf.setBackground(BACKGROUND);
                tf.setForeground(TEXT);
                tf.setOpaque(true);
                grid[r][c] = tf;
                gridPanel.add(tf);
            }
        }
    }

    private void initKeyboard() {
        for (String key : keys) {
            JButton button = new JButton(key);
            button.setFont(new Font("Clear Sans", Font.BOLD, 18));
            button.addActionListener(this);
            button.setBorderPainted(false);
            keyboardPanel.add(button);
            keyboardPanel.setBackground(TEXT);
            button.setOpaque(true);
            button.setBackground(BACKGROUND);
            button.setForeground(TEXT);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String key = e.getActionCommand();
        if (key.equals("Enter")) {
            if (col == 5) {
                StringBuilder guess = new StringBuilder();
                for (int i = 0; i < 5; i++) {
                    guess.append(grid[row][i].getText());
                }
                if (state == 1) {
                    if (checkWord(guess.toString().toUpperCase())) {
                        checkGuess(guess.toString().toUpperCase());
                    } else {
                        for (int j = 0; j < 5; j++) {
                            col--;
                            grid[row][col].setText("");
                        }
                    }
                } else {
                    checkGuess(guess.toString().toUpperCase());
                }
            }
        } else if (key.equals("Delete")) {
            if (col > 0) {
                col--;
                grid[row][col].setText("");
            }
        } else {
            if (col < 5) {
                grid[row][col].setText(key);
                col++;
            }
        }
    }

    private boolean checkWord(String guess) {
        String[] ar = word.getbigList();
        for (int a = 0; a < ar.length; a++) {
            if (ar[a].equals(guess)) {
                return true;
            }
        }
        return false;
    }

    private void checkGuess(String guess) {
        String answer = word.getAnswer();
        int[] answerLetterCount = new int[26];
        int[] guessLetterCount = new int[26];
        for (char c : answer.toCharArray()) {
            answerLetterCount[c - 'A']++;
        }
        for (int i = 0; i < 5; i++) {
            JTextField tf = grid[row][i];
            String letter = tf.getText();
            if (letter.equals(String.valueOf(answer.charAt(i)))) {
                tf.setBackground(CORRECT);
                tf.setForeground(Color.BLACK);
                tf.setOpaque(true);
                answerLetterCount[letter.charAt(0) - 'A']--;
                guessLetterCount[letter.charAt(0) - 'A']++;
                updateKeyboardButton(letter, CORRECT, Color.BLACK);
            }
        }
        for (int i = 0; i < 5; i++) {
            JTextField tf = grid[row][i];
            String letter = tf.getText();
            if (!tf.getBackground().equals(CORRECT)) {
                if (answer.contains(letter) && answerLetterCount[letter.charAt(0) - 'A'] > 0) {
                    tf.setBackground(MEH);
                    tf.setOpaque(true);
                    answerLetterCount[letter.charAt(0) - 'A']--;
                    guessLetterCount[letter.charAt(0) - 'A']++;
                    updateKeyboardButton(letter, MEH, Color.WHITE);
                } else {
                    tf.setBackground(INCORRECT);
                    tf.setOpaque(true);
                    updateKeyboardButton(letter, INCORRECT, Color.GRAY);
                }
            }
        }
        if (answer.equals(guess)) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanelWa.this);
            frame.getContentPane().removeAll();
            frame.add(new WinPanel(frame, answer));
            frame.revalidate();
            gameresult(true, answer);
            frame.repaint();
            return;
        }
        if ((row == 5) && (!answer.equals(guess))) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanelWa.this);
            frame.getContentPane().removeAll();
            frame.add(new LosePanel(frame, answer));
            frame.revalidate();
            gameresult(false, answer);
            frame.repaint();
            return;
        }
        row++;
        col = 0;
    }

    private void updateKeyboardButton(String letter, Color background, Color foreground) {
        for (Component component : keyboardPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equalsIgnoreCase(letter)) {
                    if (!button.getBackground().equals(CORRECT)) {
                        button.setBackground(background);
                        button.setForeground(foreground);
                        button.setOpaque(true);
                    }
                    break;
                }
            }
        }
    }

    public static int getState() {
        return state;
    }

    private void gameresult(boolean won, String answer) {
        String result = won ? "Won" : "Lost";
        String name = JOptionPane.showInputDialog("Enter your name:");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true))) {
            writer.write(name + " " + result + " - Answer: " + answer);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
