package pl.edu.amu.wmi.projekt_szi.view;

import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.model.Location;

import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

public class WindowManager extends JFrame {

    public WindowManager(TreeMap<Location, AbstractField> treeMap) {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setBackground(Color.WHITE);

        PanelExt panelExt = new PanelExt(treeMap);
        panelExt.setBackground(new Color(73, 207, 86));
        Container c = getContentPane();
        c.add(panelExt);
        //DiagnosticWindow d = new DiagnosticWindow();
        setVisible(true);
//        Timer timer = new Timer();
//        DrawingTask dTask = new DrawingTask(dPanel);
//        timer.schedule(dTask, 0, 10);

    }

}
