package ProFinal.Piezas;

import javax.swing.plaf.basic.BasicProgressBarUI;

import ProFinal.Tablero;

public class Reina extends Pieza {

    public Reina(int x, int y, boolean blanca, String dirc, Tablero tablero) {
        super(x, y, blanca, dirc, tablero);
    }

    
    public boolean Mueve(int destinox, int destinoy) {
        // Remember: A Queen can move as many squares as she wants forward,
        // backward, sideways, or diagonally, without jumping over any pieces.
        // She cannot attack her own pieces.

        // WRITE CODE HERE
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
        //Son los mismos movimientos que tiene la torre y alfil
        /*if(destinox!=dx && destinoy!=dy){
            return false;
        }*/

        if(destinox!=dx && destinoy!=dy && lx != ly){
            return false;
        }
        //Buscamos la dirección en la que nos estamos intentando mover 
        
        String dir = "";
        if(destinoy < dy){
            dir = "norte";
        }

        if(destinoy > dy){
            dir = "sur";
        }
        if(destinox > this.getX()){
            dir = "este";
        }
        if(destinox < this.getX()){
            dir = "oeste";
        }
        if(destinoy > dy && destinox < dx) {
        	dir = "sw";
        }
        if(destinoy < dy && destinox < dx) {
        	dir = "nw";
        }
        if(destinoy > dy && destinox > dx) {
        	dir = "se";
        }
        if(destinoy < dy && destinox > dx) {
        	dir = "ne";
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
        if(dir.equals("sur")){
            int nMov = Math.abs(destinoy - this.getY());
            for(int i=1; i < nMov; i++){
                Pieza p = tablero.getPieza(this.getX(), this.getY() +i);
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
        
        if(dir.contains("se")) {
        	for(int i = 1; i < lx; i++) {
        		Pieza nPiece = tablero.getPieza(dx + i, dy + i);
        		if( nPiece != null) {
       			 return false;
        		}
        	}
        }
        if(dir.contains("ne")) {
        	for(int i = 1; i < lx; i++) {
        		Pieza nPiece = tablero.getPieza(dx + i, dy - i);
        		if( nPiece != null) {
       			 return false;
        		}
        	}
        }
        if(dir.contains("sw")) {
        	for(int i = 1; i < lx; i++) {
        		Pieza nPiece = tablero.getPieza(dx - i, dy + i);
        		if( nPiece != null) {
       			 return false;
        		}
        	}
        }
        if(dir.contains("nw")) {
        	for(int i = 1; i < lx; i++) {
        		Pieza nPiece = tablero.getPieza(dx - i, dy - i);
        		if( nPiece != null) {
       			 return false;
        		}
        	}
        }

        return true;
    }
}
