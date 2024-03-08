import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stop_watch extends JFrame implements ActionListener {
    public static String timeString = "00:00";
    public Timer timer;
    public int elapsedTime;

    public Stop_watch() {
        // Set up the timer
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;
                updateTimeLabel();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        updateTimeLabel();
    }

    public void updateTimeLabel() {
        int minutes = (elapsedTime % 3600000) / 60000;
        int seconds = (elapsedTime % 60000) / 1000;
        timeString = String.format("%02d:%02d", minutes, seconds);
    }

    public void resetTime(Timer timer){
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;
                updateTimeLabel();
            }
        });
    }


}