package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Por herda da classe ChessPiece, a classe � obriga a ter um construtor
//com parametros da classe ChessPiece(chamada para super classe)
public class Rook extends ChessPiece {
	// ---- Tipo da pe�a no tabuleiro de xadrez ----

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		// indica no tabuleiro que � a Torre/Rook
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		// cria uma matriz de booleanos com as dimensoes do tabuleiro
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// checa posicoes acima da pe�a --------------------------------------------
		p.setValue(position.getRow() - 1, position.getColumn());

		// enquanto existir posi�ao e nao tiver pe�as no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a pe�a pode move at� a posicao
			p.setRow(p.getRow() - 1); // faz o p subir mais uma linha
		}
		// checa se no caminho tem uma pe�a oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a pe�a pode move at� a posicao
		}
		// checa posicoes acima da pe�a --------------------------------------------

		// checa posicoes a esquerda da pe�a --------------------------------------------
		p.setValue(position.getRow(), position.getColumn() - 1);

		// enquanto existir posi�ao e nao tiver pe�as no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a pe�a pode move at� a posicao
			p.setColumn(p.getColumn() - 1); // faz o p ir para a esquerda
		}
		// checa se no caminho tem uma pe�a oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a pe�a pode move at� a posicao
		}
		// checa posicoes a esquerda da pe�a --------------------------------------------

		// checa posicoes a direita da pe�a --------------------------------------------
		p.setValue(position.getRow(), position.getColumn() + 1);

		// enquanto existir posi�ao e nao tiver pe�as no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a pe�a pode move at� a posicao
			p.setColumn(p.getColumn() + 1); // faz o p ir para a esquerda
		}
		// checa se no caminho tem uma pe�a oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a pe�a pode move at� a posicao
		}
		// checa posicoes a direita da pe�a --------------------------------------------
		
		// checa posicoes abaixo da pe�a --------------------------------------------
		p.setValue(position.getRow() + 1, position.getColumn());

		// enquanto existir posi�ao e nao tiver pe�as no caminho
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a pe�a pode move at� a posicao
			p.setRow(p.getRow() + 1); // faz o p subir mais uma linha
		}
		// checa se no caminho tem uma pe�a oponente
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // adiciona true na posicao e a pe�a pode move at� a posicao
		}
		// checa posicoes abaixo da pe�a --------------------------------------------

		return mat;
	}

}
