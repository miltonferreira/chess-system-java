package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{
	// ---- Tipo da peça no tabuleiro de xadrez ----

	public Bishop(Board board, Color color) {
			super(board, color);
		}

	@Override
	public String toString() {
		// indica no tabuleiro que é a Torre/Rook
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		// cria uma matriz de booleanos com as dimensoes do tabuleiro
		// verifica movimentos possiveis da peça
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// checa posicoes noroeste da peça --------------------------------------------
		p.setValue(position.getRow() - 1, position.getColumn() - 1);

		// enquanto existir posiçao e nao tiver peças no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; 				// adiciona true na posicao e a peça pode move até a posicao
			p.setValue(p.getRow() - 1, p.getColumn() - 1); 		// faz o p subir noroeste mais uma linha e coluna
		}
		// checa se no caminho tem uma peça oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; 				// adiciona true na posicao e a peça pode move até a posicao
		}
		// checa posicoes noroeste da peça --------------------------------------------

		// checa posicoes nordeste da peça --------------------------------------------
		
		p.setValue(position.getRow() - 1, position.getColumn() + 1);

		// enquanto existir posiçao e nao tiver peças no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; 				// adiciona true na posicao e a peça pode move até a posicao
			p.setValue(p.getRow() - 1, p.getColumn() + 1); 		// faz o p ir para a nordeste
		}
		// checa se no caminho tem uma peça oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; 				// adiciona true na posicao e a peça pode move até a posicao
		}

		// checa posicoes nordeste da peça --------------------------------------------

		// checa posicoes a sudeste da peça --------------------------------------------
		p.setValue(position.getRow() + 1, position.getColumn() + 1);

		// enquanto existir posiçao e nao tiver peças no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; 				// adiciona true na posicao e a peça pode move até a posicao
			p.setValue(p.getRow() + 1, p.getColumn() + 1); 		// faz o p ir para o sudeste
		}
		// checa se no caminho tem uma peça oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; 				// adiciona true na posicao e a peça pode move até a posicao
		}
		// checa posicoes a sudeste da peça --------------------------------------------

		// checa posicoes sudoeste da peça --------------------------------------------
		p.setValue(position.getRow() + 1, position.getColumn() - 1);

		// enquanto existir posiçao e nao tiver peças no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; 				// adiciona true na posicao e a peça pode move até a posicao
			p.setValue(p.getRow() + 1, p.getColumn() - 1); 		// faz o p ir para sudoeste
		}
		// checa se no caminho tem uma peça oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; 				// adiciona true na posicao e a peça pode move até a posicao
		}
		// checa posicoes sudoeste da peça --------------------------------------------

		return mat;
	}
}
