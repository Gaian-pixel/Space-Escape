package com.GiorgioAlessio.game.stati;

import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Animazione;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.ui.AscoltatoreClick;
import com.GiorgioAlessio.game.ui.GestoreUi;
import com.GiorgioAlessio.game.ui.ImmagineBottoneUi;

/*Definizione della classe "StatoMenu", sottoclasse di "Stato", al cui interno vengono
definit i metodi "aggiornamento" e "renderizzazione".*/
public class StatoMenù extends Stato
{
	private GestoreUi gestoreUi;
	private Animazione animazioneVignetta;
	
	public StatoMenù(Maneggiatore maneggiatore)
	{
		super(maneggiatore);
		gestoreUi = new GestoreUi(maneggiatore);
		maneggiatore.getGestoreMouse().setGestoreUi(gestoreUi);
		
		animazioneVignetta = new Animazione(133,Risorse.vignetta);
		
		maneggiatore.setPunteggio(0);
		
		gestoreUi.aggiungiOggetto(new ImmagineBottoneUi(maneggiatore, 252, 252, 96, 96, Risorse.bottone_start, new AscoltatoreClick()
		{
			@Override
			public void onClick()
			{
				maneggiatore.getGestoreMouse().setGestoreUi(null);
				maneggiatore.getGioco().resettaStati();
				maneggiatore.getGioco().setStatoInput(new StatoInput(maneggiatore,false));
				Stato.setStato(maneggiatore.getGioco().getStatoInput());
			}
		}));
	}

	@Override
	public void aggiornamento()
	{
		gestoreUi.aggiornamento();
		animazioneVignetta.aggiornamento();
	}

	@Override
	public void renderizzazione(Graphics disegnatore)
	{
		disegnatore.setColor(Risorse.coloreSfondo);
		disegnatore.fillRect(0,0,600,600);
		disegnatore.drawImage(Risorse.logo, 35, 50, 530, 50, null);
		disegnatore.drawImage(Risorse.player_fermo[0], -30, 428, 192, 192, null);
		disegnatore.drawImage(animazioneVignetta.getFrameCorrente(), 25, 400, Risorse.larghezzaVignetta/2,
				Risorse.altezzaVignetta/2, null);
		gestoreUi.renderizzazione(disegnatore);
	}
}
