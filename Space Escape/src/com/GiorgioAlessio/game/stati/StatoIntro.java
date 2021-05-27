package com.GiorgioAlessio.game.stati;

import java.awt.Color;
import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.grafica.Testo;
import com.GiorgioAlessio.game.ui.AscoltatoreClick;
import com.GiorgioAlessio.game.ui.GestoreUi;
import com.GiorgioAlessio.game.ui.ImmagineBottoneUi;

public class StatoIntro extends Stato
{
	private GestoreUi gestoreUi;
	private static final int DISTANZA_RIGHE_INIZIO = 50;
	private static final int DISTANZA_RIGHE = 20;
	
	public StatoIntro(Maneggiatore maneggiatore)
	{
		super(maneggiatore);
		gestoreUi = new GestoreUi(maneggiatore);
		maneggiatore.getGestoreMouse().setGestoreUi(gestoreUi);
		
		gestoreUi.aggiungiOggetto(new ImmagineBottoneUi(maneggiatore, 252, 475, 96, 96, Risorse.bottone_start, new AscoltatoreClick()
		{
			@Override
			public void onClick()
			{
				maneggiatore.getGestoreMouse().setGestoreUi(null);
				maneggiatore.getGioco().resettaStati();
				maneggiatore.getGioco().setStatoGioco(new StatoGioco(maneggiatore));
				Stato.setStato(maneggiatore.getGioco().getStatoGioco());
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
		
		disegnatore.drawImage(Risorse.vignettaSfondo, 25, 25, 550, 400, null);
		disegnatore.drawImage(Risorse.testaPlayer, -15, 400, 200, 200, null);
		
		Testo.disegnaStringa(disegnatore, "Attento, " + maneggiatore.getNomeGiocatore() + "!",
				300, DISTANZA_RIGHE_INIZIO, true, new Color(0,0,0), Risorse.fontMenùConnessione);
		
		Testo.disegnaStringa(disegnatore, "Sei bloccato in una stazione spaziale!",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		
		Testo.disegnaStringa(disegnatore, "Dovrebbe esserci una navicella di",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*2), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		
		Testo.disegnaStringa(disegnatore, "emergenza ormeggiata nello spazioporto.",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*3), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
				
		Testo.disegnaStringa(disegnatore, "Trova il nucleo di energia per rimetterla",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*4), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		
		Testo.disegnaStringa(disegnatore, "in funzione e SCAPPA!",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*5), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		
		Testo.disegnaStringa(disegnatore, "Utilizza W,A,S,D per muoverti e F per ",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*7), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		
		Testo.disegnaStringa(disegnatore, "interagire con gli oggetti.",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*8), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		
		Testo.disegnaStringa(disegnatore, "Difenditi dagli ostili sparando con SPAZIO",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*9), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		
		Testo.disegnaStringa(disegnatore, "e apri il tuo inventario con E.",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*10), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		
		Testo.disegnaStringa(disegnatore, "Utilizza gli item selezionati ",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*11), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		
		Testo.disegnaStringa(disegnatore, "all'interno dell'inventario con invio.",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*12), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		
		
		/*
		
		Testo.disegnaStringa(disegnatore, "",
				300, DISTANZA_RIGHE_INIZIO+(DISTANZA_RIGHE*), true, new Color(0,0,0), 
				Risorse.fontMenùConnessione);
		*/
		
		gestoreUi.renderizzazione(disegnatore);
	}
}
