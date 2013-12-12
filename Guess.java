import java.util.LinkedList;


public class Guess {
	private Board board;
	//private ListStack l = new ListStack();

	public Guess(Board b) {
		// TODO Auto-generated constructor stub
		this.board = b;
	}

	public Board execute() throws ConflictException {
		// TODO Auto-generated method stub

		Board b = board;
		if(!checkFull(b)) {
			int p = -1;
			p = findNextPebble(p, b);
			b = recursion(p, board); //start recursion from left-up most point on the board
		}
		
		return b;
	}
	
	private int findNextPebble(int position, Board b) {
		int i = position;
		
		try{
			i = position + 1;
			for(; b.getColor(i) == Board.NOTHING; i++)
				;
		}
		catch(Exception ex){
			//System.out.println("Reach limit");
			return -1;
		}
		
		return i;
	}

	private synchronized Board recursion(int p, Board temp) throws ConflictException {
		// TODO Auto-generated method stub
		/*try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Board b = temp.clone();
		FillBoard fb1 = new FillBoard(b.clone());
		b = fb1.execute();
		
		Coordinates c = new Coordinates(p,b.getSize());
		int color = b.getColor(p);
		
		try{
			b = tryPosition(c.getLeft(), b.clone(), color);
			b = tryPosition(c.getUp(), b.clone(), color);
			b = tryPosition(c.getRight(), b.clone(), color);
			b = tryPosition(c.getDown(), b.clone(), color);
		}
		catch(ConflictException ex){
			System.out.println("conflicting");
		}
		
		//b.printBoard();
		FillBoard fb2 = new FillBoard(b.clone());
		b = fb2.execute();
		//System.out.println("FB finished");
		
		int next = this.findNextPebble(p, temp);
		if(next == -1)
			next = findNextPebble(-1, b);
		//System.out.println("Next pebble is " + next);
		
		if(!checkFull(b)){
			b = recursion(next, b.clone());
		}
		
		Board result = b.clone();
		
		//System.out.println("Totally finished");
		return result;
	}
	
	private synchronized Board tryPosition(Coordinates c, Board b,int color) throws ConflictException {	
		Board temp = b.clone();
		if(c.inRange())
			 if(b.getColor(c.toPosition()) == Board.NOTHING){
				 if(checkAvailability(c, color, temp))
					 temp.addPebble(c.toPosition(), Board.InverseColor(color));
				 else temp.addPebble(c.toPosition(), color);
			 }
		return temp;
				 
	}
	
	private synchronized boolean checkAvailability(Coordinates c,int color, Board b) {
		
		if(c.getLeft().inRange()) {
			if(b.getColor(c.getLeft().toPosition()) == color) {
				if(c.getLeft().getLeft().inRange()) {
					if(b.getColor(c.getLeft().getLeft().toPosition()) == color) {
						return false;
					}
				}
				else if(c.getRight().inRange()) {
					if(b.getColor(c.getRight().toPosition()) == color)
						return false;
				}
			}
		}
		
		if(c.getUp().inRange()) {
			if(b.getColor(c.getUp().toPosition()) == color) {
				if(c.getUp().getUp().inRange()) {
					if(b.getColor(c.getUp().getUp().toPosition()) == color) {
						return false;
					}
				}
				else if(c.getDown().inRange()) {
					if(b.getColor(c.getDown().toPosition()) == color)
						return false;
				}
			}
		}
		
		if(c.getRight().inRange()) {
			if(b.getColor(c.getRight().toPosition()) == color) {
				if(c.getRight().getRight().inRange()) {
					if(b.getColor(c.getRight().getRight().toPosition()) == color) {
						return false;
					}
				}
				else if(c.getLeft().inRange()) {
					if(b.getColor(c.getLeft().toPosition()) == color)
						return false;
				}
			}
		}
		
		
		if(c.getDown().inRange()) {
			if(b.getColor(c.getDown().toPosition()) == color) {
				if(c.getDown().getDown().inRange()) {
					if(b.getColor(c.getDown().getDown().toPosition()) == color) {
						return false;
					}
				}
				else if(c.getUp().inRange()) {
					if(b.getColor(c.getUp().toPosition()) == color)
						return false;
				}
			}
		}
		
		return true;
	}

	private synchronized boolean checkFull(Board b) {
		int n = b.getNumbers();
		
		for(int i = 0; i < n; i++)
			if(b.getColor(i) == Board.NOTHING)
				return false;
		
		return true;
	}
	
	private boolean isPossible(Coordinates c) {
		//if()
		return false;
	}

	private class ListStack {
		private LinkedList<Step> l = new LinkedList<Step>();
		
		ListStack() {
		}
		
		void push(Step s) {
			this.l.addLast(s);
		}
		
		Step pop(Step s) {
			return this.l.pollLast();
		}
	}
	
	private class Step {
		private int step;
		private int color;
		
		Step(int s, int c) {
			this.step = s;
			this.color = c;
		}
	}

}
