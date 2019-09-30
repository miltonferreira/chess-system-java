package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {
	// -------------- CORE do jogo - Acontece as interaçoes/ partida de xadrez
	// --------------

	private int turn;
	private Color currentPlayer; // indica se é branca ou preta a peça
	private Board board;
	private boolean check; // indica se o rei está em check
	private boolean checkMate; // indica se rei tomou check mate
	private ChessPiece enPassantVulnerable; // movimento especial do peao

	private List<Piece> piecesOnTheBoard = new ArrayList<>(); // peças no tabuleiro
	private List<Piece> capturedPieces = new ArrayList<>(); // peças capturadas

	public ChessMatch() {

		board = new Board(8, 8); // cria o tabuleiro com 8x8 posicoes
		turn = 1; // turno 1
		currentPlayer = Color.WHITE; // peças brancas começam primeiro

		initialSetup(); // posiciona as peças no tabuleiro
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

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
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

	// movimentos possiveis peça
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {

		Position position = sourcePosition.toPosition(); // converte para posicao da matriz do xadrez
		validateSourcePosition(position); // checa se a posicao é valida

		return board.piece(position).possibleMoves(); // retorna a matriz com movimentos possiveis
	}

	// faz a jogada e movimenta a peça no tabuleiro
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {

		Position source = sourcePosition.toPosition(); // recebe a posicao da peça
		Position target = targetPosition.toPosition(); // posicao que deseja mover a peça

		validateSourcePosition(source); // valida se existe a posicao indicada

		validateTargetPosition(source, target); // valida se pode ir para a posicao

		Piece capturedPiece = makeMove(source, target);

		// checa se jogar se colocou em cheque
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece); // desfaz o movimento do jogador
			throw new ChessException("You can't put yourself in check");
		}

		ChessPiece movedPiece = (ChessPiece) board.piece(target); // pega uma referencia do destino da peça

		check = (testCheck(opponent(currentPlayer))) ? true : false; // check se o oponente ficou em cheque

		// checa se o oponente ficou em check mate
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		} else {
			nextTurn(); // chama proximo turno se nao houve check mate
		}

		// se foi um peao que movimento 2 casas, ele pode tomar en passant
		if (movedPiece instanceof Pawn
				&& (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
			enPassantVulnerable = movedPiece;
		} else {
			enPassantVulnerable = null;
		}

		return (ChessPiece) capturedPiece; // faz um downcast pois é tipo Piece, e nao uma peça do xadrez

	}

	// movimento da peça no tabuleiro
	private Piece makeMove(Position source, Position target) {

		ChessPiece p = (ChessPiece) board.removePiece(source); // retira a peça na origem dela
		p.increaseMoveCount(); // +1 ao movimento da peça
		Piece capturedPiece = board.removePiece(target); // remove a peça que estiver no caminha da outra peça

		board.placePiece(p, target); // coloca a peça na nova posiçao

		// caso nao for null, significa que houve peça capturada
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece); // remove da lista peça capturada
			capturedPieces.add(capturedPiece); // adiciona peça capturada a lista
		}

		// movimento especial do roque pequeno para a direita
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {

			Position sourceT = new Position(source.getRow(), source.getColumn() + 3); // pega a posicao da torre
			Position targetT = new Position(source.getRow(), source.getColumn() + 1); // pega a posicao do rei +1

			ChessPiece rook = (ChessPiece) board.removePiece(sourceT); // retira a torre da posicao
			board.placePiece(rook, targetT); // coloca a torre na nova posicao
			rook.increaseMoveCount(); // incrementa +1 movimento

		}

		// movimento especial do roque grande para a esquerda
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {

			Position sourceT = new Position(source.getRow(), source.getColumn() - 4); // pega a posicao da torre
			Position targetT = new Position(source.getRow(), source.getColumn() - 1); // pega a posicao do rei +1

			ChessPiece rook = (ChessPiece) board.removePiece(sourceT); // retira a torre da posicao
			board.placePiece(rook, targetT); // coloca a torre na nova posicao
			rook.increaseMoveCount(); // incrementa +1 movimento

		}

		// movimento especial en passant do peao
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == null) {

				Position pawnPosition;

				if (p.getColor() == Color.WHITE) {
					pawnPosition = new Position(target.getRow() + 1, target.getColumn()); // posicao da peça que vai ser
																							// capturada
				} else {
					pawnPosition = new Position(target.getRow() - 1, target.getColumn()); // posicao da peça que vai ser
																							// capturada
				}

				capturedPiece = board.removePiece(pawnPosition); // remove a peça capturada do tabuleiro
				capturedPieces.add(capturedPiece); // adiciona a peça capturada a lista
				piecesOnTheBoard.remove(capturedPiece); // remove a peça da lista de peças do tabuleiro
			}
		}

		return capturedPiece; // retorna a peça capturada
	}

	// desfaz movimento da peça
	private void undoMove(Position source, Position target, Piece capturedPiece) {

		ChessPiece p = (ChessPiece) board.removePiece(target); // pega a peça removida do tabuleiro
		p.decreaseMoveCount(); // -1 no movimento da peça
		board.placePiece(p, source); // coloca na posicao de origem a peça que se moveu

		// se houve peça capturada, coloca em sua posiçao de origem também
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece); // tira da lista de capturadas
			piecesOnTheBoard.add(capturedPiece); // coloca novamente no tabuleiro
		}

		// movimento especial do roque pequeno para a direita
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {

			Position sourceT = new Position(source.getRow(), source.getColumn() + 3); // pega a posicao da torre
			Position targetT = new Position(source.getRow(), source.getColumn() + 1); // pega a posicao do rei +1

			ChessPiece rook = (ChessPiece) board.removePiece(targetT); // retira a torre da posicao
			board.placePiece(rook, sourceT); // coloca a torre na nova posicao
			rook.decreaseMoveCount(); // decrementa -1 movimento

		}

		// movimento especial do roque grande para a esquerda
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {

			Position sourceT = new Position(source.getRow(), source.getColumn() - 4); // pega a posicao da torre
			Position targetT = new Position(source.getRow(), source.getColumn() - 1); // pega a posicao do rei +1

			ChessPiece rook = (ChessPiece) board.removePiece(targetT); // retira a torre da posicao
			board.placePiece(rook, sourceT); // coloca a torre na nova posicao
			rook.decreaseMoveCount(); // decrementa -1 movimento

		}

		// movimento especial en passant do peao
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
				
				ChessPiece pawn = (ChessPiece)board.removePiece(target);	//pega peao que está na posicao errada, uma casa atrás
				Position pawnPosition;

				if (p.getColor() == Color.WHITE) {
					pawnPosition = new Position(3, target.getColumn()); // posicao da peça anterior a jogada
				} else {
					pawnPosition = new Position(4, target.getColumn()); // posicao da peça anterior a jogada
				}
				
				board.placePiece(pawn, pawnPosition); // posiciona a peça na casa anterior a jogada
				
			}
		}

	}

	// valida se existe a posicao indicada
	private void validateSourcePosition(Position position) {

		// se nao existir uma peça na posicao indicada
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is not a piece on source position.");
		}

		// se o jogador pegar uma peça que nao seja do turno, lança exceçao
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}

		// se nao existe movimentos possiveis para a peça
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is not possible moves for the chess piece.");
		}

	}

	// valida se pode ir para a posicao de destino
	private void validateTargetPosition(Position source, Position target) {

		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chess piece can't move to target position.");
		}
	}

	// vai para o proximo turno
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; // expressao ternaria, se for white
																					// troca para black, se nao fica
																					// white
	}

	// returna cor do oponente
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// procura o rei nas listas
	private ChessPiece king(Color color) {

		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());

		// percorre a lista atrás do rei
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		// se nao encontrar lança uma exceçao
		throw new IllegalStateException("There is not " + color + " king on the board");
	}

	// checa se o rei está em cheque
	private boolean testCheck(Color color) {

		Position kingPosition = king(color).getChessPosition().toPosition(); // pega a posicao em forma de matriz

		// lista de peças do oponente
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());

		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();

			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true; // se retornar true o rei está em cheque
			}
		}
		return false;
	}

	// checa se o rei tomou check mate
	private boolean testCheckMate(Color color) {

		if (!testCheck(color)) {
			return false; // se nao tiver em check retorna false
		}

		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());

		// checa se alguma peça pode tirar o rei do check
		for (Piece p : list) {

			boolean[][] mat = p.possibleMoves();
			for (int i = 0; i < board.getRows(); i++) {
				for (int j = 0; j < board.getColumns(); j++) {
					// testa se esse movimento tira o rei do check
					if (mat[i][j]) {
						Position source = ((ChessPiece) p).getChessPosition().toPosition(); // pega a posiçao do p
						Position target = new Position(i, j); // cria um novo position com a posicao que tira o rei do
																// check
						Piece capturedPiece = makeMove(source, target); // movimenta a peça
						boolean testCheck = testCheck(color); // verifica se o rei ainda está em check
						undoMove(source, target, capturedPiece); // desfaz o movimento no final
						if (!testCheck) {
							return false; // retorna que o rei na estava em check mate
						}
					}
				}
			}

		}
		return true; // indica que o rei tomou check mate
	}

	// Posiciona peça na linha/coluna indicada
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); // recebe linha/coluna e converte para
																				// console/xadrez
		piecesOnTheBoard.add(piece); // adiciona a peça na lista
	}

	// Inicia a partida de xadrez, colocando as peças no tabuleiro
	private void initialSetup() {

		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE, this));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));

	}

}
