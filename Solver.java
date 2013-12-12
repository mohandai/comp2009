import java.io.FileNotFoundException;




public class Solver{
	private int size;
	private Board board;
	private InfoPack info;
	
	public static String inFileName;
	public static String outFileName;
	
	private static final int NOTHING = Board.NOTHING;
	private static final int BLACK = Board.BLACK;
	private static final int WHITE = Board.WHITE;
	
	public Solver(String inFileName, String outFileName) throws ConflictException, FileNotFoundException {
		// TODO Auto-generated constructor stub
		Solver.inFileName = inFileName;
		Solver.outFileName = outFileName;
		
		this.info = HandleFile.inputFile(Solver.inFileName);
		board = new Board(info);
	}

	private synchronized void Solving(Board b) throws ConflictException {
		// TODO Auto-generated method stub
		FillBoard fb = new FillBoard(b.clone());
		b = fb.execute();
		//b.printBoard();
		Guess g = new Guess(b.clone());
		//OK
		b = g.execute();
		System.out.println("Result is:");
		b.printBoard();
		HandleFile.outputFile(b.clone(), Solver.outFileName);
		//board.printBoard();
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			Solving(board.clone());
		} catch (ConflictException e) {
			e.printMsg();
			System.out.println("Cannot solve it!");
		}
		//HandleFile.outputFile(board.clone(), outFileName);
	}
	
	public Board getOriginalBoard() {
		return this.board;
	}
	
}
