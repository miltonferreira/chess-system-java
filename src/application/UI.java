package application;

import chess.ChessPiece;

public class UI {
	
	//recebe uma matriz de pe�as
	public static void printBoard(ChessPiece[][] pieces) {
		for(int i=0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for(int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]);	//imprime a pe�a na posicao
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	//imprime uma unica pe�a na tela
	private static void printPiece(ChessPiece piece) {
		if(piece == null) {
			System.out.print("-");
		}else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
	
}
