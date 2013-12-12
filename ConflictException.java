import java.io.PrintWriter;


public class ConflictException extends Exception {
	String msg = "no comment";
	public ConflictException(String msg) {
		//System.out.println(msg);
		//System.out.println("Can't Solve It");
		this.msg = msg;
	}
	public ConflictException() {
		//System.out.println("Can't Solve It");
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(Solver.outFileName);
			
			pw.print("UNSOLVABLE");
			pw.close();
		}
		catch(Exception ex) {
			
		}
			
	}
	public void printMsg() {
		System.out.println(msg);
	}
}
