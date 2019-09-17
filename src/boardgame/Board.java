package boardgame;

public class Board {
	// -------------- Tabuleiro do jogo de xadrez --------------

	private int rows; // linhas
	private int columns; // colunas

	private Piece[][] pieces; // Matriz de pe�as

	public Board(int rows, int columns) {

		this.rows = rows;
		this.columns = columns;

		pieces = new Piece[rows][columns]; // matriz vai ser instanciada com as linhas/colunas

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

	// Metodo da pe�a recebe uma linha e coluna
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}

	// Metodo da pe�a recebe uma position para pegar
	//linhas e coluna da pe�a
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}

}
