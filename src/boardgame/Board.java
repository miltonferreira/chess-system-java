package boardgame;

public class Board {
	// -------------- Tabuleiro do jogo de xadrez --------------

	private int rows; // linhas
	private int columns; // colunas

	private Piece[][] pieces; // Matriz de peças da classe Piece

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

	// Metodo da peça recebe uma linha e coluna
	public Piece piece(int row, int column) {
		
		if(!positionExists(row, column)) {
			//se a posicao nao existir
			throw new BoardException("Position not exist on the board");
		}
		
		return pieces[row][column];
	}

	// Metodo da peça recebe uma position para pegar
	//linhas e coluna da peça
	public Piece piece(Position position) {
		
		if(!positionExists(position)) {
			//se a posicao nao existir
			throw new BoardException("Position not exist on the board");
		}
		
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//na matriz de peças do tabuleiro, esse metodo
	//posiciona a peça na linha e coluna indicada
	public void placePiece(Piece piece, Position position) {
		
		if(thereIsAPiece(position)) {
			//se existir uma  peça na posiçao indicada 
			throw new BoardException("There is already a piece on position " + position);
		}
		
		//passa a peça para a matriz/tabuleiro
		pieces[position.getRow()][position.getColumn()] = piece;
		//peça recebe sua posicao em linha e coluna
		piece.position = position;
		
	}
	
	//remove peça do tabuleiro
	public Piece removePiece(Position position) {
		
		if(!positionExists(position)) {
			//se a posicao nao existir
			throw new BoardException("Position not exist on the board");
		}
		
		if(piece(position) == null) {
			return null;
		}
		
		Piece aux = piece(position);	//aponta a peça no tabuleiro
		aux.position = null;			//a posicao da peça fica nulo
		
		pieces[position.getRow()][position.getColumn()] = null;		//posiçao no tabuleiro fica nulo
		
		return aux;		//retorna a peça
		
	}
	
	//checa se posicao existe no tabuleiro em linha/coluna
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	//checa se posicao existe no tabuleiro em posicao
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	//checa se tem uma peça na posicao indicada
	public boolean thereIsAPiece(Position position) {
		
		if(!positionExists(position)) {
			//se a posicao nao existir
			throw new BoardException("Position not exist on the board");
		}
		
		return piece(position) != null; //se for diferente de null, tem uma peça
	}
	

}
