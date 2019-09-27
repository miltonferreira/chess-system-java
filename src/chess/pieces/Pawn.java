package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);

	}

	@Override
	public boolean[][] possibleMoves() {

		// cria uma matriz de booleanos com as dimensoes do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// anda para cima, linhas a menos por ser branca
		if (getColor() == Color.WHITE) {
			
			p.setValue(position.getRow() - 1, position.getColumn());					// recebe nova linha +1/coluna

			// verifica se a posicao existe e nao tem peça na posiçao
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; 									// a peça pode mover para a posicao
			}

			p.setValue(position.getRow() - 2, position.getColumn());					// recebe nova linha +2/coluna
			Position p2 = new Position(position.getRow() - 1, position.getColumn());	// pega posicao a frente do peao

			// verifica se a posicao existe e nao tem peça na posiçao e o contador igual 0
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				mat[p.getRow()][p.getColumn()] = true; // a peça pode mover para a posicao
			}
			
			// movimento de capturar peça do oponente ------------------------------------------------------
			// esquerda
			p.setValue(position.getRow() - 1, position.getColumn() - 1);				// recebe nova linha +1/coluna

			// verifica se a posicao existe e tem oponente
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; 									// a peça pode mover para a posicao
			}
			// direita
			p.setValue(position.getRow() - 1, position.getColumn() + 1);				// recebe nova linha +1/coluna

			// verifica se a posicao existe e tem oponente
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; 									// a peça pode mover para a posicao
			}
			
		}else{
			// peça preta ------------------------------------------------
			p.setValue(position.getRow() + 1, position.getColumn());					// recebe nova linha +1/coluna

			// verifica se a posicao existe e nao tem peça na posiçao
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; 									// a peça pode mover para a posicao
			}

			p.setValue(position.getRow() + 2, position.getColumn());					// recebe nova linha +2/coluna
			Position p2 = new Position(position.getRow() - 1, position.getColumn());	// pega posicao a frente do peao

			// verifica se a posicao existe e nao tem peça na posiçao e o contador igual 0
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				mat[p.getRow()][p.getColumn()] = true; // a peça pode mover para a posicao
			}
			
			// movimento de capturar peça do oponente ------------------------------------------------------
			// esquerda
			p.setValue(position.getRow() + 1, position.getColumn() - 1);				// recebe nova linha +1/coluna

			// verifica se a posicao existe e tem oponente
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; 									// a peça pode mover para a posicao
			}
			// direita
			p.setValue(position.getRow() + 1, position.getColumn() + 1);				// recebe nova linha +1/coluna

			// verifica se a posicao existe e tem oponente
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; 									// a peça pode mover para a posicao
			}
		}

		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
	
	

}
