

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIArray {

    public static JFrame frame = new JFrame();
    public static JButton[][] button = new JButton[14][18];

    public static int bombsFlagged;

    public static int numButtonsClicked;


    //makes sure buttons aren't reclicked when finding zeroes


    public static void main(String[] args) {

        String[][] guiFinalArr = new String[14][18];
        boolean[][] buttonClicked = new boolean[14][18];
        boolean[][] buttonRightClicked = new boolean[14][18];





        //sets all of the button flags to false
        for (int j = 0; j < 14; j++){
            for (int k = 0; k < 18; k++){
                buttonClicked[j][k] = false;
            }
        }
        for (int j = 0; j < 14; j++){
            for (int k = 0; k < 18; k++){
                buttonRightClicked[j][k] = false;
            }
        }
        numButtonsClicked = 0;
        bombsFlagged = 0;
        putStopWatch();
        //color for when the button has been clicked
        Color color2 = new Color(153, 102, 0);
        Color color3 = new Color(102, 51, 0);

        JPanel panel = new JPanel(new GridLayout(14, 18));

        for (int i = 0; i < 14; i++){
            for (int n = 0; n < 18; n++){
                guiFinalArr[i][n] = Main.finalArr[i][n];
            }
        }

        //creates the button for each corresponding finalArr cell
        for (int i = 0; i < 14; i++) {
            for (int n = 0; n < 18; n++) {
                //sets the values of row and col so that they can't be changed in the making of each button
                final int row = i;
                final int col = n;
                button[i][n] = new JButton(guiFinalArr[i][n]);
                button[i][n].setFont(new Font("Arial", Font. PLAIN, 32));

                //makes specialized event listeners for each button
                button[i][n].putClientProperty("row", i);
                button[i][n].putClientProperty("col", n);
                //makes it so you can't highlight a button
                button[i][n].setFocusable(false);
                button[i][n].setFocusPainted(false);
                panel.add(button[i][n]);

                //color setting
                Color buttonColor;
                if ((i + n) % 2 == 0){
                    buttonColor = Color.GREEN.darker();
                    button[i][n].setBackground(buttonColor);
                }
                else{
                    buttonColor = Color.GREEN.darker().darker();
                    button[i][n].setBackground(buttonColor);
                }
                button[i][n].setForeground(button[i][n].getBackground());

                //checking if a right click
                button[i][n].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //if a cell is clicked it is marked as red
                        if (SwingUtilities.isRightMouseButton(e)){
                            if (!buttonClicked[row][col] && !buttonRightClicked[row][col]){
                                buttonRightClicked[row][col] = true;
                                button[row][col].setBackground(Color.RED.brighter());
                                button[row][col].setForeground(Color.RED.brighter());
                                ++bombsFlagged;
                                updateLabel(bombsFlagged);
                                gameWon(buttonClicked);
                            }

                        }
                    }
                });

                //left click
                ActionListener listener = e -> {

                    //gets the exact row and column of the button clicked
                    int rowIndex = (int) ((JButton) e.getSource()).getClientProperty("row");
                    int colIndex = (int) ((JButton) e.getSource()).getClientProperty("col");

                    //flags it as true
                    buttonClicked[row][col] = true;

                    //increments the number of buttons clicked and checks if the user won
                    numButtonsClicked++;
                    gameWon(buttonClicked);


                    //decides what to do when each button is clicked
                    switch (guiFinalArr[row][col]){
                        case ("x"):
                            //sets a flag so the GameOverGui is only called once
                            GameOverGUI.main(null);
                            break;

                        //if it's 0, make the background and text the same color
                            //call to find zero with the button 2d array, and the button index as parameters
                        case ("0"):
                            if (buttonRightClicked[row][col]){updateLabel(--bombsFlagged); }
                            button[row][col].setForeground(button[row][col].getBackground());
                            buttonClicked[row][col] = true;
                            button[row][col].setText("");
                            findZero(button, buttonClicked, guiFinalArr, rowIndex, colIndex);
                            break;
                        case ("1"):
                            if (buttonRightClicked[row][col]){updateLabel(--bombsFlagged); }

                            //set the text to bright red and flag
                            button[row][col].setForeground(new Color(220, 20, 60));
                            buttonClicked[row][col] = true;
                            break;
                        case ("2"):
                            if (buttonRightClicked[row][col]){updateLabel(--bombsFlagged); }

                            //set the text to cyan and flag
                            button[row][col].setForeground(Color.CYAN);
                            buttonClicked[row][col] = true;
                            break;
                        case ("3"):
                            if (buttonRightClicked[row][col]){updateLabel(--bombsFlagged); }

                            //set the text to blue and flag
                            button[row][col].setForeground(new Color(30, 144, 255));
                            buttonClicked[row][col] = true;
                            break;
                        case ("4"):
                            if (buttonRightClicked[row][col]){updateLabel(--bombsFlagged); }

                            //set the text to magenta and flag
                            button[row][col].setForeground(Color.MAGENTA);
                            buttonClicked[row][col] = true;
                            break;
                        case ("5"):
                            if (buttonRightClicked[row][col]){updateLabel(--bombsFlagged); }

                            //set the text to orange and flag
                            button[row][col].setForeground(Color.ORANGE);
                            buttonClicked[row][col] = true;
                            break;
                        case ("6"):
                            if (buttonRightClicked[row][col]){updateLabel(--bombsFlagged); }

                            //set the text to pink and flag
                            button[row][col].setForeground(Color.PINK);
                            buttonClicked[row][col] = true;
                            break;
                        case ("7"):
                            if (buttonRightClicked[row][col]){updateLabel(--bombsFlagged); }

                            //set the text to dark yellow and flag
                            button[row][col].setForeground(Color.YELLOW.darker().darker());
                            buttonClicked[row][col] = true;
                            break;
                    }

                    //if the color was dark dark green, the color is set to dark brown,
                    // if it is set to dark green then it is set to brown
                    if (button[row][col].getBackground().equals(Color.GREEN.darker())){
                        button[row][col].setBackground(color2);
                    }                           //this is to change the background color once it has been clicked
                    else {
                        button[row][col].setBackground(color3);
                    }

                };
                //add the specialized action listener to each button
                button[i][n].addActionListener(listener);
            }
        }
        frame.setTitle("Mines left to sweep: 40");
        frame.add(panel);
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setVisible(false);
        Main.clickZero();
        panel.setVisible(true);
    }

    public static String updateLabel(int bombsFlagged){
        return("Mines left to sweep: " + (40 - bombsFlagged));
    }

    public static void putStopWatch(){
        Stop_watch stopWatch = new Stop_watch();
        frame.setTitle(updateLabel(bombsFlagged) + "     Time: " + stopWatch.timeString);
    }




    public static void findZero(JButton[][] buttons, @org.jetbrains.annotations.NotNull boolean[][] buttonClicked, String[][] guiFinalArr, int row, int col){

        if (buttonClicked[row][col]){
            Runnable clickTask = () -> {
                buttons[row][col].doClick();
                buttons[row][col].setEnabled(false);
            };

            SwingUtilities.invokeLater(clickTask);
            //check up left
            if (isValid(row - 1, col - 1) && !buttonClicked[row - 1][col - 1]){
                if (guiFinalArr[row - 1][col - 1] == "0"){
                    button[row][col].setForeground(button[row][col].getBackground());
                    buttonClicked[row][col] = true;
                    button[row][col].setText("");
                    findZero(button, buttonClicked, guiFinalArr, row - 1, col - 1);
                }
                else {
                        buttons[row - 1][col - 1].doClick();
                        buttons[row][col].setEnabled(false);

                }
            }
            //check up
            if (isValid(row - 1, col) && !buttonClicked[row -1][col]){
                if (guiFinalArr[row - 1][col] == "0"){
                    button[row][col].setForeground(button[row][col].getBackground());
                    buttonClicked[row][col] = true;
                    button[row][col].setText("");
                    findZero(button, buttonClicked, guiFinalArr, row - 1, col);
                }
                else {
                        buttons[row - 1][col].doClick();
                        buttons[row][col].setEnabled(false);

                }
            }
            //check up right
            if (isValid(row - 1, col + 1) && !buttonClicked[row - 1][col + 1]){
                if (guiFinalArr[row - 1][col + 1] == "0"){
                    button[row][col].setForeground(button[row][col].getBackground());
                    buttonClicked[row][col] = true;
                    button[row][col].setText("");
                    findZero(button, buttonClicked, guiFinalArr, row -1, col + 1);
                }
                else {
                        buttons[row - 1][col + 1].doClick();
                        buttons[row][col].setEnabled(false);

                }
            }
            //check left
            if (isValid(row, col - 1) && !buttonClicked[row][col - 1]){
                if (guiFinalArr[row][col - 1] == "0"){
                    button[row][col].setForeground(button[row][col].getBackground());
                    buttonClicked[row][col] = true;
                    button[row][col].setText("");
                    findZero(button, buttonClicked, guiFinalArr, row, col -1);
                }
                else {
                        buttons[row][col - 1].doClick();
                        buttons[row][col].setEnabled(false);


                }
            }
            //check right
            if (isValid(row, col + 1) && !buttonClicked[row][col + 1]){
                if (guiFinalArr[row][col + 1] == "0"){
                    button[row][col].setForeground(button[row][col].getBackground());
                    buttonClicked[row][col] = true;
                    button[row][col].setText("");
                    findZero(button, buttonClicked, guiFinalArr, row, col + 1);
                }
                else {
                        buttons[row][col + 1].doClick();
                        buttons[row][col].setEnabled(false);


                }
            }
            //check down left
            if (isValid(row + 1, col - 1) && !buttonClicked[row + 1][col - 1]){
                if (guiFinalArr[row + 1][col - 1] == "0"){
                    button[row][col].setForeground(button[row][col].getBackground());
                    buttonClicked[row][col] = true;
                    button[row][col].setText("");
                    findZero(button, buttonClicked, guiFinalArr, row + 1, col - 1);
                }
                else {
                        buttons[row + 1][col - 1].doClick();
                        buttons[row][col].setEnabled(false);


                }
            }
            //check down
            if (isValid(row + 1, col) && !buttonClicked[row + 1][col]){
                if (guiFinalArr[row + 1][col] == "0"){
                    button[row][col].setForeground(button[row][col].getBackground());
                    buttonClicked[row][col] = true;
                    button[row][col].setText("");
                    findZero(button, buttonClicked, guiFinalArr, row + 1, col);
                }
                else {
                        buttons[row + 1][col].doClick();
                        buttons[row][col].setEnabled(false);


                }
            }
            //check down right
            if (isValid(row + 1, col + 1) && !buttonClicked[row + 1][col + 1]){
                if (guiFinalArr[row + 1][col + 1] == "0"){
                    button[row][col].setForeground(button[row][col].getBackground());
                    buttonClicked[row][col] = true;
                    button[row][col].setText("");
                    findZero(button, buttonClicked, guiFinalArr, row + 1, col + 1);
                }
                else {
                        buttons[row + 1][col + 1].doClick();
                        buttons[row][col].setEnabled(false);


                }
            }
        }

    }

    public static void gameWon(boolean[][] buttonClicked){
        int cellsClicked = 0;
        //if all of the non mines have been clicked
        for (int i = 0; i < 14; i++){
            for (int n = 0; n < 18; n++){
                if (buttonClicked[i][n]){
                    cellsClicked++;
                }
                if (cellsClicked == 212 && bombsFlagged == 40){
                    System.out.println("game won");
                    StartGUI.stopwatch.timer.stop();
                    GameWonGUI.main(null);
                }
            }
        }
    }
    public static boolean isValid(int row, int column) {
        //makes sure the given cell is in the boundaries
        return row >= 0 && row < 14 && column >= 0 && column < 18;
    }

}