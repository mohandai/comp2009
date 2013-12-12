

public class InfoPack {
	private int size;
	private MyQueue<Integer> whites;
	private MyQueue<Integer> blacks;
	
	public InfoPack() {
		size = 0;
		whites = new MyQueue<Integer>();
		blacks = new MyQueue<Integer>();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public MyQueue<Integer> getWhites() {
		return this.whites;
	}

	public void setWhites(MyQueue<Integer> whites) {
		this.whites = whites;
	}

	public MyQueue<Integer> getBlacks() {
		return this.blacks;
	}

	public void setBlacks(MyQueue<Integer> blacks) {
		this.blacks = blacks;
	}
	
	public void addPebble(int position,int color) {
		if(color == Board.BLACK)
			blacks.enqueue(position);
		else whites.enqueue(position);
	}
}
