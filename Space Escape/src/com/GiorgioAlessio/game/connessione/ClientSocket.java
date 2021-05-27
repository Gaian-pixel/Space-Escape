package com.GiorgioAlessio.game.connessione;

import java.io.*;
import java.net.*;

public class ClientSocket 
{
    String nomeServer = "localhost";
    int portaServer = 6789;
    
    Socket miosocket;
    
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer; 

    public String comunica(String nome, long tempoInizio, int punteggio) 
    {
    	float tempo;
    	String div = " ";
		
		tempoInizio = System.currentTimeMillis() - tempoInizio;
		tempo = (float) tempoInizio / 1000;
		
		stringaUtente = nome + div + tempo + div + punteggio;
    	
		try
        {
        	//la spedisco al server
            //System.out.println("Invio la stringa al server e attendo ...\n");
            outVersoServer.writeBytes(stringaUtente + "\n");
            
            //System.out.println("Invio avvenuto, attendo risposta");
            
            //leggo la risposta dal server
            stringaRicevutaDalServer = inDalServer.readLine();
            
            //System.out.println(stringaRicevutaDalServer);
        } 
        catch(Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
        }

        // chiudo la connessione
        //System.out.println("CLIENT termina elaborazione e chiude connessione" );
        try 
        { 
            miosocket.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        return stringaRicevutaDalServer;
    }
    
    public void connetti() throws Exception
    {
    	//System.out.println("CLIENT partito in esecuzione ...");
    	
        //per l'input da tastiera
    	tastiera = new BufferedReader(new InputStreamReader(System.in));
        
        // creo un socket 
    	miosocket = new Socket();
    	miosocket.connect(new InetSocketAddress(InetAddress.getByName(nomeServer) ,portaServer), 3000);
        //InetAddress myInetAddress = InetAddress.getByName(nomeServer);
        //miosocket = new Socket(InetAddress.getLocalHost(), 6789);
        
        //associo due oggetti al socket per effettuare la scrittura e la lettura 
    	outVersoServer = new DataOutputStream(miosocket.getOutputStream());
    	inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
    }
}