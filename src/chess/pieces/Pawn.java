package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {

		// cria uma matriz de booleanos com as dimensoes do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// anda para cima, linhas a menos por ser branca
		if (getColor() == Color.WHITE) {

			p.setValue(position.getRow() - 1, position.getColumn()); // recebe nova linha +1/coluna

			// verifica se a posicao existe e nao tem peça na posiçao
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; // a peça pode mover para a posicao
			}

			p.setValue(position.getRow() - 2, position.getColumn()); // recebe nova linha +2/coluna
			Position p2 = new Position(position.getRow() - 1, position.getColumn()); // pega posicao a frente do peao

			// verifica se a posicao existe e nao tem peça na posiçao e o contador igual 0
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0
					&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				mat[p.getRow()][p.getColumn()] = true; // a peça pode mover para a posicao
			}

			// movimento de capturar peça do oponente
			// ------------------------------------------------------
			// esquerda
			p.setValue(position.getRow() - 1, position.getColumn() - 1); // recebe nova linha +1/coluna

			// verifica se a posicao existe e tem oponente
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; // a peça pode mover para a posicao
			}
			// direita
			p.setValue(position.getRow() - 1, position.getColumn() + 1); // recebe nova linha +1/coluna

			// verifica se a posicao existe e tem oponente
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; // a peça pode mover para a posicao
			}

			// movimento especial en passant para peça branca
			if (position.getRow() == 3) {

				Position left = new Position(position.getRow(), position.getColumn() - 1); // pega posicao da casa a
																							// esquerda
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true; // inclue movimento possivel ao peao a esquerda
				}

				Position right = new Position(position.getRow(), position.getColumn() + 1); // pega posicao da casa a
																							// esquerda
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true; // inclue movimento possivel ao peao a esquerda
				}

			}

		} else {
			// peça preta ------------------------------------------------
			p.setValue(position.getRow() + 1, position.getColumn()); // recebe nova linha +1/coluna

			// verifica se a posicao existe e nao tem peça na posiçao
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; // a peça pode mover para a posicao
			}

			p.setValue(position.getRow() + 2, position.getColumn()); // recebe nova linha +2/coluna
			Position p2 = new Position(position.getRow() + 1, position.getColumn()); // pega posicao a frente do peao

			// verifica se a posicao existe e nao tem peça na posiçao e o contador igual 0
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0
					&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				mat[p.getRow()][p.getColumn()] = true; // a peça pode mover para a posicao
			}

			// movimento de capturar peça do oponente
			// ------------------------------------------------------
			// esquerda
			p.setValue(position.getRow() + 1, position.getColumn() - 1); // recebe nova linha +1/coluna

			// verifica se a posicao existe e tem oponente
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; // a peça pode mover para a posicao
			}
			// direita
			p.setValue(position.getRow() + 1, position.getColumn() + 1); // recebe nova linha +1/coluna

			// verifica se a posicao existe e tem oponente
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; // a peça pode mover para a posicao
			}

			// movimento especial en passant para peça preta
			if (position.getRow() == 4) {

				Position left = new Position(position.getRow(), position.getColumn() - 1); // pega posicao da casa a esquerda
																							
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true; // inclue movimento possivel ao peao a esquerda
				}

				Position right = new Position(position.getRow(), position.getColumn() + 1); // pega posicao da casa a esquerda
																							
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true; // inclue movimento possivel ao peao a esquerda
				}

			}

		}

		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
