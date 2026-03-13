import javax.swing.*;
public class Driverw 
{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Wordly");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new Startpanel()); 
        frame.setVisible(true);// Pass the JFrame here
    }
}
