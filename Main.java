import java.io.FileNotFoundException;




public class Main {
	/**
	 * @param args
	 */	
	
	private static String inFileName = "input.txt";
	private static String outFileName = "result.txt";
	
	public static void main(String[] args) {
		try{
			if(args.length == 0)
				;
			else if(args.length == 1)
				inFileName = args[0];
			else if(args.length == 2) {
				inFileName = args[0];
				outFileName = args[1];
			}
			else throw new ConflictException("Invalid Arguments");
		
			Solver solver = new Solver(inFileName, outFileName);
			solver.run();
		}
		catch(ConflictException ex) {
			ex.printMsg();
			System.out.println("Cannot solve it");
		}
		catch(FileNotFoundException ex) {
			System.out.println("FileNotFound");
			System.out.println("Cannot solve it");
		}
		finally{
			System.exit(0);
		}
		
	}

}
