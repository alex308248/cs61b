package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Game {
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        drawStartPicture();
        World world = null;

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                System.out.println(c);
                switch (c) {
                    case 'l':
                        world = loadFile();
                        System.out.println("LOADING");
                        break;
                    case 'n':
                        world = createWorld();
                        break;
                    case 'q':
                        System.exit(0);
                        return;
                    default:
                        world = createWorld();
                        break;
                }
                break;
            }
        }

        startGame(world);
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        World world = null;
        int num = 0;
        int seed = 0;
        int[] seedReturnArr;
        for (int i = 0; i < input.length(); i += 1) {
            char c = input.charAt(i);
            switch (c) {
                case 'l':
                    world = loadFile();
                    break;
                case 'L':
                    world = loadFile();
                    break;
                case 'n':
                    seedReturnArr = getInputSeed(input);
                    seed = seedReturnArr[0];
                    num  = seedReturnArr[1];
                    System.out.println(seed);
                    world = new World(seed, WIDTH, HEIGHT);
                    break;
                case 'N':
                    seedReturnArr = getInputSeed(input);
                    seed = seedReturnArr[0];
                    num  = seedReturnArr[1];
                    world = new World(seed, WIDTH, HEIGHT);
                    break;
                case 'q':
                    return null;
                case 'Q':
                    return null;
                default:
                    seedReturnArr = getInputSeed(input);
                    seed = seedReturnArr[0];
                    num  = seedReturnArr[1];
                    World w = new World(seed, WIDTH, HEIGHT);
                    break;
            }
            num = i + 1;
            break;
        }

        for (int i = num; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == 'q' || c == 'Q') {
                saveWorld(world);
                return world.getWorld();
            }
        }

        TETile[][] finalWorldFrame = world.getWorld();
        return finalWorldFrame;
    }

    private static void drawStartPicture() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 0.75, "CS61B THE GAME");
        StdDraw.text(0.5, 0.6, "(N)ew Game");
        StdDraw.text(0.5, 0.55, "(L)oad Game");
        StdDraw.text(0.5, 0.5, "(Q)uit");
        StdDraw.show();
    }

    public void drawFrame(String s) {
        //TOD0: Take the string and display it in the center of the screen
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, s);

        StdDraw.show();
    }

    private World loadFile() {
        File f = new File("./GAME.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                World loadWorld = (World) os.readObject();
                os.close();
                return loadWorld;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
        return createWorld();
    }

    private World createWorld() {
        StdDraw.text(0.5, 0.4, "Enter seed");
        StdDraw.show();
        int seed = getSeed();
        World w = new World(seed, WIDTH, HEIGHT);
        return w;
    }

    private int getSeed() {
        int seed = 0;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (c >= 48 && c <= 57) {
                    seed = seed * 10 + (c - 48);
                } else if (c == '\b') {
                    seed /= 10;
                } else if (c == 'S' || c == 's') {
                    break;
                } else if (c == 'q') {
                    System.exit(0);
                } else {
                    continue;
                }
                StdDraw.clear();
                drawStartPicture();
                StdDraw.text(0.5, 0.4, "Enter seed");
                StdDraw.text(0.5, 0.3, Integer.toString(seed));
                StdDraw.show();
            }
        }
        return seed;
    }

    private static void saveWorld(World w) {
        File f = new File("./GAME.ser");
        try {

            if (!f.exists()) {
                f.createNewFile();
            }

            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(w);
            os.close();
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private int[] getInputSeed(String input) {
        int[] ret = {0, 0};
        for (int i = 0; i < input.length(); i += 1) {
            char c = input.charAt(i);
            if (c >= 48 && c <= 57) {
                ret[0] = ret[0] * 10 + (c - 48);
            } else {
                continue;
            }
            ret[1] = i;
        }
        return ret;
    }

    private void startGame(World world){
        System.out.println("Game Start");
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        while (true) {
            //TODO: mouse
            /*
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                System.out.println(world.);
            }*/


            if (world.checkOutOfMaze()) {
                drawFrame("You Win!");
                while (!StdDraw.hasNextKeyTyped()) {
                }
                System.exit(0);
                break;
            } else if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if(c == 'q' || c == 'Q') {
                    saveWorld(world);
                    System.exit(0);
                    break;
                }
                world.characterMove(c);
            }
            world.draw(ter);
        }
    }
}
