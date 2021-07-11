package byog.Core;
import byog.TileEngine.TERenderer;

public class WorldTest {
    public static void main(String[] args) {
        World w = new World(12345678, 80, 30);
        TERenderer ter = new TERenderer();
        ter.initialize(80, 30);





        w.draw(ter);
    }
}
