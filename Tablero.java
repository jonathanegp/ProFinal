package ProFinal;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import ProFinal.Piezas.*;

public class Tablero extends JComponent {

    public int turnCounter = 0;
    private static Image NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

    private final int tamTabl = 65;
    public ArrayList<Pieza> Blancas;
    public ArrayList<Pieza> Negras;

    public ArrayList<DrawingShape> Static_Shapes;
    public ArrayList<DrawingShape> Piece_Graphics;

    public Pieza PiezaActiva;

    private final int fil = 8;
    private final int col = 8;
    private Integer[][] TaPrincipal;
    private String board_file_path = "img" + File.separator + "tabl.png";
    private String botonActivo = "img" + File.separator + "rojo.png";

    public void dibTab() {
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                TaPrincipal[i][j] = 0;
            }
        }

        Blancas.add(new Rey(3, 0, true, "Rey.png", this));
        Blancas.add(new Reina(4, 0, true, "Reina.png", this));
        Blancas.add(new Alfil(2, 0, true, "Alfil.png", this));
        Blancas.add(new Alfil(5, 0, true, "Alfil.png", this));
        Blancas.add(new Caballo(1, 0, true, "Caballo.png", this));
        Blancas.add(new Caballo(6, 0, true, "Caballo.png", this));
        Blancas.add(new Torre(0, 0, true, "Torre.png", this));
        Blancas.add(new Torre(7, 0, true, "Torre.png", this));
        Blancas.add(new Peon(0, 1, true, "Peon.png", this));
        Blancas.add(new Peon(1, 1, true, "Peon.png", this));
        Blancas.add(new Peon(2, 1, true, "Peon.png", this));
        Blancas.add(new Peon(3, 1, true, "Peon.png", this));
        Blancas.add(new Peon(4, 1, true, "Peon.png", this));
        Blancas.add(new Peon(5, 1, true, "Peon.png", this));
        Blancas.add(new Peon(6, 1, true, "Peon.png", this));
        Blancas.add(new Peon(7, 1, true, "Peon.png", this));

        Negras.add(new Rey(3, 7, false, "Rey.png", this));
        Negras.add(new Reina(4, 7, false, "Reina.png", this));
        Negras.add(new Alfil(2, 7, false, "Alfil.png", this));
        Negras.add(new Alfil(5, 7, false, "Alfil.png", this));
        Negras.add(new Caballo(1, 7, false, "Caballo.png", this));
        Negras.add(new Caballo(6, 7, false, "Caballo.png", this));
        Negras.add(new Torre(0, 7, false, "Torre.png", this));
        Negras.add(new Torre(7, 7, false, "Torre.png", this));
        Negras.add(new Peon(0, 6, false, "Peon.png", this));
        Negras.add(new Peon(1, 6, false, "Peon.png", this));
        Negras.add(new Peon(2, 6, false, "Peon.png", this));
        Negras.add(new Peon(3, 6, false, "Peon.png", this));
        Negras.add(new Peon(4, 6, false, "Peon.png", this));
        Negras.add(new Peon(5, 6, false, "Peon.png", this));
        Negras.add(new Peon(6, 6, false, "Peon.png", this));
        Negras.add(new Peon(7, 6, false, "Peon.png", this));

    }

    public Tablero() {

        TaPrincipal = new Integer[fil][col];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        Blancas = new ArrayList();
        Negras = new ArrayList();

        dibTab();

        this.setBackground(new Color(37, 13, 84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));

        this.addMouseListener(mouseAdapter);
        this.addComponentListener(componentAdapter);
        this.addKeyListener(keyAdapter);

        this.setVisible(true);
        this.requestFocus();
        Dibuja();
    }

    private void Dibuja() {
        Piece_Graphics.clear();
        Static_Shapes.clear();
        

        Image tabl = loadImage(board_file_path);
        Static_Shapes
                .add(new DrawingImage(tabl, new Rectangle2D.Double(0, 0, tabl.getWidth(null), tabl.getHeight(null))));
        if (PiezaActiva != null) {
            Image rojo = loadImage("img" + File.separator + "rojo.png");
            Static_Shapes.add(new DrawingImage(rojo, new Rectangle2D.Double(tamTabl * PiezaActiva.getX(),
                    tamTabl * PiezaActiva.getY(), rojo.getWidth(null), rojo.getHeight(null))));
        }
        for (int i = 0; i < Blancas.size(); i++) {
            int COL = Blancas.get(i).getX();
            int ROW = Blancas.get(i).getY();
            Image piece = loadImage("img" + File.separator + "blancas" + File.separator + Blancas.get(i).getDirc());
            Piece_Graphics.add(new DrawingImage(piece,
                    new Rectangle2D.Double(tamTabl * COL, tamTabl * ROW, piece.getWidth(null), piece.getHeight(null))));
        }
        for (int i = 0; i < Negras.size(); i++) {
            int COL = Negras.get(i).getX();
            int ROW = Negras.get(i).getY();
            Image piece = loadImage("img" + File.separator + "negras" + File.separator + Negras.get(i).getDirc());
            Piece_Graphics.add(new DrawingImage(piece,
                    new Rectangle2D.Double(tamTabl * COL, tamTabl * ROW, piece.getWidth(null), piece.getHeight(null))));
        }
        this.repaint();
    }

    public Pieza getPieza(int x, int y) {
        for (Pieza p : Blancas) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        for (Pieza p : Negras) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        return null;
    }

    private MouseAdapter mouseAdapter = new MouseAdapter() {

        
        public void mouseClicked(MouseEvent e) {

        }

        
        public void mousePressed(MouseEvent e) {
            int d_X = e.getX();
            int d_Y = e.getY();
            int Clicked_Row = d_Y / tamTabl;
            int Clicked_Column = d_X / tamTabl;
            boolean is_whites_turn = true;
            if (turnCounter % 2 == 1) {
                is_whites_turn = false;
            }

            Pieza clicked_piece = getPieza(Clicked_Column, Clicked_Row);

            if (PiezaActiva == null && clicked_piece != null &&
                    ((is_whites_turn && clicked_piece.Blanca()) || (!is_whites_turn && clicked_piece.Negra()))) {
                PiezaActiva = clicked_piece;
            } else if (PiezaActiva != null && PiezaActiva.getX() == Clicked_Column
                    && PiezaActiva.getY() == Clicked_Row) {
                PiezaActiva = null;
            } else if (PiezaActiva != null && PiezaActiva.Mueve(Clicked_Column, Clicked_Row)
                    && ((is_whites_turn && PiezaActiva.Blanca()) || (!is_whites_turn && PiezaActiva.Negra()))) {
                if (clicked_piece != null) {
                    if (clicked_piece.Blanca()) {
                        Blancas.remove(clicked_piece);
                    } else {
                        Negras.remove(clicked_piece);
                    }
                }
                
                PiezaActiva.setX(Clicked_Column);
                PiezaActiva.setY(Clicked_Row);

                // Si la pieza es peon 
                if (PiezaActiva.getClass().equals(Peon.class)) {
                    Peon castedPawn = (Peon) (PiezaActiva);
                    castedPawn.setMovimiento(true);
                }

                PiezaActiva = null;
                turnCounter++;
            }

            Dibuja();
        }

        
        public void mouseDragged(MouseEvent e) {
        }

        
        public void mouseReleased(MouseEvent e) {
        }

        
        public void mouseWheelMoved(MouseWheelEvent e) {
        }

    };

    private void adjustShapePositions(double dx, double dy) {

        Static_Shapes.get(0).adjustPosition(dx, dy);
        this.repaint();

    }

    private Image loadImage(String imageFile) {
        try {
            return ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            return NULL_IMAGE;
        }
    }

    
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);
        drawShapes(g2);
    }

    private void drawBackground(Graphics2D g2) {
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawShapes(Graphics2D g2) {
        for (DrawingShape shape : Static_Shapes) {
            shape.draw(g2);
        }
        for (DrawingShape shape : Piece_Graphics) {
            shape.draw(g2);
        }
    }

    private ComponentAdapter componentAdapter = new ComponentAdapter() {

        
        public void componentHidden(ComponentEvent e) {

        }

        
        public void componentMoved(ComponentEvent e) {

        }

        
        public void componentResized(ComponentEvent e) {

        }

        
        public void componentShown(ComponentEvent e) {

        }
    };

    private KeyAdapter keyAdapter = new KeyAdapter() {

       
        public void keyPressed(KeyEvent e) {

        }

        
        public void keyReleased(KeyEvent e) {

        }

        
        public void keyTyped(KeyEvent e) {

        }
    };

}

interface DrawingShape {
    boolean contains(Graphics2D g2, double x, double y);

    void adjustPosition(double dx, double dy);

    void draw(Graphics2D g2);
}

class DrawingImage implements DrawingShape {

    public Image image;
    public Rectangle2D rect;

    public DrawingImage(Image image, Rectangle2D rect) {
        this.image = image;
        this.rect = rect;
    }

    
    public boolean contains(Graphics2D g2, double x, double y) {
        return rect.contains(x, y);
    }

    
    public void adjustPosition(double dx, double dy) {
        rect.setRect(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());
    }

   
    public void draw(Graphics2D g2) {
        Rectangle2D bounds = rect.getBounds2D();
        g2.drawImage(image, (int) bounds.getMinX(), (int) bounds.getMinY(), (int) bounds.getMaxX(),
                (int) bounds.getMaxY(),
                0, 0, image.getWidth(null), image.getHeight(null), null);
    }
}
