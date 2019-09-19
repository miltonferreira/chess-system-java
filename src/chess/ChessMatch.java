package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	//-------------- CORE do jogo - Acontece as interaçoes/ partida de xadrez --------------
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);	//cria o tabuleiro com 8x8 posicoes
		initialSetup();				//posiciona as peças no tabuleiro
	}	
	
	//retorna uma matriz de peças de xadrez da partida
	public ChessPiece[][] getPieces(){
		
		//cria a matriz com as linhas e colunas do tabuleiro
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				//faz um downCasting retornando uma peça de xadrez e nao peça comum
				mat[i][j] = (ChessPiece)board.piece(i, j);	
			}
		}
		return mat;	//retorna a matriz
		
	}
	
	//
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());	//recebe linha/coluna e converte para console/xadrez
	}
	
	//Inicia a partida de xadrez, colocando as peças no tabuleiro
	private void initialSetup() {
		
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));	//torre
		placeNewPiece('e', 8, new King(board, Color.BLACK));	//rei
		placeNewPiece('e', 1, new King(board, Color.WHITE));	//rei
		
	}
	
}
