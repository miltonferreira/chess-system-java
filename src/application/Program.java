package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			
			try {
				UI.clearScreen();						//limpa a tela
				UI.printBoard(chessMatch.getPieces());	//mostra o tabuleiro
				
				System.out.println();
				System.out.print("Source: " );
				
				ChessPosition source = UI.readChessPosition(sc);				//recebe a posicao indicada do jogador
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);	//checa posicoes para movimentar a peça no tabuleiro
				
				UI.clearScreen();												//limpa a tela
				UI.printBoard(chessMatch.getPieces(), possibleMoves);			//Mostra o tabuleiro, colorindo para onde a peça pode se mover
				
				System.out.println();			
				System.out.print("Target: ");
				
				ChessPosition target = UI.readChessPosition(sc);	//recebe a posicao indicada do jogador
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target); 	//Move a peça para a posiçao indicada
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
	}

}
