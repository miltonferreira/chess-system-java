package application;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// limpa a tela
	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}

	// le a posicao da peça no tabuleiro
	public static ChessPosition readChessPosition(Scanner sc) {

		try {

			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1)); // recorta a string na posicao 1, onde fica o numero

			return new ChessPosition(column, row); // retorna coluna e linha
		} catch (RuntimeException e) {

			throw new InputMismatchException("Error reading ChessPosition. " + "Valid values are from a1 to h8");

		}

	}

	// recebe uma matriz de peças
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false); // imprime a peça na posicao sem fundo azul
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		
		printBoard(chessMatch.getPieces());										// imprime o tabuleiro
		System.out.println();
		printCapturedPieces(captured);											// mostra as peças capturadas
		System.out.println();
		System.out.println("Turn : " + chessMatch.getTurn());					// mostra turno
		
		// se nao estiver em check mate
		if(!chessMatch.getCheckMate()) {
			System.out.println("Waiting player: " + chessMatch.getCurrentPlayer()); // mostra a cor da peça em jogo
			if (chessMatch.getCheck()) {
				System.out.println("CHECK!"); // avisa que jogador está em cheque
			}
		}else{
			System.out.println("CHECKMATE!");
			System.out.println("Winner: " + chessMatch.getCurrentPlayer());
		}
		
	}

	// recebe uma matriz de peças com movimentos possiveis da peça através da matriz
	// booleana
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]); // imprime a peça na posicao com fundo azul
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	// imprime uma unica peça na tela
	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	// imprime lista de peças capturadas
	private static void printCapturedPieces(List<ChessPiece> captured) {

		// filtra para pegar somente peças brancas
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList());

		// filtra para pegar somente peças pretas
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK)
				.collect(Collectors.toList());

		System.out.println("Captured pieces");

		// peças brancas capturadas ------------------------------
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.println(ANSI_RESET);

		// peças pretas capturadas ------------------------------
		System.out.print("Black: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.println(ANSI_RESET);

	}

}
