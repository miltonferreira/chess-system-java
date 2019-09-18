package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{
	// ---- Tipo da pe�a no tabuleiro de xadrez ----

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		//indica no tabuleiro que � a Torre/Rook
		return "K";
	}
	
}