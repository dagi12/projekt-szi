package pl.edu.amu.wmi.projekt_szi.view;

import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.model.Location;

import javax.swing.*;
import java.awt.*;
import java.util.Map.Entry;
import java.util.TreeMap;

class PanelExt extends JPanel {

    private final TreeMap<Location, AbstractField> treeMap;

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
        repaint();
    }

}
