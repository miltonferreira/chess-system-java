package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	// ---- Tipo da peça no tabuleiro de xadrez ----

	private ChessMatch chessMatch; // rei precisa saber como está o tabuleiro para fazer jogada especial de roque

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		// indica no tabuleiro que é a Torre/Rook
		return "K";
	}

	// checa se o rei pode mover para determinada posicao
	private boolean canMove(Position position) {

		ChessPiece p = (ChessPiece) getBoard().piece(position); // pega a posicao da peça do tabuleiro
		return p == null || p.getColor() != getColor(); // checa se a posicao é nula ou a cor é diferente da outra peça
														// no caminho
	}

	// verifica se a torre está parada em sua posicao inicial
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position); // pega peça na posicao informada
		// retorna se torre está apta para a jogada especial roque *** == getColor()
		// mesma cor do rei ***
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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

		// jogada especial de roque --------------------------------------------
		// roque pequeno a direita
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {

			Position post1 = new Position(position.getRow(), position.getColumn() + 3); // pega posicao da torre a direita

			if (testRookCastling(post1)) {

				Position p1 = new Position(position.getRow(), position.getColumn() + 1); // checa se posicao da casa +1 a direta do rei está vazia
				Position p2 = new Position(position.getRow(), position.getColumn() + 2); // checa se posicao da casa +2 a direta do rei está vazia

				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					// se as duas casas estiverem vazia entao pode acontecer o roque
					mat[position.getRow()][position.getColumn() + 2] = true; 			// adiciona esse movimento possivel ao rei
				}
			}
		}
		// roque grande a esquerda
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {

			Position post2 = new Position(position.getRow(), position.getColumn() - 4); // pega posicao da torre a esquerda

			if (testRookCastling(post2)) {

				Position p1 = new Position(position.getRow(), position.getColumn() - 1); // checa se posicao da casa -1 a esquerda do rei está vazia
				Position p2 = new Position(position.getRow(), position.getColumn() - 2); // checa se posicao da casa -2 a esquerda do rei está vazia
				Position p3 = new Position(position.getRow(), position.getColumn() - 3); // checa se posicao da casa -3 a esquerda do rei está vazia

				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					// se as duas casas estiverem vazia entao pode acontecer o roque
					mat[position.getRow()][position.getColumn() - 2] = true; 			// adiciona esse movimento possivel ao rei
				}
			}
		}

		return mat;
	}

}
