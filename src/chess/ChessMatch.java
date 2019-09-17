package chess;

import boardgame.Board;

public class ChessMatch {
	//-------------- CORE do jogo - Acontece as interaçoes/ partida de xadrez --------------
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);	//cria o tabuleiro com 8x8 posicoes
	}
	
	//retorna uma matriz de peças de xadrez da partida
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				//faz um downCasting retornando uma peça de xadrez e nao peça comum
				mat[i][j] = (ChessPiece)board.piece(i, j);	
			}
		}
		return mat;	//retorna a matriz
		
	}
	
}
