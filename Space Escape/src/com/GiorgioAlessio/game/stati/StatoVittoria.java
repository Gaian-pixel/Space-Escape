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
public class StatoVittoria extends Stato
{
	private GestoreUi gestoreUi;
	
	public StatoVittoria(Maneggiatore maneggiatore)
	{
		super(maneggiatore);
		gestoreUi = new GestoreUi(maneggiatore);
		maneggiatore.getGestoreMouse().setGestoreUi(gestoreUi);
		
		maneggiatore.setPunteggio(maneggiatore.getPunteggio() + 100);
		
		gestoreUi.aggiungiOggetto(new ImmagineBottoneUi(maneggiatore, 252, 252, 96, 96, Risorse.bottone_start, new AscoltatoreClick()
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
		
		Testo.disegnaStringa(disegnatore, "Hai Vinto!",
				300, 230, true, Risorse.coloreScritte, Risorse.fontDanno);
		
		gestoreUi.renderizzazione(disegnatore);
	}
}
