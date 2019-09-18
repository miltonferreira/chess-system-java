package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece{
	//-------------- Pe�a do jogo de xadrez --------------
	
	private Color color;	//cor da pe�a
	
	//no construtor recebe o tabuleiro e a cor
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	//retorna a cor da pe�a
	public Color getColor() {
		return color;
	}
	
	
}
