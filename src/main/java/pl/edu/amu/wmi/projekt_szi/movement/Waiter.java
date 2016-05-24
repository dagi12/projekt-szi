package pl.edu.amu.wmi.projekt_szi.movement;

import pl.edu.amu.wmi.projekt_szi.ApplicationConstants;
import pl.edu.amu.wmi.projekt_szi.model.AbstractField;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Waiter extends AbstractField implements ApplicationConstants {

    private static final String WAITER_IMAGE_PATH = "res/waiter.png";

    private static BufferedImage waiterImage;

    private Direction direction;

    public Waiter() {
        super(WAITER_START_X, WAITER_START_Y, FieldType.WAITER);
        try {
            waiterImage = ImageIO.read(new File(WAITER_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.direction = WAITER_START_DIRECTION;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(waiterImage, TILE_SIZE * getLocation().getX(), getLocation().getY(), null);
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
}
