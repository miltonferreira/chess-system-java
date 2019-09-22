package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	// -------------- CORE do jogo - Acontece as interaçoes/ partida de xadrez
	// --------------

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8); // cria o tabuleiro com 8x8 posicoes
		initialSetup(); // posiciona as peças no tabuleiro
	}

	// retorna uma matriz de peças de xadrez da partida
	public ChessPiece[][] getPieces() {

		// cria a matriz com as linhas e colunas do tabuleiro
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// faz um downCasting retornando uma peça de xadrez e nao peça comum
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat; // retorna a matriz

	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		
		Position source = sourcePosition.toPosition();	//recebe a posicao da peça
		Position target = targetPosition.toPosition();	//posicao que deseja mover a peça
		
		validateSourcePosition(source);		//valida se existe a posicao indicada
		
		Piece capturedPiece = makeMove(source, target);
		
		return (ChessPiece)capturedPiece;	// faz um downcast pois é tipo Piece, e nao uma peça do xadrez
		
	}
	
	//
	private Piece makeMove(Position source, Position target) {
		
		Piece p = board.removePiece(source);				//retira a peça na origem dela
		Piece capturedPiece = board.removePiece(target);	//remove a peça que estiver no caminha da outra peça
		
		board.placePiece(p, target); 	//coloca a peça na nova posiçao
		
		return capturedPiece;			//retorna a peça capturada
	}

	//valida se existe a posicao indicada
	private void validateSourcePosition(Position position) {
		
		//se nao existir uma peça na posicao indicada
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is not a piece on source position.");
		}
		
		//se nao existe movimentos possiveis para a peça
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is not possible moves for the chess piece.");
		}
		
	}

	//
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); // recebe linha/coluna e converte para
																				// console/xadrez
	}

	// Inicia a partida de xadrez, colocando as peças no tabuleiro
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
