package pl.edu.amu.wmi.projekt_szi.model;

import pl.edu.amu.wmi.projekt_szi.model.AbstractField;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Floor extends AbstractField {

    private static final String FLOOR_IMAGE_PATH = "res/drzewo.png";

    private static BufferedImage floorImage;

    public Floor(Integer x, Integer y) {
        super(x, y, FieldType.FLOOR);
        try {
            floorImage = ImageIO.read(new File(FLOOR_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(floorImage, TILE_SIZE * getLocation().getX(), TILE_SIZE * getLocation().getY(), null);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
