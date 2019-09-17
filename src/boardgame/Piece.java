package boardgame;

public class Piece {
	//-------------- Pe�a do jogo de xadrez --------------
	
	protected Position position;	//pega a classe Position
	private Board board;			//tabuleiro do jogo
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
	
	
	
}
