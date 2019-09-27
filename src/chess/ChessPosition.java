package chess;

import boardgame.Position;

public class ChessPosition {
	
	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		
		//logica de posicionamento do tabuleiro
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating ChessPosition. "
					+ "Valid values are from a1 to h8.");
		}
		
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	
	//Converte posicoes indicadas do console/xadrez para a matriz
	protected Position toPosition() {
		return new Position(8 - row,  column - 'a');
	}
	
	//Converte posicoes indicadas da matriz para o console/xadrez
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());
	}

	@Override
	public String toString() {
		return "" + column + row;
	}
	
}
