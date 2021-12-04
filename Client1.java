import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
	public static void main(String[]args) {
	
		try {
		Socket socket = new Socket("localhost",32007);
		SceneManager.setSocket(socket);
		
		PrintWriter out = new PrintWriter(SceneManager.getSocket().getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(SceneManager.getSocket().getInputStream()));
		
		Scanner sc = new Scanner(System.in); 
		String line = null; 
		
		while(!"exit".equalsIgnoreCase(line)) {
			line = sc.nextLine(); 
			out.println(line);
			out.flush();
			
			System.out.println("Server replied:" + in.readLine()); 
		}
		
		sc.close();
		//socket.close();
		
		
		
	}catch(Exception e) {
		
	}
}
}
