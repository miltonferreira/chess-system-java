package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{
	// ---- Tipo da peça no tabuleiro de xadrez ----

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		//indica no tabuleiro que é a Torre/Rook
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		//cria uma matriz de booleanos com as dimensoes do tabuleiro 
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}
	
}
