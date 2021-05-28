import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread
{
	Socket client;

	public ServerThread(Socket client)
	{
		this.client = client;
	}
	
	@Override
	public void run()
	{
		try
		{
			comunica();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void comunica() throws IOException
	{
		String stringaRicevuta;
		BufferedReader inDaClient;
		DataOutputStream outVersoClient;
		Connessione connessione = new Connessione();
		
		inDaClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
		outVersoClient = new DataOutputStream(client.getOutputStream());
		
		stringaRicevuta = inDaClient.readLine();
		
		//JOptionPane.showMessageDialog(null, stringaRicevuta);
		System.out.println(stringaRicevuta);
		
		String[] vettore = stringaRicevuta.split(" ");
		
		String nome = vettore[0];
		float tempo = Float.parseFloat(vettore[1]);
		int punteggio = Integer.parseInt(vettore[2]);
		
		String output = connessione.invioDati(nome, tempo, punteggio);
		
		//JOptionPane.showMessageDialog(null, output);
		System.out.println(output);
		
		outVersoClient.writeBytes(output + "\n");
		
		//JOptionPane.showMessageDialog(null, "Inviato");
		System.out.println("Inviato");
	}
}
