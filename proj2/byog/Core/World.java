package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World implements Serializable {
    private List<Rectangle> rectangles;
    private Random r;
    private static int WIDTH;
    private static int HEIGHT;
    private TETile[][] world;
    private static final long serialVersionUID = 123123123123123L;
    private int[] character = {0, 0};
    private int[] door = {0, 0};
    private int lightSize = 2;

    public World(Integer seed, Integer w, Integer h) {
        WIDTH = w;
        HEIGHT = h;
        world = new TETile[WIDTH][HEIGHT];
        r = new Random(seed);
        rectangles = new ArrayList<Rectangle>();

        int roomNum = RandomUtils.uniform(r, 30, 50);

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        for (int i = 0; i < roomNum; i += 1) {
            addRandomRoom();
        }
        addHallways();
        addWall();
        addDoor();
        addCharacter();
    }

    public boolean checkOutOfMaze() {
        if (character[0] == door[0] && character[1] == door[1]) {
            return true;
        }
        return false;
    }

    private void addCharacter() {
        int x = RandomUtils.uniform(r, 0, WIDTH - 1);
        int y = RandomUtils.uniform(r, 0, HEIGHT - 1);

        if (world[x][y] == Tileset.WOOD) {
            world[x][y] = Tileset.HERO;
            character[0] = x;
            character[1] = y;
            return;
        }
        addCharacter();
    }

    private void addRandomRoom() {
        int width = RandomUtils.uniform(r, 3, 6);
        int height = RandomUtils.uniform(r, 3, 6);
        int posX = RandomUtils.uniform(r, 4, WIDTH - width);
        int posY = RandomUtils.uniform(r, 4, HEIGHT - height);
        Rectangle newRectangle = new Rectangle(width, height, posX, posY);

        //If the position is overlapped, build a new rectangle
        if (checkPosition(newRectangle)
                || posX + width >= WIDTH - 4 || posY + height >= HEIGHT - 4) {
            addRandomRoom();
            return;
        }

        for (int i = posX; i < posX + width; i += 1) {
            for (int j = posY; j < posY + height; j += 1) {
                world[i][j] = Tileset.WOOD;
            }
        }
        //System.out.println(width + " " +height + " " +posX + " " +posY);
        rectangles.add(newRectangle);
    }

    private void addHallways() {
        for (int i = 0; i < rectangles.size() - 1; i++) {
            connectTwoRectangle(rectangles.get(i), rectangles.get(i + 1));
        }
        if (rectangles.size() > 0) {
            connectTwoRectangle(rectangles.get(rectangles.size() - 1), rectangles.get(0));
        }
    }

    private void addWall() {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                if (world[x][y] == Tileset.NOTHING) {
                    if (x != WIDTH - 1 && world[x + 1][y] == Tileset.WOOD) {
                        world[x][y] = Tileset.ROCK;
                    } else if (x != 0 && world[x - 1][y] == Tileset.WOOD) {
                        world[x][y] = Tileset.ROCK;
                    } else if (y != HEIGHT - 1 && world[x][y + 1] == Tileset.WOOD) {
                        world[x][y] = Tileset.ROCK;
                    } else if (y != 0 && world[x][y - 1] == Tileset.WOOD) {
                        world[x][y] = Tileset.ROCK;
                    }
                }
            }
        }
    }

    private void addDoor() {
        int x = RandomUtils.uniform(r, 0, WIDTH);
        int y = 0;
        while (y < HEIGHT && world[x][y] == Tileset.NOTHING) {
            y += 1;
        }
        if (y == HEIGHT) {
            addDoor();
            return;
        }
        world[x][y] = Tileset.LOCKED_DOOR;
        door[0] = x;
        door[1] = y;
    }

    private boolean checkPosition(Rectangle rec) {
        for (int i = 0; i < rectangles.size(); i += 1) {
            if (rec.checkOverlap(rectangles.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void draw(TERenderer ter) {
        //ter.renderFrame(world);
        ter.renderFrameLight(world, lightSize, character[0], character[1]);
    }

    public TETile[][] getWorld() {
        return world;
    }

    private void connectTwoRectangle(Rectangle r1, Rectangle r2) {
        int x1 = r1.getPosX();
        int y1 = r1.getPosY();
        int x2 = r2.getPosX();
        int y2 = r2.getPosY();
        int t;
        //System.out.println("x1:"+x1 + " y1:"+ y1+ " x2:"+ x2 + " y2:"+ y2);

        if (x1 > x2) {
            for (int i = x2; i < x1; i += 1) {
                world[i][y1] =  Tileset.WOOD;
            }
        } else {
            for (int i = x1; i < x2; i += 1) {
                world[i][y1] =  Tileset.WOOD;
            }
        }
        if (y1 > y2) {
            for (int i = y2; i < y1; i += 1) {
                world[x2][i] =  Tileset.WOOD;
            }
        } else {
            for (int i = y1; i < y2; i += 1) {
                world[x2][i] =  Tileset.WOOD;
            }
        }
    }

    private void connectTwoRectangle2(Rectangle r1, Rectangle r2) {
        int x1 = r1.getPosX();
        int y1 = r1.getPosY();
        int x2 = r2.getPosX();
        int y2 = r2.getPosY();
        int checkPositiveX;
        int checkPositiveY;
        //System.out.println("x1:"+x1 + " y1:"+ y1+ " x2:"+ x2 + " y2:"+ y2);

        if (x1 < x2) {
            checkPositiveX = 1;
        } else {
            checkPositiveX = -1;
        }

        if (y1 < y2) {
            checkPositiveY = 1;
        } else {
            checkPositiveY = -1;
        }

        boolean exchange = true;
        while (x1 != x2 && y1 != y2) {
            if (exchange) {
                x1 += checkPositiveX;
                world[x1][y1] = Tileset.WOOD;
                exchange = false;
            } else {
                y1 += checkPositiveY;
                world[x1][y1] = Tileset.WOOD;
                exchange = true;
            }
        }
        if (x1 == x2) {
            for (int i = y1; i != y2; i += checkPositiveY) {
                world[x1][i] = Tileset.WOOD;
            }
            return;
        } else if (y1 == y2) {
            for (int i = x1; i != x2; i += checkPositiveX) {
                world[i][y1] = Tileset.WOOD;
            }
            return;
        }
    }

    public void characterMove(char c) {
        int x = character[0], y = character[1];
        if (c == 'w' || c == 'W') {
            if (world[x][y + 1].equals(Tileset.WOOD)
                    || world[x][y + 1].equals(Tileset.LOCKED_DOOR)) {
                world[x][y] = Tileset.WOOD;
                world[x][y + 1] = Tileset.HERO;
                character[1] += 1;
            }
        } else if (c == 'a' || c == 'A') {
            if (world[x - 1][y].equals(Tileset.WOOD)
                    || world[x - 1][y].equals(Tileset.LOCKED_DOOR)) {
                world[x][y] = Tileset.WOOD;
                world[x - 1][y] = Tileset.HERO;
                character[0] -= 1;
            }
        } else if (c == 's' || c == 'S') {
            if (world[x][y - 1].equals(Tileset.WOOD)
                    || world[x][y - 1].equals(Tileset.LOCKED_DOOR)) {
                world[x][y] = Tileset.WOOD;
                world[x][y - 1] = Tileset.HERO;
                character[1] -= 1;
            }
        } else if (c == 'd' || c == 'D') {
            if (world[x + 1][y].equals(Tileset.WOOD)
                    || world[x + 1][y].equals(Tileset.LOCKED_DOOR)) {
                world[x][y] = Tileset.WOOD;
                world[x + 1][y] = Tileset.HERO;
                character[0] += 1;
            }
        }
    }

    public String mouseInfo(int x, int y) {
        if (Math.abs(x - character[0]) < lightSize + 1 && Math.abs(y - character[1]) < lightSize + 1) {
            return world[x][y].description();
        } else {
            return "nothing";
        }
    }
}
