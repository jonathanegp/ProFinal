package Ajedrez;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;

public class Frame extends JFrame {
    Component component;

    public Frame() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Ajedrez");
        this.setResizable(false);
        component = new Tablero();
        this.add(component, BorderLayout.CENTER);

        this.setLocation(200, 50);
        this.pack();
        this.setVisible(true);
    }
}
