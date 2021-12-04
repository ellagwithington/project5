import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiThread extends Thread {
	
	public Socket socket;
	public String name ; 
	public String end = "end"; 
	
	public MultiThread(Socket socket, String name) {
		this.socket = socket ; 
		this.name = name ; 
	}
	
	public void run() {
		PrintWriter out = null ; 
		BufferedReader in = null ; 
		
		try {
			out = new PrintWriter(socket.getOutputStream()); 
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String line ;
		
			while(true) {
			while((line = in.readLine())  != null){
				System.out.println(name + " " +line); 
				
				
			}
			}
			
			
		
			
			
			
		}catch(Exception e) {
			
		}
		
	
	}
	
	

}
