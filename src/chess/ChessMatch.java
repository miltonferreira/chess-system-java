package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	// -------------- CORE do jogo - Acontece as intera�oes/ partida de xadrez
	// --------------
	
	
	private int turn;
	private Color currentPlayer;	// indica se � branca ou preta a pe�a
	private Board board;
	private boolean check;			// indica se o rei est� em check
	private boolean checkMate;		// indica se rei tomou check mate
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();	// pe�as no tabuleiro
	private List<Piece> capturedPieces = new ArrayList<>();		// pe�as capturadas

	public ChessMatch() {
		
		board = new Board(8, 8); 		// cria o tabuleiro com 8x8 posicoes
		turn = 1;						// turno 1
		currentPlayer = Color.WHITE;	// pe�as brancas come�am primeiro
		
		initialSetup(); 				// posiciona as pe�as no tabuleiro
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	// retorna uma matriz de pe�as de xadrez da partida
	public ChessPiece[][] getPieces() {

		// cria a matriz com as linhas e colunas do tabuleiro
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// faz um downCasting retornando uma pe�a de xadrez e nao pe�a comum
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat; // retorna a matriz

	}
	
	//movimentos possiveis pe�a
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		
		Position position = sourcePosition.toPosition();	//converte para posicao da matriz do xadrez
		validateSourcePosition(position);	//checa se a posicao � valida
		
		return board.piece(position).possibleMoves();	//retorna a matriz com movimentos possiveis
	}
	
	// faz a jogada e movimenta a pe�a no tabuleiro
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		
		Position source = sourcePosition.toPosition();	// recebe a posicao da pe�a
		Position target = targetPosition.toPosition();	// posicao que deseja mover a pe�a
		
		validateSourcePosition(source);					// valida se existe a posicao indicada
		
		validateTargetPosition(source, target);			// valida se pode ir para a posicao
		
		Piece capturedPiece = makeMove(source, target);
		
		// checa se jogar se colocou em cheque
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);	// desfaz o movimento do jogador
			throw new ChessException("You can't put yourself in check");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;	// check se o oponente ficou em cheque
		
		// checa se o oponente ficou em check mate
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}else{
			nextTurn();										// chama proximo turno se nao houve check mate	
		}
		
		
		return (ChessPiece)capturedPiece;				// faz um downcast pois � tipo Piece, e nao uma pe�a do xadrez
		
	}
	
	// movimento da pe�a no tabuleiro
	private Piece makeMove(Position source, Position target) {
		
		ChessPiece p = (ChessPiece)board.removePiece(source);				// retira a pe�a na origem dela
		p.increaseMoveCount(); 								// +1 ao movimento da pe�a
		Piece capturedPiece = board.removePiece(target);	// remove a pe�a que estiver no caminha da outra pe�a
		
		board.placePiece(p, target); 						// coloca a pe�a na nova posi�ao
		
		//caso nao for null,  significa que houve pe�a capturada
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);			// remove da lista pe�a capturada
			capturedPieces.add(capturedPiece);				// adiciona pe�a capturada a lista
		}
		
		return capturedPiece;								// retorna a pe�a capturada
	}
	
	// desfaz movimento da pe�a
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		
		ChessPiece p = (ChessPiece)board.removePiece(target);		// pega a pe�a removida do tabuleiro
		p.decreaseMoveCount(); 						// -1 no movimento da pe�a
		board.placePiece(p, source);				// coloca na posicao de origem a pe�a que se moveu
		
		// se houve pe�a capturada, coloca em sua posi�ao de origem tamb�m
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);	// tira da lista de capturadas
			piecesOnTheBoard.add(capturedPiece);	// coloca novamente no tabuleiro
		}
		
	}

	//valida se existe a posicao indicada
	private void validateSourcePosition(Position position) {
		
		//se nao existir uma pe�a na posicao indicada
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is not a piece on source position.");
		}
		
		//se o jogador pegar uma pe�a que nao seja do turno, lan�a exce�ao
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		
		//se nao existe movimentos possiveis para a pe�a
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is not possible moves for the chess piece.");
		}
		
	}
	
	//valida se pode ir para a posicao de destino
	private void validateTargetPosition(Position source, Position target) {
		
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chess piece can't move to target position.");
		}
	}
	
	// vai para o proximo turno
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;	// expressao ternaria, se for white troca para black, se nao fica white
	}
	
	// returna cor do oponente
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	// procura o rei nas listas
	private ChessPiece king(Color color) {
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		
		// percorre a lista atr�s do rei
		for(Piece p : list) {
			if(p instanceof King) {
				return (ChessPiece) p;
			}
		}
		// se nao encontrar lan�a uma exce�ao
		throw new IllegalStateException("There is not " + color + " king on the board");
	}
	
	// checa se o rei est� em cheque
	private boolean testCheck(Color color) {
		
		Position kingPosition = king(color).getChessPosition().toPosition();	// pega a posicao em forma de matriz
		
		// lista de pe�as do oponente
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		
		for(Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;	// se retornar true o rei est� em cheque
			}
		}
		return false;
	}
	
	// checa se o rei tomou check mate
	private boolean testCheckMate(Color color) {
		
		if(!testCheck(color)) {
			return false;	//se nao tiver em check retorna false
		}
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		
		// checa se alguma pe�a pode tirar o rei do check
		for(Piece p : list) {
			
			boolean[][] mat = p.possibleMoves();
			for(int i = 0; i < board.getRows(); i++) {
				for(int j = 0; j < board.getColumns(); j++) {
					// testa se esse movimento tira o rei do check
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition(); // pega a posi�ao do p
						Position target = new Position(i, j);				// cria um novo position com a posicao que tira o rei do check
						Piece capturedPiece = makeMove(source, target);		// movimenta a pe�a
						boolean testCheck = testCheck(color);				// verifica se o rei ainda est� em check
						undoMove(source, target, capturedPiece); 			// desfaz o movimento no final
						if(!testCheck) {
							return false;	// retorna que o rei na estava em check mate
						}
					}
				}
			}
			
		}
		return true;	// indica que o rei tomou check mate
	}
	
	// Posiciona pe�a na linha/coluna indicada
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); 	// recebe linha/coluna e converte para console/xadrez
		piecesOnTheBoard.add(piece);											// adiciona a pe�a na lista
	}

	// Inicia a partida de xadrez, colocando as pe�as no tabuleiro
	private void initialSetup() {
		
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		
		placeNewPiece('b', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 8, new King(board, Color.BLACK));
		
		/*
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
		*/
	}

}
