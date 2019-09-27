package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<ChessPiece>();	//lista de peças capturadas
		
		// enquanto nao estiver em check mate, o jogo roda
		while(!chessMatch.getCheckMate()) {
			
			try {
				
				UI.clearScreen();			//limpa a tela
				UI.printMatch(chessMatch, captured);	//mostra partida
				
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
				
				//Se alguma peça for capturada, adiciona ela a lista
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
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
		
		UI.clearScreen();
		UI.printMatch(chessMatch, captured); // finaliza a partida
		
	}

}
