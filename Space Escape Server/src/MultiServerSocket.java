import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServerSocket
{
	public void attendi()
	{
		ServerSocket serverSocket;
		
		try
		{
			serverSocket = new ServerSocket(6789);
			
			while(true)
			{
				//JOptionPane.showMessageDialog(null, "Server in attesa di connessione...");
				System.out.println("Server in attesa di connessione...");
				
				Socket socket = serverSocket.accept();
				//JOptionPane.showMessageDialog(null, "Nuovo Client connesso");
				System.out.println("Nuovo Client connesso");
				
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}