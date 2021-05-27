package com.GiorgioAlessio.game.stati;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.connessione.ClientSocket;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.grafica.Testo;
import com.GiorgioAlessio.game.ui.AscoltatoreClick;
import com.GiorgioAlessio.game.ui.GestoreUi;
import com.GiorgioAlessio.game.ui.ImmagineBottoneUi;

public class StatoConnessione extends Stato
{
	private GestoreUi gestoreUi;
	String[] tabella;
	final static int DISTANZA_FISSA = 50;
	final static int DISTANZA_FISSA2 = 30;
	private ClientSocket client;
	private boolean errore;
	
	public StatoConnessione(Maneggiatore maneggiatore)
	{
		super(maneggiatore);
		gestoreUi = new GestoreUi(maneggiatore);
		maneggiatore.getGestoreMouse().setGestoreUi(gestoreUi);
		
		client = new ClientSocket();
		
		try
		{
			client.connetti();
			
			errore = false;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Errore: " + e);
			
			errore = true;
		}
		
		if(!errore)
		{
			String input = client.comunica(maneggiatore.getNomeGiocatore(),maneggiatore.getTempoInizio(),maneggiatore.getPunteggio());
			tabella = input.split(";");
		}
        
		if(errore)
		{
			gestoreUi.aggiungiOggetto(new ImmagineBottoneUi(maneggiatore, 152, 450, 96, 96, Risorse.bottone_start, new AscoltatoreClick()
			{
				@Override
				public void onClick()
				{
					maneggiatore.getGestoreMouse().setGestoreUi(null);
					maneggiatore.getGioco().resettaStati();
					maneggiatore.getGioco().setStatoConnessione(new StatoConnessione(maneggiatore));
					Stato.setStato(maneggiatore.getGioco().getStatoConnessione());
				}
			}));
		}
		
		int xBottone;
		int yBottone;
		
		if(errore)
		{
			xBottone = 352;
			yBottone = 450;
		}
		else
		{
			xBottone = 252;
			yBottone = 475;
		}
		
		gestoreUi.aggiungiOggetto(new ImmagineBottoneUi(maneggiatore, xBottone, yBottone, 
				96, 96, Risorse.bottone_start, new AscoltatoreClick()
		{
			@Override
			public void onClick()
			{
				maneggiatore.getGestoreMouse().setGestoreUi(null);
				maneggiatore.getGioco().resettaStati();
				maneggiatore.getGioco().setStatoMenu(new StatoMenù(maneggiatore));
				Stato.setStato(maneggiatore.getGioco().getStatoMenu());
			}
		}));
	}

	@Override
	public void aggiornamento()
	{
		
		
		gestoreUi.aggiornamento();
	}

	@Override
	public void renderizzazione(Graphics disegnatore)
	{
		disegnatore.setColor(Risorse.coloreSfondo);
		disegnatore.fillRect(0,0,600,600);
		
		if(!errore)
		{
			Testo.disegnaStringa(disegnatore, "Scoreboard Top 10", DISTANZA_FISSA*6, DISTANZA_FISSA, 
					true, new Color(255,255,0), Risorse.fontDanno);
			
			disegnatore.setColor(Color.red);
			disegnatore.fillRect(0, DISTANZA_FISSA+15, 600, 3);
			
			Testo.disegnaStringa(disegnatore, "Giocatore", (int)(DISTANZA_FISSA*3), 
					DISTANZA_FISSA + (DISTANZA_FISSA2 * 1)+15, 
					true, Risorse.coloreScritte, Risorse.fontDanno);
			
			Testo.disegnaStringa(disegnatore, "Tempo", (int)((DISTANZA_FISSA*(3*2))), 
					DISTANZA_FISSA + (DISTANZA_FISSA2 * 1)+15, 
					true, Risorse.coloreScritte, Risorse.fontDanno);
			
			Testo.disegnaStringa(disegnatore, "Punteggio", (int)((DISTANZA_FISSA*(3*3))), 
					DISTANZA_FISSA + (DISTANZA_FISSA2 * 1)+15, 
					true, Risorse.coloreScritte, Risorse.fontDanno);
			
			for(int i=0; i<tabella.length; i++)
			{
				String[] record = tabella[i].split(" ");
				
				for(int j=0; j<record.length; j++)
				{
					Testo.disegnaStringa(disegnatore, record[j], (int)((DISTANZA_FISSA*(3*(j+1)))), 
							DISTANZA_FISSA + (DISTANZA_FISSA2 * (i+2))+15, 
							true, Risorse.coloreScritte, Risorse.fontDanno);
				}
			}
			
			Testo.disegnaStringa(disegnatore, "Torna al menu\'", 300, 450, 
					true, Risorse.coloreScritte, Risorse.fontMenùConnessione);
		}
		
		if(errore)
		{
			Testo.disegnaStringa(disegnatore, "Ritenta", 200, 400, 
					true, Risorse.coloreScritte, Risorse.fontMenùConnessione);
			
			Testo.disegnaStringa(disegnatore, "Connessione", 200, 415, 
					true, Risorse.coloreScritte, Risorse.fontMenùConnessione);
			
			Testo.disegnaStringa(disegnatore, "Torna al menu\'", 400, 400, 
					true, Risorse.coloreScritte, Risorse.fontMenùConnessione);
		}
		
		gestoreUi.renderizzazione(disegnatore);
	}
}