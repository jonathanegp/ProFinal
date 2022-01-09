package Piezas;
import Ajedrez.Tablero;
public class Torre extends Pieza {

    public Torre(int x, int y, boolean blanca, String dirc, Tablero tablero) {
        super(x, y, blanca, dirc, tablero);// llamamos al constructor de la clase piezas.
    }

    
    public boolean Mueve(int destinox, int destinoy) {
        // Remember: A rook can move as many squares as he wants either forward,
        // backward, or sideways without jumping any pieces. He cannot attack
        // his own pieces.
        // si hay una pieza en el destino, y es nuestra, no dejamos que se  mueva ahí
        int dx = this.getX();
        int dy = this.getY();



        Pieza posible = tablero.getPieza(destinox, destinoy);
        if(posible != null) {
            if(posible.Blanca() && Blanca()){
                return false;
            }
            if(posible.Negra() && Negra()){
                return false;
            }
        }
        if(destinox!=dx && destinoy!=dy){
            return false;
        }
        /*if((lx==ly) || (ly==lx)){
            return false;
        }else{
            if((lx)*(lx) +(ly)*(ly)==5){
                return false;
            }
        }*/


        //averigüamos en qué dirección estamos tratando de movernos
        String dir = "";
        if(destinox > this.getX()){
            dir = "este";
        }
        if(destinox < this.getX()){
            dir = "oeste";
        }
        if(destinoy > this.getY()){
            dir = "sur";
        }
        if(destinoy < this.getY()){
            dir = "norte";
        }
        // en cualquier dirección, nos aseguramos que no hay nadie que nos estorba 
        if(dir.equals("sur")){
            int nMov = Math.abs(destinoy - this.getY());
            for(int i=1; i < nMov; i++){
                Pieza p = tablero.getPieza(this.getX(), this.getY() +i);
                if(p != null){
                    return false;
                }
            }
        }
        if(dir.equals("norte")){
            int nMov = Math.abs(destinoy - this.getY());
            for(int i=1; i < nMov; i++){
                Pieza p = tablero.getPieza(this.getX(), this.getY() -i);
                if(p != null){
                    return false;
                }
            }
        }
        if(dir.equals("este")){
            int nMov = Math.abs(destinox - this.getX());
            for(int i=1; i < nMov; i++){
                Pieza p = tablero.getPieza(this.getX() +i, this.getY());
                if(p != null){
                    return false;
                }
            }
        }
        if(dir.equals("oeste")){
            int nMov = Math.abs(destinox - this.getX());
            for(int i=1; i < nMov; i++){
                Pieza p = tablero.getPieza(this.getX()- i, this.getY());
                if(p != null){
                    return false;
                }
            }
        }
        return true;
    }
}
