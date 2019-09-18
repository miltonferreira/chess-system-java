package boardgame;

public class Piece {
	//-------------- Pe�a comum/generica do tabuleiro --------------
	
	protected Position position;	//pega a classe Position
	private Board board;			//tabuleiro do jogo
	
	//no Construtor ele recebe um board, pois a pe�a pertence a um tabuleiro
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	//retorna seu tabuleiro
	protected Board getBoard() {
		return board;
	}
	
	
	
	
}
