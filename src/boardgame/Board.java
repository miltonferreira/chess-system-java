package boardgame;

public class Board {
	// -------------- Tabuleiro do jogo de xadrez --------------

	private int rows; // linhas
	private int columns; // colunas

	private Piece[][] pieces; // Matriz de pe�as da classe Piece

	public Board(int rows, int columns) {
		
		if(rows < 1 || columns < 1) {
			//se passar valor menor que 1 em linha e coluna
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		
		this.rows = rows;
		this.columns = columns;

		pieces = new Piece[rows][columns]; // matriz vai ser instanciada com as linhas/colunas

	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	// Metodo da pe�a recebe uma linha e coluna
	public Piece piece(int row, int column) {
		
		if(!positionExists(row, column)) {
			//se a posicao nao existir
			throw new BoardException("Position not exist on the board");
		}
		
		return pieces[row][column];
	}

	// Metodo da pe�a recebe uma position para pegar
	//linhas e coluna da pe�a
	public Piece piece(Position position) {
		
		if(!positionExists(position)) {
			//se a posicao nao existir
			throw new BoardException("Position not exist on the board");
		}
		
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//na matriz de pe�as do tabuleiro, esse metodo
	//posiciona a pe�a na linha e coluna indicada
	public void placePiece(Piece piece, Position position) {
		
		if(thereIsAPiece(position)) {
			//se existir uma  pe�a na posi�ao indicada 
			throw new BoardException("There is already a piece on position " + position);
		}
		
		//passa a pe�a para a matriz/tabuleiro
		pieces[position.getRow()][position.getColumn()] = piece;
		//pe�a recebe sua posicao em linha e coluna
		piece.position = position;
		
	}
	
	//remove pe�a do tabuleiro
	public Piece removePiece(Position position) {
		
		if(!positionExists(position)) {
			//se a posicao nao existir
			throw new BoardException("Position not exist on the board");
		}
		
		if(piece(position) == null) {
			return null;
		}
		
		Piece aux = piece(position);	//aponta a pe�a no tabuleiro
		aux.position = null;			//a posicao da pe�a fica nulo
		
		pieces[position.getRow()][position.getColumn()] = null;		//posi�ao no tabuleiro fica nulo
		
		return aux;		//retorna a pe�a
		
	}
	
	//checa se posicao existe no tabuleiro em linha/coluna
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	//checa se posicao existe no tabuleiro em posicao
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	//checa se tem uma pe�a na posicao indicada
	public boolean thereIsAPiece(Position position) {
		
		if(!positionExists(position)) {
			//se a posicao nao existir
			throw new BoardException("Position not exist on the board");
		}
		
		return piece(position) != null; //se for diferente de null, tem uma pe�a
	}
	

}
