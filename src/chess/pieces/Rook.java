package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//Por herda da classe ChessPiece, a classe é obriga a ter um construtor
//com parametros da classe ChessPiece(chamada para super classe)
public class Rook extends ChessPiece{
	// ---- Tipo da peça no tabuleiro de xadrez ----

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		//indica no tabuleiro que é a Torre/Rook
		return "R";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		//cria uma matriz de booleanos com as dimensoes do tabuleiro 
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}
	
}
