package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece{
	//-------------- Peça do jogo de xadrez --------------
	
	private Color color;	//cor da peça
	
	//no construtor recebe o tabuleiro e a cor
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	//retorna a cor da peça
	public Color getColor() {
		return color;
	}
	
	
}
