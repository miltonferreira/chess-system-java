package boardgame;

public class Board {
	// -------------- Tabuleiro do jogo de xadrez --------------

	private int rows; // linhas
	private int columns; // colunas

	private Piece[][] pieces; // Matriz de peças da classe Piece

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

	// Metodo da peça recebe uma linha e coluna
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}

	// Metodo da peça recebe uma position para pegar
	//linhas e coluna da peça
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//na matriz de peças do tabuleiro, esse metodo
	//posiciona a peça na linha e coluna indicada
	public void placePiece(Piece piece, Position position) {
		
		//passa a peça para a matriz/tabuleiro
		pieces[position.getRow()][position.getColumn()] = piece;
		//peça recebe sua posicao em linha e coluna
		piece.position = position;
		
	}
	

}
