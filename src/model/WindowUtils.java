package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowUtils {
    // Movement window
    public static void enableWindowDrag(JComponent dragComponent) {
        final Point clickPoint = new Point();

        dragComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clickPoint.x = e.getX();
                clickPoint.y = e.getY();
            }
        });

        dragComponent.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Window window = SwingUtilities.getWindowAncestor(dragComponent);
                if (window != null) {
                    int x = e.getXOnScreen() - clickPoint.x;
                    int y = e.getYOnScreen() - clickPoint.y;
                    window.setLocation(x, y);
                }
            }
        });
    }

    //Close program
    public static void attachExitButton(JButton btnExit) {
        btnExit.addActionListener(e -> System.exit(0));
    }

    //Minimice program
    public static void attachMinimizeButton(JButton btnMin, JComponent anyComponentInsideFrame) {
        btnMin.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(anyComponentInsideFrame);
            if (window instanceof JFrame frame) {
                frame.setState(JFrame.ICONIFIED);
            }
        });
    }
}
