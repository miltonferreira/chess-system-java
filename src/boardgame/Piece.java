package boardgame;

public class Piece {
	//-------------- Posicionamento da Peça tabuleiro --------------
	
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
