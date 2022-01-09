package Piezas;
import Ajedrez.Tablero;
public class Alfil extends Pieza {

	public Alfil(int x, int y, boolean blanca, String dirc, Tablero tablero) {
		super(x, y, blanca, dirc, tablero);
	}

	public boolean Mueve(int destinox, int destinoy) {

		int dx = this.getX();
		int dy = this.getY();

		int ly = Math.abs(destinoy - dy);
		int lx = Math.abs(destinox - dx);

		String dec = "";

		Pieza pPiece = tablero.getPieza(destinox, destinoy);
		if (pPiece != null) {
			if (pPiece.Blanca() && Blanca()) {
				return false;
			} else if (pPiece.Negra() && Negra()) {
				return false;
			}
		}

		if (lx != ly) {
			return false;
		}

		int l = lx;

		if (destinoy > dy && destinox < dx) {
			dec = "sw";
		}
		if (destinoy < dy && destinox < dx) {
			dec = "nw";
		}
		if (destinoy > dy && destinox > dx) {
			dec = "se";
		}
		if (destinoy < dy && destinox > dx) {
			dec = "ne";
		}

		if (dec.contains("se")) {
			for (int i = 1; i < l; i++) {
				Pieza nPiece = tablero.getPieza(dx + i, dy + i);
				if (nPiece != null) {
					return false;
				}
			}
		}
		if (dec.contains("ne")) {
			for (int i = 1; i < l; i++) {
				Pieza nPiece = tablero.getPieza(dx + i, dy - i);
				if (nPiece != null) {
					return false;
				}
			}
		}
		if (dec.contains("sw")) {
			for (int i = 1; i < l; i++) {
				Pieza nPiece = tablero.getPieza(dx - i, dy + i);
				if (nPiece != null) {
					return false;
				}
			}
		}
		if (dec.contains("nw")) {
			for (int i = 1; i < l; i++) {
				Pieza nPiece = tablero.getPieza(dx - i, dy - i);
				if (nPiece != null) {
					return false;
				}
			}
		}

		return true;
	}
}
