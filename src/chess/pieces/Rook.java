package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Por herda da classe ChessPiece, a classe é obriga a ter um construtor
//com parametros da classe ChessPiece(chamada para super classe)
public class Rook extends ChessPiece {
	// ---- Tipo da peça no tabuleiro de xadrez ----

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		// indica no tabuleiro que é a Torre/Rook
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		// cria uma matriz de booleanos com as dimensoes do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// checa posicoes acima da peça --------------------------------------------
		p.setValue(position.getRow() - 1, position.getColumn());

		// enquanto existir posiçao e nao tiver peças no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a peça pode move até a posicao
			p.setRow(p.getRow() - 1); // faz o p subir mais uma linha
		}
		// checa se no caminho tem uma peça oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a peça pode move até a posicao
		}
		// checa posicoes acima da peça --------------------------------------------

		// checa posicoes a esquerda da peça --------------------------------------------
		p.setValue(position.getRow(), position.getColumn() - 1);

		// enquanto existir posiçao e nao tiver peças no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a peça pode move até a posicao
			p.setColumn(p.getColumn() - 1); // faz o p ir para a esquerda
		}
		// checa se no caminho tem uma peça oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a peça pode move até a posicao
		}
		// checa posicoes a esquerda da peça --------------------------------------------

		// checa posicoes a direita da peça --------------------------------------------
		p.setValue(position.getRow(), position.getColumn() + 1);

		// enquanto existir posiçao e nao tiver peças no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a peça pode move até a posicao
			p.setColumn(p.getColumn() + 1); // faz o p ir para a esquerda
		}
		// checa se no caminho tem uma peça oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a peça pode move até a posicao
		}
		// checa posicoes a direita da peça --------------------------------------------
		
		// checa posicoes abaixo da peça --------------------------------------------
		p.setValue(position.getRow() + 1, position.getColumn());

		// enquanto existir posiçao e nao tiver peças no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a peça pode move até a posicao
			p.setRow(p.getRow() + 1); // faz o p subir mais uma linha
		}
		// checa se no caminho tem uma peça oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a peça pode move até a posicao
		}
		// checa posicoes abaixo da peça --------------------------------------------

		return mat;
	}

}
