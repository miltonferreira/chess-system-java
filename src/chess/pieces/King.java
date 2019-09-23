package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	// ---- Tipo da peça no tabuleiro de xadrez ----

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		// indica no tabuleiro que é a Torre/Rook
		return "K";
	}

	// checa se o rei pode mover para determinada posicao
	private boolean canMove(Position position) {

		ChessPiece p = (ChessPiece) getBoard().piece(position); // pega a posicao da peça do tabuleiro
		return p == null || p.getColor() != getColor(); 		// checa se a posicao é nula ou a cor é diferente da outra peça no caminho
	}

	@Override
	public boolean[][] possibleMoves() {
		// cria uma matriz de booleanos com as dimensoes do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0); // posicao auxiliar

		// acima ------------------------------------------------------------
		p.setValue(position.getRow() - 1, position.getColumn());

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// abaixo ------------------------------------------------------------
		p.setValue(position.getRow() + 1, position.getColumn());

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// esquerda ------------------------------------------------------------
		p.setValue(position.getRow(), position.getColumn() - 1);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// direita ------------------------------------------------------------
		p.setValue(position.getRow(), position.getColumn() + 1);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// noroeste ------------------------------------------------------------
		p.setValue(position.getRow() - 1, position.getColumn() - 1);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// nordeste ------------------------------------------------------------
		p.setValue(position.getRow() - 1, position.getColumn() + 1);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// suldoeste ------------------------------------------------------------
		p.setValue(position.getRow() + 1, position.getColumn() - 1);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// suldeste ------------------------------------------------------------
		p.setValue(position.getRow() + 1, position.getColumn() + 1);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		return mat;
	}

}
