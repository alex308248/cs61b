package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class World implements Serializable {
    private List<Rectangle> rectangles;
    private Random r;
    private static int WIDTH;
    private static int HEIGHT;
    private TETile[][] world;
    private static final long serialVersionUID = 123123123123123L;
    private int[] character = {0, 0};
    private int[] door = {0, 0};
    private boolean[][] w_B;
    private int lightSize = 4;

    public World(Integer seed, Integer w, Integer h) {
        WIDTH = w;
        HEIGHT = h;
        world = new TETile[WIDTH][HEIGHT]; // the world area
        w_B = new boolean[WIDTH][HEIGHT]; // record the room area for building hallways
        r = new Random(seed);
        rectangles = new ArrayList<Rectangle>(); // a list of rectangles, a.k.a. rooms
        int roomNum = RandomUtils.uniform(r, 50, 70);

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        for (int i = 0; i < roomNum; i += 1) {
            addRandomRoom();
        }
        addHallways();
        drawRoom();
        //addWall();
        //adding walls makes the world ugly, so I anotated it   QAQ
        addDoor();
        addCharacter();
    }

    //Checking whether the character is entering the locked door
    public boolean checkOutOfMaze() {
        if (character[0] == door[0] && character[1] == door[1]) {
            return true;
        }
        return false;
    }

    //Add a character at random part of the maze
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

    //the function for checking position [x,y] is in a room or not
    private boolean checkInRoom(int x, int y) {
        return w_B[x][y];
    }

    // adding a room at random position which have random size to w_B
    private void addRandomRoom() {
        int width = RandomUtils.uniform(r, 2, 5);
        int height = RandomUtils.uniform(r, 2, 5);
        int posX = RandomUtils.uniform(r, 3, WIDTH - width - 3);
        int posY = RandomUtils.uniform(r, 3, HEIGHT - height - 3);
        Rectangle newRectangle = new Rectangle(width, height, posX, posY);

        //If the position is overlapped, build a new rectangle
        for (int i = posX - 1; i <= posX + width; i += 1) {
            for (int j = posY - 1; j <= posY + height; j += 1) {
                if (checkInRoom(i, j)) {
                    addRandomRoom();
                    return;
                }
            }
        }

        for (int i = posX; i < posX + width; i += 1) {
            for (int j = posY; j < posY + height; j += 1) {
                w_B[i][j] = true;
            }
        }
        rectangles.add(newRectangle);
    }

    //adding the rooms from w_B to world
    private void drawRoom() {
        for (int id = 0; id < rectangles.size(); id++) {
            Rectangle rec = rectangles.get(id);
            for (int i = rec.getPosX(); i < rec.getPosX() + rec.getWidth(); i += 1) {
                for (int j = rec.getPosY(); j < rec.getPosY() + rec.getHeight(); j += 1) {
                    world[i][j] = Tileset.WOOD;
                }
            }
        }
    }

    //adding hallways to connect the rooms
    private void addHallways() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        for (int i = 0; i < rectangles.size() - 1; i++) {
            connectTwoRectangle(rectangles.get(i), rectangles.get(i + 1));
        }
        if (rectangles.size() > 0) {
            connectTwoRectangle(rectangles.get(rectangles.size() - 1), rectangles.get(0));
        }
    }

    // add walls out of rooms and hallways
    // I need to find a better Title because the current one is really ugly. lol
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

    //add a door at random position
    private void addDoor() {
        int x = RandomUtils.uniform(r, 0, WIDTH - 1);
        int y = RandomUtils.uniform(r, 0, HEIGHT - 1);

        if (world[x][y] != Tileset.WOOD) {
            addDoor();
            return;
        }

        int dir = RandomUtils.uniform(r, 0, 4);
        while (world[x][y] == Tileset.WOOD) {
            if (dir == 0) {
                x += 1;
            } else if (dir == 1) {
                x -= 1;
            } else if (dir == 2) {
                y += 1;
            } else {
                y -= 1;
            }
        }
        world[x][y] = Tileset.LOCKED_DOOR;
        door[0] = x;
        door[1] = y;
    }

    // draw the world with two choices
    // the upper one is for showing the whole world,
    // and the lower one can make more fun
    public void draw(TERenderer ter) {
        //ter.renderFrame(world);
        //ter.renderFrameLight(world, lightSize, character[0], character[1]);
        ter.renderFrameLight_2(world, 20, character[0], character[1]);
    }

    // return world for playing with inputString
    public TETile[][] getWorld() {
        return world;
    }

    // check whether the position [x,y] is in the room r1
    private boolean checkTitleInRectangle(Rectangle r1, int x, int y) {
        if (r1.getPosX() <= x && x <= r1.getPosX() + r1.getWidth()) {
            if (r1.getPosY() <= y && y <= r1.getPosY() + r1.getHeight()) {
                return true;
            }
        }
        return false;
    }

    // avoid the two parallel hallways next by next, if it happen then return true
    private boolean fourDirection(int x, int y, int dir) {
        if (world[x][y] == Tileset.WOOD) {
            return true;
        }

        if (dir == 1 || dir == -1) {
            if (world[x][y - 1] == Tileset.WOOD && world[x - dir][y - 1] == Tileset.WOOD) {
                return true;
            } else if (world[x][y + 1] == Tileset.WOOD && world[x - dir][y + 1] == Tileset.WOOD) {
                return true;
            }
        } else if (dir == 0 || dir == 2) {
            if (world[x - 1][y] == Tileset.WOOD && world[x - 1 - dir][y - 1] == Tileset.WOOD) {
                return true;
            } else if (world[x + 1][y] == Tileset.WOOD
                    && world[x - 1 - dir][y + 1] == Tileset.WOOD) {
                return true;
            }
        }
        return false;
    }

    // connect two rooms, avoid the parallel hallways,
    // and start building hallways when [x,y] is out of a room
    private void connectTwoRectangle(Rectangle r1, Rectangle r2) {
        int x1 = r1.getPosX();
        int y1 = r1.getPosY();
        int x2 = r2.getPosX();
        int y2 = r2.getPosY();

        if (x1 > x2) {
            for (int i = x2; i < x1; i += 1) {
                if (fourDirection(i, y2, 1) && !checkTitleInRectangle(r2, i, y2)) {
                    return;
                }
                world[i][y2] =  Tileset.WOOD;
            }
        } else {
            for (int i = x2; i >= x1; i -= 1) {
                if (fourDirection(i, y2, -1) && !checkTitleInRectangle(r2, i, y2)) {
                    return;
                }
                world[i][y2] =  Tileset.WOOD;
            }
        }

        if (y1 > y2) {
            for (int i = y2; i < y1; i += 1) {
                if (fourDirection(x1, i, 0) && !checkTitleInRectangle(r2, x2, i)) {
                    break;
                }
                world[x1][i] =  Tileset.WOOD;
            }
        } else {
            for (int i = y2; i >= y1; i -= 1) {
                if (fourDirection(x1, i, 2) && !checkTitleInRectangle(r2, x2, i)) {
                    break;
                }
                world[x1][i] =  Tileset.WOOD;
            }
        }
    }

    // make the character move by input
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

    // return the info of mouse clicking
    public String mouseInfo(int x, int y) {
        if (Math.abs(x - character[0]) < lightSize + 1
                && Math.abs(y - character[1]) < lightSize + 1) {
            return world[x][y].description();
        } else {
            return "nothing";
        }
    }
}
