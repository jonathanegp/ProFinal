package Piezas;
import Ajedrez.Tablero;
public class Rey extends Pieza {

    public Rey(int x, int y, boolean blanca, String file, Tablero tablero) {
        super(x, y, blanca, file, tablero);

    }

    public boolean Mueve(int destinox, int destinoy) {
        // No dejamos que ataque a las de su color
        Pieza posible = tablero.getPieza(destinox, destinoy);

        int dx = this.getX();
        int dy = this.getY();

        int ly = Math.abs(destinoy - dy);
        int lx = Math.abs(destinox - dx);

        if (posible != null) {
            if (posible.Blanca() && this.Blanca()) {
                return false;
            }
            if (posible.Negra() && this.Negra()) {
                return false;
            }
        }
        // No se puede mover mas de una casilla
        if (lx <= 1 && ly <= 1) {
            return true;
        }
        // tenemos que evitar que se ponga en una casilla que es atacada
        // es decir, si al destino que me quiero mover es igual al destino
        // que se quiere mover otra pieza no me dejes mover

        if (posible != null){
            if (posible.Mueve(destinox, destinoy) == posible.Mueve(destinox, destinoy)) {
                return false;
            }
            if (posible.Negra() && this.Blanca()) {
                return false;
            }
        }

        return false;
    }
}
