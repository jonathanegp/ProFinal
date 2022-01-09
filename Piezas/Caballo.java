package Piezas;
import Ajedrez.Tablero;
public class Caballo extends Pieza {

    public Caballo(int x, int y, boolean Blanca, String dirc, Tablero tablero) {
        super(x, y, Blanca, dirc, tablero);
    }

    public boolean Mueve(int destinox, int destinoy) {
        // Esta pieza si puede saltar sobre
        // otras gracias a dios o al universo
        // como sea una cosa menos por agregar.

        int dx = this.getX();
        int dy = this.getY();

        int ly = Math.abs(destinoy - dy);
        int lx = Math.abs(destinox - dx);

        Pieza posible = tablero.getPieza(destinox, destinoy);
        // No dejamos que se coma las de su color
        if (posible != null) {
            if (posible.Blanca() && Blanca()) {
                return false;
            }
            if (posible.Negra() && Negra()) {
                return false;
            }
        }

        // La hacemos mover en forma de L
        /*
         * if(((lx == 2 && ly == 1)||(ly == 2 && lx == 1))) {
         * return true;
         * }
         */
        if ((lx) * (lx) + (ly) * (ly) == 5) {
            return true;
        }

        return false;
    }
}
