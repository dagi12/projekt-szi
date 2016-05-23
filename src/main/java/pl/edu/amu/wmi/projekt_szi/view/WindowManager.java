package pl.edu.amu.wmi.projekt_szi.view;

import pl.edu.amu.wmi.projekt_szi.movement.Waiter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by softra43 on 20.04.2016.
 */
public class WindowManager extends JFrame {

    pl.edu.amu.wmi.projekt_szi.view.Panel panel;

    public WindowManager() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setBackground(Color.WHITE);

        panel = new pl.edu.amu.wmi.projekt_szi.view.Panel();
        panel.setBackground(new Color(73, 207, 86));
        Container c = getContentPane();
        c.add(panel);
        //DiagnosticWindow d = new DiagnosticWindow();

        setVisible(true);


//        Timer timer = new Timer();
//        DrawingTask dTask = new DrawingTask(dPanel);
//        timer.schedule(dTask, 0, 10);

    }

    public void initMap(Waiter waiter) {
        panel.initMap(waiter);
    }
}
