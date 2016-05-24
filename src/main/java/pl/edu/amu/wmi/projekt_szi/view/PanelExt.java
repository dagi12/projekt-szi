package pl.edu.amu.wmi.projekt_szi.view;

import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.model.Location;
import pl.edu.amu.wmi.projekt_szi.model.Map;
import pl.edu.amu.wmi.projekt_szi.movement.Waiter;
import pl.edu.amu.wmi.projekt_szi.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

public class PanelExt extends JPanel {

    TreeMap<Location, AbstractField> treeMap;

    public PanelExt(TreeMap<Location, AbstractField> treeMap) {
        super();
        this.treeMap = treeMap;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        for (Entry<Location, AbstractField> entry : treeMap.entrySet()) {
            entry.getValue().draw(g);
        }
    }

}
