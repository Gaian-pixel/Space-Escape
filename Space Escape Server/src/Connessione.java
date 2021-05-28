import java.sql.*;
import javax.swing.*;

public class Connessione
{
	public Connessione()
	{
		
	}
	
	public String invioDati(String nome, float tempo, int punteggio)
	{
		int puntiEsistenti;
		String risultato = "";
		String div = " ";
		String divLine = ";";
		ResultSet rs;
		
		try
		{
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Scoreboardgioco?user=root&password=");
			
			Statement statement = connection.createStatement();
			
			/*statement.executeQuery("INSERT INTO punteggi(Giocatore,Tempo,Punteggio) VALUES("
					+ "'" + giocatore + "'," + tempo + ","+ punteggio + ");");*/
			
			rs = statement.executeQuery("SELECT punteggio FROM punteggi WHERE giocatore='" + nome + "';");
			
			if(!rs.first())
			{
				statement.execute("INSERT INTO punteggi(Giocatore,Tempo,Punteggio) VALUES("
						+ "'" + nome + "'," + tempo + ","+ punteggio + ");");
			}
			else
			{
				puntiEsistenti = rs.getInt(1);
				
				if(puntiEsistenti < punteggio)
				{
					statement.execute("UPDATE Punteggi SET punteggio=" + punteggio + " WHERE giocatore='" + nome + "';");
					statement.execute("UPDATE Punteggi SET tempo=" + tempo + " WHERE giocatore='" + nome + "';");
				}
			}
			
			rs = statement.executeQuery("SELECT Giocatore,Tempo,Punteggio FROM punteggi ORDER BY punteggio DESC LIMIT 10;");
			
			while(rs.next())
			{
				risultato = risultato + rs.getString(1) + div + rs.getFloat(2) + div + rs.getInt(3) + divLine;
			}
			
			System.out.println(risultato);
			
			connection.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione: " + e);
		}
		
		return risultato + "\n";
	}
}