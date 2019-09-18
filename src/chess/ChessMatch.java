package chess;

import boardgame.Board;
import boardgame.Position;
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
	
	//Inicia a partida de xadrez, colocando as peças no tabuleiro
	private void initialSetup() {
		
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));	//torre
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));	//rei
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));	//rei
		
	}
	
}
