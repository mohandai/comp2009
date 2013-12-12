
public class FillBoard {
	private Board board;
	
	public FillBoard(Board board) {
		// TODO Auto-generated constructor stub
		this.board = board;
		//board.printBoard();
	}

	public synchronized Board execute() throws ConflictException {
		// TODO
		int n = board.getNumbers();
		int changeMaked = 1;
		
		while(changeMaked != 0){
			changeMaked = 0;
			for(int i = 0; i < n; i++) {
				if(board.getColor(i) != board.NOTHING) {
					changeMaked += applyRule(i);
				}
			}
		}
		//board.printBoard();
		return board;
	}

	private synchronized int applyRule(int i) throws ConflictException {
		// TODO Auto-generated method stub
		Coordinates c = new Coordinates(i, board.getSize());
		int color = board.getColor(i);
		int inverseColor = Board.InverseColor(color);
		int size = board.getSize();
		int changed = 0;
		
		Coordinates up = (c.getUp().inRange()) ? (c.getUp()): (new Coordinates("null"));
		Coordinates up2 = (c.getUp().getUp().inRange()) ? (c.getUp().getUp()): (new Coordinates("null"));
		Coordinates left = (c.getLeft().inRange()) ? (c.getLeft()): (new Coordinates("null"));
		Coordinates left2 = (c.getLeft().getLeft().inRange()) ? (c.getLeft().getLeft()): (new Coordinates("null"));
		Coordinates right = (c.getRight().inRange()) ? (c.getRight()): (new Coordinates("null"));
		Coordinates right2 = (c.getRight().getRight().inRange()) ? (c.getRight().getRight()): (new Coordinates("null"));
		Coordinates down = (c.getDown().inRange()) ? (c.getDown()): (new Coordinates("null"));
		Coordinates down2 = (c.getDown().getDown().inRange()) ? (c.getDown().getDown()): (new Coordinates("null"));
		
		//check with up2
		if(checkGap(c, up2, up, inverseColor))
			changed++;
		//check with up
		if(checkAjacent(c, up, up2, down, inverseColor))
			changed++;
		//check with left2
		if(checkGap(c, left2, left, inverseColor))
			changed++;
		//check with left
		if(checkAjacent(c, left, left2, right, inverseColor))
			changed++;
		//check with right2
		if(checkGap(c, right2, right, inverseColor))
			changed++;
		//check with right
		if(checkAjacent(c, right, right2, left, inverseColor))
			changed++;
		//check with down2
		if(checkGap(c, down2, down, inverseColor))
			changed++;
		//check with down
		if(checkAjacent(c, down, down2, up, inverseColor))
			changed++;
		
		
		
		
		
		/*if((up2 != null) && board.sameColor(i, up2)) {
			
		}*/
		/*try{
			c.printCoordinates();
			up.printCoordinates();
			up2.printCoordinates();	
		}
		catch(NullPointerException ex) {
			System.out.println("null");
		}*/
		
		return changed;
	}
	
	public synchronized boolean checkGap(Coordinates c, Coordinates g, Coordinates m, int inverseColor) throws ConflictException {
		if(g.inRange() && board.sameColor(c.toPosition(), g.toPosition())) {
			if(board.sameColor(c.toPosition(), m.toPosition()))
				throw new ConflictException("conflict color");
			else if(board.getColor(m.toPosition()) == Board.NOTHING) {
				board.addPebble(m.toPosition(),inverseColor);
				//System.out.println("add one");								//test
				return true;
			}
			else return false;
		}
		return false;
	}
	
	public synchronized boolean checkAjacent(Coordinates c, Coordinates a, Coordinates m, Coordinates n, int inverseColor) throws ConflictException {
		if(a.inRange() && board.sameColor(c.toPosition(), a.toPosition())) {
			if(m.inRange()) {
				if(board.sameColor(c.toPosition(), m.toPosition()))
					throw new ConflictException("conflict color");
				else if(board.getColor(m.toPosition()) == Board.NOTHING) {
					board.addPebble(m.toPosition(),inverseColor);
				}
			}
			if(n.inRange()) {
				if(board.sameColor(c.toPosition(), n.toPosition()))
					throw new ConflictException("conflict color");
				else if(board.getColor(n.toPosition()) == Board.NOTHING) {
					board.addPebble(n.toPosition(),inverseColor);
				}
			}
		}
		return false;
	}
}
