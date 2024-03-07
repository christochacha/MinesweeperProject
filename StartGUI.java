import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGUI {

    public static Stop_watch stopwatch = new Stop_watch();
    public static void main(String[] args){

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update the stopwatch display
                GUIArray.frame.setTitle(GUIArray.updateLabel(GUIArray.bombsFlagged) + "     Time: " + Stop_watch.timeString);
            }
        });


        GUIArray.frame.getContentPane().removeAll();
        GUIArray.frame.repaint();
        //flag so the
        Main.bombClicked = true;
        GUIArray.frame.setSize(1000, 800);
        GUIArray.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 800));

        JPanel panel = new JPanel();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\awcha\\OneDrive\\Pictures\\Screenshots\\Start.jpg");
        JLabel background = new JLabel(imageIcon);
        panel.add(background);

        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("START");
        button.setFont(new Font("Arial", Font.BOLD, 64));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(new Color(30, 144, 255));
        button.setEnabled(true);
        button.setFocusable(false);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIArray.frame.getContentPane().removeAll();
                GUIArray.frame.repaint();
                GUIArray.numButtonsClicked = 0;
                GUIArray.bombsFlagged = 0;
                Main.main(null);

                stopwatch.timer.start();  // Start the stopwatch timer
                timer.start();  // Start the display update timer
                GUIArray.putStopWatch();
            }
        };


        button.addActionListener(actionListener);
        buttonPanel.add(button);

        panel.setBounds(0, 0, 1000, 800);
        buttonPanel.setBounds(400, 600, 230, 100); // Adjust the position and size as needed
        buttonPanel.setOpaque(false);
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);


        GUIArray.frame.add(layeredPane);

        GUIArray.frame.setVisible(true);
    }
}