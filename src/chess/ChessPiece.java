package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece{
	//-------------- Peça do jogo de xadrez --------------
	
	private Color color;	//cor da peça

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	
}
