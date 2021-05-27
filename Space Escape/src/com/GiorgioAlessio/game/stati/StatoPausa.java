package com.GiorgioAlessio.game.stati;

import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.grafica.Testo;
import com.GiorgioAlessio.game.ui.AscoltatoreClick;
import com.GiorgioAlessio.game.ui.GestoreUi;
import com.GiorgioAlessio.game.ui.ImmagineBottoneUi;

/*Definizione della classe "StatoMenu", sottoclasse di "Stato", al cui interno vengono
definit i metodi "aggiornamento" e "renderizzazione".*/
public class StatoPausa extends Stato
{
	private GestoreUi gestoreUi;
	
	public StatoPausa(Maneggiatore maneggiatore)
	{
		super(maneggiatore);
		gestoreUi = new GestoreUi(maneggiatore);
		maneggiatore.getGestoreMouse().setGestoreUi(gestoreUi);
		
		maneggiatore.setPunteggio(0);
		
		gestoreUi.aggiungiOggetto(new ImmagineBottoneUi(maneggiatore, 152, 352, 96, 96, Risorse.bottone_start, new AscoltatoreClick()
		{
			@Override
			public void onClick()
			{
				maneggiatore.getGestoreMouse().setGestoreUi(null);
				Stato.setStato(maneggiatore.getGioco().getStatoGioco());
			}
		}));
		
		gestoreUi.aggiungiOggetto(new ImmagineBottoneUi(maneggiatore, 352, 352, 96, 96, Risorse.bottone_start, new AscoltatoreClick()
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
		
		Testo.disegnaStringa(disegnatore, "Pausa", 300, 200, 
				true, Risorse.coloreScritte, Risorse.fontDanno);
		
		Testo.disegnaStringa(disegnatore, "Torna al", 200, 325, 
				true, Risorse.coloreScritte, Risorse.fontDanno);
		Testo.disegnaStringa(disegnatore, "gioco", 200, 340, 
				true, Risorse.coloreScritte, Risorse.fontDanno);
		
		Testo.disegnaStringa(disegnatore, "Torna al", 400, 325, 
				true, Risorse.coloreScritte, Risorse.fontDanno);
		Testo.disegnaStringa(disegnatore, "menu\'", 400, 340, 
				true, Risorse.coloreScritte, Risorse.fontDanno);
		
		gestoreUi.renderizzazione(disegnatore);
	}
}
