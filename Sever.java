import java.net.ServerSocket;
import java.net.Socket;

import com.sun.tools.jconsole.JConsoleContext.ConnectionState;

public class Sever {
	//private volatile ConnectionState state; 
	
	public static void main(String[]args) {
		ServerSocket server = null; 
		try {
			
			server = new ServerSocket(32007); 
			
			Socket client = server.accept(); 
			System.out.println("connected");
			SceneManager.setSocket(client); 
			
			
			MultiThread thread1 = new MultiThread(SceneManager.getSocket(),"Bob"); 
			thread1.start();
			
			Socket client2 = server.accept(); 
			SceneManager.setSocket(client2);
			
			MultiThread thread2 = new MultiThread(SceneManager.getSocket(),"Amber"); 
			
			thread2.start();
			
			server.close();
			
			
		}catch(Exception e) {
			
		}
	}

}
