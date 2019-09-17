package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece{
	//-------------- Pe�a do jogo de xadrez --------------
	
	private Color color;	//cor da pe�a

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	
}
