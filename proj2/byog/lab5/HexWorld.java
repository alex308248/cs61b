package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static int WIDTH;
    private static int HEIGHT;

    public static void addHexagon(int n, TETile[][] world, int X, int Y) {
        Random r = new Random();
        int random =  r.nextInt(5);
        //System.out.println(random);

        int x = n * 2 + (n-2), y = n * 2;
        for (int i = 0; i < n * 2; i += 1) {
            if (i < n) {
                makeAt((n - 1 - i) + X, (n * 2 - 2 + i) + X, i  + Y, world, random);
            }
            else {
                makeAt((i - n) + X, (n * 4 - 3 - i) + X, i  + Y, world, random);
            }
        }
    }

    private static void makeAt(int start, int end, int level, TETile[][] world, int random) {
        int width = world[0].length;
        for (int i = 0; i < width; i ++) {
            if (i >= start && i <= end) {
                System.out.println(random);
                switch (random) {
                    case 0:
                        world[i][level] = Tileset.SAND;
                        break;
                    case 1:
                        world[i][level] = Tileset.FLOWER;
                        break;
                    case 2:
                        world[i][level] = Tileset.GRASS;
                        break;
                    case 3:
                        world[i][level] = Tileset.MOUNTAIN;
                        break;
                    case 4:
                        world[i][level] = Tileset.TREE;
                        break;
                }
            }
        }
    }

    public static void buildCatan(int n, TETile[][] world) {
        for (int i = 0; i < 5; i ++) {
            int maxHeight;
            if (i == 0 || i == 4) {
                maxHeight = 3;
            } else if (i == 1 || i == 3) {
                maxHeight =4;
            } else {
                maxHeight = 5;
            }

            for (int j = 0; j < maxHeight; j++) {
                addHexagon(n, world,i * (n * 2  - 1) + 2,j * 2 * n + (5 -maxHeight) * n + 2);
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        WIDTH =  (3 * n - 2) * 3 + n * 2 + 4;
        HEIGHT = n * 10 + 4;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        buildCatan(n, world);
        ter.renderFrame(world);
    }
}
