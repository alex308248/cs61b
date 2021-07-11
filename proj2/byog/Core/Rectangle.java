package byog.Core;

import java.io.Serializable;

public class Rectangle implements Serializable {
    private int width;
    private int height;
    private int posX;
    private int posY;
    private static final long serialVersionUID = 45498234798734234L;

    public Rectangle(Integer w, Integer h, Integer x, Integer y) {
        width = w;
        height = h;
        posX = x;
        posY = y;
    }

    public boolean checkOverlap(Rectangle r1) {
        if (posX < r1.posX && posX + width >= r1.posX
                || posX > r1.posX && r1.posX + width >= posX
                || posY > r1.posY && r1.posY + width >= posY
                || posY < r1.posY && posY + width >= r1.posY) {
            return true;
        }
        return false;

    }

    public int getPosX() {
        return posX;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPosY() {
        return posY;
    }

}
