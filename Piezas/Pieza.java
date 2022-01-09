package ProFinal.Piezas;

import ProFinal.Tablero;

public class Pieza {
    private int x;
    private int y;
    final private boolean Blanca;
    private String dirc;
    public Tablero tablero;

    public Pieza(int x, int y, boolean Blanca, String dirc, Tablero tablero) {
        this.Blanca = Blanca;
        this.x = x;
        this.y = y;
        this.dirc = dirc;
        this.tablero = tablero;
    }

    public String getDirc() {
        return dirc;
    }

    public void setDirc(String path) {
        this.dirc = path;
    }

    public boolean Blanca() {
        return Blanca;
    }

    public boolean Negra() {
        return !Blanca;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean Mueve(int destinox, int destinoy) {
        return false;
    }
}
