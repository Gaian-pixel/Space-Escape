package com.GiorgioAlessio.game.strumenti;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*Definizione della classe "Strumenti"-*/
public class Strumenti
{
	/*Definizione del metodo "caricaFileComeStringa" che prende il contenuto di un file e lo
	 inserisce in una stringa.*/
	public static String caricaFileComeStringa(String percorso)
	{
		StringBuilder builder = new StringBuilder();
		
		try
		{
			/*Lettura file e inserimento in un buffer.*/
			BufferedReader buffer = new BufferedReader(new FileReader(percorso));
			
			/*Inserimento dati file da buffer a stringa.*/
			String riga;
			while((riga = buffer.readLine()) != null)
			{
				builder.append(riga + "\n");
			}
			
			buffer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	/*Metodo che prende come parametro una stringa contenente caratteri numerici e li inserisce in
	 una variabile intera.*/
	public static int parseInt(String numero)
	{
		try
		{
			return Integer.parseInt(numero);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}
