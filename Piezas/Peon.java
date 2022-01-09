package Piezas;
import Ajedrez.Tablero;
public class Peon extends Pieza {

    private boolean nMovimientos;

    public Peon(int x, int y, boolean blanca, String dirc, Tablero tablero) {
        super(x, y, blanca, dirc, tablero);
        nMovimientos = false;
    }

    public void setMovimiento(boolean nMovimientos) {
        this.nMovimientos = nMovimientos;
    }

    public boolean getMovimiento() {
        return nMovimientos;
    }

    public boolean Mueve(int destinox, int destinoy) {
        boolean movAnt = getMovimiento();
        int dx = this.getY();
        int dy = this.getX();

        int ly = Math.abs(destinoy - dx);
        int lx = Math.abs(destinox - dy);

        char dec = 0;

        Pieza posible = tablero.getPieza(destinox, destinoy);
        // No dejamos comer nuestras piezas
        if (posible != null) {
            if (posible.Blanca() && Blanca()) {
                return false;
            } else if (posible.Negra() && Negra()) {// si es negra y quiere comer negra no es valido pues, lo mismo
                                                         // que en el caso de las blancas
                return false;
            }
        }

        // no se puede mover en x.
        // Si es primer mov se puede mover dos casillas.
        // Come en diagonal
        if (lx > 1 || ly > 2) {
            return false;
        }
        if (lx == 1 && ly != 1) {
            return false;
        }

        if (dx > destinoy) {
            dec = 'n';
        }
        if (dx < destinoy) {
            dec = 's';
        }
        if (nMovimientos && ly > 1) {
            return false;
        }

        if (dec == 'n') {
            if (this.Blanca()) {
                return false;
            }
            if (posible == null && lx == 1) {
                return false;
            }
            if (lx != 1) {
                Pieza nPiece = tablero.getPieza(dy, dx - 1);
                Pieza nPiece1 = tablero.getPieza(dy, dx - 2);
                if (nPiece != null) {
                    return false;
                }
                if (ly == 2 && nPiece1 != null) {
                    return false;
                }
            }
        }
        if (dec == 's') {
            if (this.Negra()) {
                return false;
            }
            if (posible == null && lx == 1) {
                return false;
            }
            if (lx != 1) {
                Pieza nPiece = tablero.getPieza(dy, dx + 1);
                Pieza nPiece1 = tablero.getPieza(dy, dx + 2);
                if (nPiece != null) {
                    return false;
                }
                if (ly == 2 && nPiece1 != null) {
                    return false;
                }
            }
        }
        return true;
    }
}