import javax.swing.*;
import java.util.Random;

public class Main {

    private static String[][] mineArr = new String[14][18];

    private static String[][] bombArray = new String[14][18];
    //flag so that when you press start in the GameOverGUI it doesn't make a ton of new GUI
    public static boolean bombClicked = false;
    public static String[][] finalArr = new String[14][18];
    public static void main(String[] args) {

        placeBombs();
        placeBombLabels();
        createFinalArr();
        GUIArray.main(null);
        //create GUI

    }

    public static void placeBombs(){
        //total bombs

        int totalBombs = 40;
        int bombsPlaced = 0;
        //mark all of the cells as 0's
        for (int i = 0; i < 14; i++) {
            for (int n = 0; n < 18; n++) {
                mineArr[i][n] = "0";
            }
        }
        //now, all of the cells should be marked with a zero
        //create a random instance variable
        Random random = new Random();
        //make sure the number of bombs placed doesn't go over 30
        //generate random numbers between 0 and 9 for columns and rows
        //check if that cell has an x
        //if it doesnt have an x, keep loop going
        //if it has an x, replace it with a 0
        while (bombsPlaced < totalBombs) {
            int row = random.nextInt(14);
            int column = random.nextInt(18);
            if (mineArr[row][column].equals("0")) {
                mineArr[row][column] = "x";
                bombsPlaced++;
            }
        }

    }
    public static void clickZero(){
        boolean zeroFound = false;
        Random random = new Random();
        while (!zeroFound){
            int row = random.nextInt(14);
            int col = random.nextInt(18);
            if (GUIArray.button[row][col].getText().equals("0")){
                GUIArray.button[row][col].doClick();
                zeroFound = true;
            }
        }
    }

    public static void placeBombLabels(){
        //now to detect how many bombs are surrounding each mine
        //create a new 2darray
        for (int i = 0; i < 14; i++) {
            for (int n = 0; n < 18; n++) {
                //specific cell bomb label
                int bombLabel = 0;
                //if there is not a bomb at the cell
                if (mineArr[i][n].equals("0")) {
                    //check up left
                    if (isValid(i - 1, n - 1) && mineArr[i - 1][n - 1].equals("x")) {
                        bombLabel++;
                    }
                    //check up
                    if (isValid(i - 1, n) && mineArr[i - 1][n].equals("x")) {
                        bombLabel++;
                    }
                    //check up right
                    if (isValid(i - 1, n + 1) && mineArr[i - 1][n + 1].equals("x")) {
                        bombLabel++;
                    }
                    //check left
                    if (isValid(i, n - 1) && mineArr[i][n - 1].equals("x")) {
                        bombLabel++;
                    }
                    //check right
                    if (isValid(i, n + 1) && mineArr[i][n + 1].equals("x")) {
                        bombLabel++;
                    }
                    //check down left
                    if (isValid(i + 1, n - 1) && mineArr[i + 1][n - 1].equals("x")) {
                        bombLabel++;
                    }
                    //check down
                    if (isValid(i + 1, n) && mineArr[i + 1][n].equals("x")) {
                        bombLabel++;
                    }
                    //check down right
                    if (isValid(i + 1, n + 1) && mineArr[i + 1][n + 1].equals("x")) {
                        bombLabel++;
                    }
                }
                bombArray[i][n] = Integer.toString(bombLabel);
            }
        }


    }

    public static void createFinalArr(){

        for (int i = 0; i < 14; i++){
            for (int n = 0; n < 18; n++){
                if (mineArr[i][n] == ("x")){
                    finalArr[i][n] = "x";
                }

                else{
                    finalArr[i][n] = bombArray[i][n];
                }
            }
        }
        




        //for testing
        for (int i = 0; i < 14; i++){
            for (int n = 0; n < 18; n++){
                System.out.print(finalArr[i][n] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void resetGame(){


        GUIArray.frame.getContentPane().removeAll();
        GUIArray.frame.repaint();

        //set finalArr to zero
        for (int i = 0; i < 14; i++){
            for (int n = 0; n < 18; n++){
                finalArr[i][n] = "0";
            }
        }





        //place the bombs
        placeBombs();
        //place the bomb labels
        placeBombLabels();
        //create the final array
        createFinalArr();
        //call back to the GUIArray
        GUIArray.main(null);
    }


    //check to make sure that the cell the minesweeper is checking is within the array
    public static boolean isValid(int row, int column) {
        return row >= 0 && row < 14 && column >= 0 && column < 18;
    }
}