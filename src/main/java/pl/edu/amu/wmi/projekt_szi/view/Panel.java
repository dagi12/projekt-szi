package pl.edu.amu.wmi.projekt_szi.view;

import pl.edu.amu.wmi.projekt_szi.model.Map;
import pl.edu.amu.wmi.projekt_szi.movement.Waiter;
import pl.edu.amu.wmi.projekt_szi.model.Location;
import pl.edu.amu.wmi.projekt_szi.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by softra43 on 20.04.2016.
 */
public class Panel extends JPanel implements Observer {

    private static final Integer tileSize = new Integer(80);
    private static BufferedImage okGrass;
    private static BufferedImage neglectedGrass;
    private static BufferedImage urgentAttentionGrass;
    private static BufferedImage toTotalReclamation;
    private static BufferedImage tractorImage;
    private static BufferedImage treeImage;

    private Waiter waiter;


    public Panel() {
        super();
        loadImages();
    }

    TreeMap<Location, Field> fieldMap = null;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        while(fieldMap == null) {
            fieldMap = Map.getInstance().getTreeMap();
        }

        for (java.util.Map.Entry<Location, Field> entry : fieldMap.entrySet()) {
            Location location = entry.getKey();
            if (GrassType.NEGLECTED_GRASS.inRange(entry.getValue().getPriority().intValue())) {
                drawField(g, neglectedGrass, location);
            } else if (GrassType.URGENT_ATTENTION_GRASS.inRange(entry.getValue().getPriority().intValue())) {
                drawField(g, urgentAttentionGrass, location);
            } else if (GrassType.OK_GRASS.inRange(entry.getValue().getPriority().intValue())) {
                drawField(g, okGrass, location);
            } else if (GrassType.TO_TOTAL_RECLAMATION_GRASS.inRange(entry.getValue().getPriority().intValue())) {
                drawField(g, toTotalReclamation, location);
            }
        }

        drawField(g, tractorImage, waiter.getLocation());
        for (Pair pair : Map.getInstance().treeLocations) {
            drawField(g, treeImage, new Location(pair.getA(), pair.getB()));
        }
    }

    public void loadImages() {
        try {
            okGrass = ImageIO.read(new File("res/trawa1.png"));
            neglectedGrass = ImageIO.read(new File("res/trawa2.png"));
            urgentAttentionGrass = ImageIO.read(new File("res/trawa3.png"));
            toTotalReclamation = ImageIO.read(new File("res/trawa4.png"));
            tractorImage = ImageIO.read(new File("res/traktor1.png"));
            treeImage = ImageIO.read(new File("res/drzewo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawField(Graphics g, BufferedImage image, Location location) {
        g.drawImage(
                image,
                tileSize * location.getX(),
                tileSize * location.getY(),
                null
        );
    }

    public void initMap(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
