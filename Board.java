


public class Board {
	
	private int size;
	private int[] board;
	private int num;
	
	public static final int NOTHING = 0;
	public static final int BLACK = 1;
	public static final int WHITE = 2;
	
	private Board() {
	}
	
	public Board(InfoPack info) throws ConflictException {
		this.size = info.getSize();
		this.num = (size * size);
		board = new int[size * size];
		for(int u : board)
			u = NOTHING;
		initialize(info);
	}
	
	private void initialize(InfoPack info) throws ConflictException {
		for(MyQueue<Integer> q = info.getWhites(); q.getSize() > 0;) {
			int i = q.dequeue();
			addWhite(i - 1);
		}
		
		for(MyQueue<Integer> q = info.getBlacks(); q.getSize() > 0;) {
			int i = q.dequeue();
			addBlack(i - 1);
		}
	}
	
	public int getNumbers() {
		return num;
	}

	public int getColor(int position) {
		return board[position];
	}
	
	public void setBlack(int position){
		board[position] = BLACK;
	}
	
	public void setWhite(int position){
		board[position] = WHITE;
	}
	
	public void addPebble(int position, int color) throws ConflictException {
		if(inRange(position) && board[position] == NOTHING)
			board[position] = color;
		else throw new ConflictException("Invalid Position " + position);
	}
	
	public void addBlack(int position) throws ConflictException {
		if(inRange(position) && board[position] == NOTHING)
			board[position] = BLACK;
		else throw new ConflictException("Invalid Position" + position);
	}
	
	public void addWhite(int position) throws ConflictException {
		if(inRange(position) && board[position] == NOTHING)
			board[position] = WHITE;
		else throw new ConflictException("Invalid Position" + position);
	}

	public boolean inRange(int position) {
		if (position < size || position >= 0)
			return true;
		else return false;
	}
	
	public int[] getBoard() {
		return this.board;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void printBoard() {
		for(int i = 0; i < board.length;) {
			for(int j = 0; j < size; i++,j++){
				if(board[i] == BLACK)
					System.out.print(" B ");
				else if(board[i] == WHITE)
					System.out.print(" W ");
				else
					System.out.print(" O ");
					
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void setBoard(int[] set) {
		this.board = set;
	}
	
	private void setSize(int set) {
		this.size = set;
	}
	
	public Board clone() {
		Board clone = new Board();
		clone.setBoard(this.getBoard());
		clone.setSize(this.getSize());
		clone.setNumbers(this.getNumbers());
		return clone;
	}
	
	public boolean sameColor(int posA, int posB) {
		if(getColor(posA) == getColor(posB))
			return true;
		else return false;
	}	
	
	private void setNumbers(int set) {
		this.num = set;
	}
	
	public static int InverseColor(int color) {
		if(color == BLACK)
			return WHITE;
		else if(color == WHITE)
			return BLACK;
		else return NOTHING;
	}
	
	public boolean isEmpty() {
		//TODO
		return false;
	}
	
}
