package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//Por herda da classe ChessPiece, a classe � obriga a ter um construtor
//com parametros da classe ChessPiece(chamada para super classe)
public class Rook extends ChessPiece{
	// ---- Tipo da pe�a no tabuleiro de xadrez ----

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		//indica no tabuleiro que � a Torre/Rook
		return "R";
	}
	
}
