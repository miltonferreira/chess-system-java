package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{
	// ---- Tipo da peça no tabuleiro de xadrez ----

	public Knight(Board board, Color color) {
			super(board, color);
		}

	@Override
	public String toString() {
		// indica no tabuleiro que é a Torre/Rook
		return "N";
	}

	// checa se pode mover para determinada posicao
	private boolean canMove(Position position) {

		ChessPiece p = (ChessPiece) getBoard().piece(position); // pega a posicao da peça do tabuleiro
		return p == null || p.getColor() != getColor(); // checa se a posicao é nula ou a cor é diferente da outra peça
														// no caminho
	}

	@Override
	public boolean[][] possibleMoves() {
		// cria uma matriz de booleanos com as dimensoes do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0); // posicao auxiliar

		// ????? ------------------------------------------------------------
		p.setValue(position.getRow() - 1, position.getColumn() - 2);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// ????? ------------------------------------------------------------
		p.setValue(position.getRow() - 2, position.getColumn() - 1);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// ?????? ------------------------------------------------------------
		p.setValue(position.getRow() - 2, position.getColumn() + 1);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// ?????? ------------------------------------------------------------
		p.setValue(position.getRow() - 1, position.getColumn() + 2);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// ??????? ------------------------------------------------------------
		p.setValue(position.getRow() + 1, position.getColumn() + 2);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// ???????? ------------------------------------------------------------
		p.setValue(position.getRow() + 2, position.getColumn() + 1);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// ??????? ------------------------------------------------------------
		p.setValue(position.getRow() + 2, position.getColumn() - 1);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		// ??????? ------------------------------------------------------------
		p.setValue(position.getRow() + 1, position.getColumn() - 2);

		// se existir a posicao e pode mover para a posicao
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true; // indica que pode mover para a posicao
		}

		return mat;
	}
}
