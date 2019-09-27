package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
	//-------------- Peça do jogo de xadrez --------------
	
	private Color color;	//cor da peça
	
	// no construtor recebe o tabuleiro e a cor
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	// retorna a cor da peça
	public Color getColor() {
		return color;
	}
	
	// retorna a posicao da peça no xadrez 
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);	// converte posicoes indicadas da matriz para o console/xadrez 
	}
	
	// checa se a peça é oponente
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);	// pega uma peça na posiçao indicada
		return p != null && p.getColor() != color;				// checa se existe a peça e ela de outra cor
	}
	
}
