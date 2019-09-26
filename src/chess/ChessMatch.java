package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	// -------------- CORE do jogo - Acontece as intera�oes/ partida de xadrez
	// --------------
	
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();	// pe�as no tabuleiro
	private List<Piece> capturedPieces = new ArrayList<>();		// pe�as capturadas

	public ChessMatch() {
		
		board = new Board(8, 8); 		// cria o tabuleiro com 8x8 posicoes
		turn = 1;						// turno 1
		currentPlayer = Color.WHITE;	//pe�as brancas come�am primeiro
		
		initialSetup(); 				// posiciona as pe�as no tabuleiro
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
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
		
		Position source = sourcePosition.toPosition();	//recebe a posicao da pe�a
		Position target = targetPosition.toPosition();	//posicao que deseja mover a pe�a
		
		validateSourcePosition(source);					//valida se existe a posicao indicada
		
		validateTargetPosition(source, target);			//valida se pode ir para a posicao
		
		Piece capturedPiece = makeMove(source, target);
		nextTurn();										//chama proximo turno
		return (ChessPiece)capturedPiece;				// faz um downcast pois � tipo Piece, e nao uma pe�a do xadrez
		
	}
	
	//movimento da pe�a no tabuleiro
	private Piece makeMove(Position source, Position target) {
		
		Piece p = board.removePiece(source);				//retira a pe�a na origem dela
		Piece capturedPiece = board.removePiece(target);	//remove a pe�a que estiver no caminha da outra pe�a
		
		board.placePiece(p, target); 						//coloca a pe�a na nova posi�ao
		
		//caso nao for null,  significa que houve pe�a capturada
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);			//remove da lista pe�a capturada
			capturedPieces.add(capturedPiece);				//adiciona pe�a capturada a lista
		}
		
		return capturedPiece;								//retorna a pe�a capturada
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

	//Posiciona pe�a na linha/coluna indicada
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); 	// recebe linha/coluna e converte para console/xadrez
		piecesOnTheBoard.add(piece);											// adiciona a pe�a na lista
	}

	// Inicia a partida de xadrez, colocando as pe�as no tabuleiro
	private void initialSetup() {

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

	}

}
