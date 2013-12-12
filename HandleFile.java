import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;



public class HandleFile {

	public static synchronized InfoPack inputFile(String file) throws ConflictException, FileNotFoundException {
		// TODO Auto-generated method stub
		InfoPack info = new InfoPack();
		Scanner input = new Scanner(new File("input.txt"));
		MyQueue<Integer> q = new MyQueue<Integer>();
		int zeros = 0;
		int size = 0;
		
		while(input.hasNext()){
			String s = input.next();
			int i = Integer.parseInt(s);
			if(i == 0)
				zeros++;
			q.enqueue(i);
		}
		input.close();
		
		if(q.getSize() < 3 || zeros != 2 || (size = q.dequeue()) == 0)
			throw new ConflictException("Input Error");
		else info.setSize(size);
		
		for(int i = q.dequeue();q.getSize() > 0;i = q.dequeue()) {
			if (i == 0)
				break;
			else
				info.addPebble(i, Board.WHITE);
		}
		
		for(int i = 0;;) {
			if ((i = q.dequeue()) == 0)
				break;
			else info.addPebble(i, Board.BLACK);
		}
		
		/*System.out.println("Board size: "+info.getSize());
		System.out.println("Initial Whites: "+info.getWhites().toString());
		System.out.println("Initial Blacks: "+info.getBlacks().toString());
		*/
		return info;
	}
	
	public static void outputFile(Board board,String fileName) {
		//int[] iteration = board.getBoard();
		int n = board.getNumbers();
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(fileName);
			
			for(int i = 0; i < n; i++) {
				if(board.getColor(i) == Board.WHITE) {
					pw.print(i + 1);
					pw.print(" ");
				}
			}

			pw.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File already exists");
			e.printStackTrace();
		}
		

		//for(int i; )
		
	}
	
	public static void CreateErrorFile(String file) {
		
	}
}
