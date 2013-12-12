
public class Coordinates {
	private int x;
	private int y;
	private int size;
	
	public Coordinates(int x, int y, int size) {
		this.setX(x);
		this.setY(y);
		this.setSize(size);
	}
	
	public Coordinates(int pos, int size) {
		x = pos%size;
		y = pos/size;
		this.setSize(size);
	}
	
	public Coordinates(String str) {
		this.x = -1;
		this.y = -1;
		this.size = 1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Coordinates getLeft() {
		return new Coordinates(this.x - 1, this.y, this.size);
	}
	
	public Coordinates getRight() {
		return new Coordinates(this.x + 1, this.y, this.size);
	}
	
	public Coordinates getUp() {
		return new Coordinates(this.x, this.y - 1, this.size);
	}
	
	public Coordinates getDown() {
		return new Coordinates(this.x, this.y + 1, this.size);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public static boolean inRange(int x, int y, int size) {
		if(x >= 0 && x < size && y >= 0 && y < size)
			return true;
		else return false;
	}
	
	public boolean inRange() {
		if(this.x >= 0 && x < this.size && this.y >= 0 && this.y < this.size)
			return true;
		else return false;
	}
	
	public int toPosition() {
		return (getY() * size + getX());
	}
	
	public void printCoordinates() throws NullPointerException{
		System.out.println("x:" + getX() + " y:" + getY() + " size:" + getSize());
	}
}
