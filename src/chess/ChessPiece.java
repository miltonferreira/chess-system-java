package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
	//-------------- Pe�a do jogo de xadrez --------------
	
	private Color color;	// cor da pe�a
	private int moveCount;	// contador de movimento da pe�a
	
	// no construtor recebe o tabuleiro e a cor
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	// retorna a cor da pe�a
	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;	// incrementa +1
	}
	
	public void decreaseMoveCount() {
		moveCount--;	// decrementa -1
	}
	
	// retorna a posicao da pe�a no xadrez 
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);	// converte posicoes indicadas da matriz para o console/xadrez 
	}
	
	// checa se a pe�a � oponente
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);	// pega uma pe�a na posi�ao indicada
		return p != null && p.getColor() != color;				// checa se existe a pe�a e ela de outra cor
	}
	
}
