package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        StdDraw.setPenColor(StdDraw.WHITE);
        //TODO: Initialize random number generator
        rand = new Random();
    }

    public String generateRandomString(int n) {
        //TOD0: Generate random string of letters of length n
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i += 1) {
            int num = rand.nextInt(26) + 97;
            stringBuilder.append((char) num);
        }

        return stringBuilder.toString();
    }

    public void drawFrame(String s, int round) {
        //TOD0: Take the string and display it in the center of the screen
        System.out.println(s);
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.enableDoubleBuffering();
        StdDraw.text(width/2, height/2, s);


        //TODO: If game is not over, display relevant game information at the top of the screen
        if (round != 0) {
            Font smallFont = new Font("Monaco", Font.BOLD, 17);
            StdDraw.setFont(smallFont);
            StdDraw.text(2, this.height - 1, "Round:" + round);
            StdDraw.text(this.width/2, this.height - 1, "Watch!");
            StdDraw.text(this.width-3, this.height - 1, "Excellent!");
            StdDraw.line(0, this.height-1.5, this.width, this.height-1.5);
        }
        StdDraw.show();
    }

    public void flashSequence(String letters){
        //TOD0: Display each character in letters, making sure to blank the screen between letters

        for (int i = 0; i < letters.length(); i += 1) {
            drawFrame(String.valueOf(letters.charAt(i)), letters.length());
            try {
                Thread.sleep( 1000 );
            } catch (Exception e){
                System.exit( 0 );
            }

            drawFrame("", letters.length());
            try {
                Thread.sleep( 500 );
            } catch (Exception e){
                System.exit( 0 );
            }
        }
    }

    public String solicitNCharsInput(int n) {
        //TOD0: Read n letters of player input
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                stringBuilder.append(StdDraw.nextKeyTyped());
            }
            if (stringBuilder.length() == n) {
                break;
            }
        }

        return stringBuilder.toString();
    }

    public void startGame(){
        //TODO: Set any relevant variables before the game starts
        int round = 1;
        String question, answer;
        //TODO: Establish Game loop
        while (true) {
            String rd = "Round:" + round;
            drawFrame(rd, 0);
            try {
                Thread.sleep( 1000 );
            } catch (Exception e){
                System.exit( 0 );
            }

            question = generateRandomString(round);
            flashSequence(question);
            answer = solicitNCharsInput(round);
            if (question.equals(answer)) {
                int num = rand.nextInt(7);
                drawFrame(ENCOURAGEMENT[num], 0);
                try {
                    Thread.sleep( 1000 );
                } catch (Exception e){
                    System.exit( 0 );
                }
                round += 1;
            } else {
                drawFrame("Game Over! You made it to round:" + round, 0);
                break;
            }
        }
    }

}
