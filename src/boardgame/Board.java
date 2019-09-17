package boardgame;

public class Board {
	//-------------- Tabuleiro do jogo de xadrez --------------

	private int rows;				//linhas
	private int columns;			//colunas
	
	private Piece[][] pieces;		//Matriz de peças

	public Board(int rows, int columns) {
		
		this.rows = rows;
		this.columns = columns;
		
		pieces = new Piece[rows][columns];	//matriz vai ser instanciada com as linhas/colunas
		
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
	
}
