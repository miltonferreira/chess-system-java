package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			UI.printBoard(chessMatch.getPieces());	//mostra o tabuleiro
			
			System.out.println();
			System.out.print("Source: " );
			
			ChessPosition source = UI.readChessPosition(sc);	//recebe a posicao indicada do jogador
			
			System.out.println();			
			System.out.print("Target: ");
			
			ChessPosition target = UI.readChessPosition(sc);	//recebe a posicao indicada do jogador
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target); 	//Move a peça para a posiçao indicada
			
		}
		
	}

}
