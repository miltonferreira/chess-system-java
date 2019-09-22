package boardgame;

public abstract class Piece {
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
	
	//movimentos possiveis de uma pe�a
	public abstract boolean[][] possibleMoves();
	
	//movimentos possiveis de uma pe�a
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	//indica se existe algum movimento possivel
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
}
