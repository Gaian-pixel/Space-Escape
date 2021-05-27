package com.GiorgioAlessio.game.stati;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextField;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.grafica.Testo;
import com.GiorgioAlessio.game.ui.AscoltatoreClick;
import com.GiorgioAlessio.game.ui.GestoreUi;
import com.GiorgioAlessio.game.ui.ImmagineBottoneUi;
import com.GiorgioAlessio.game.ui.InputTesto;

/*Definizione della classe "StatoMenu", sottoclasse di "Stato", al cui interno vengono
definit i metodi "aggiornamento" e "renderizzazione".*/
public class StatoInput extends Stato
{
	private GestoreUi gestoreUi;
	private boolean errore;
	
	private boolean anim = false;
	
	private long tempoAnimazione = 1000;
	private long tempoCorrente;
	private long tempoImpiegato;
	
	private long tempoAnimazione2 = 100;
	private long tempoCorrente2;
	private long tempoImpiegato2;
	
	public StatoInput(Maneggiatore maneggiatore, boolean erroreExt)
	{
		super(maneggiatore);
		gestoreUi = new GestoreUi(maneggiatore);
		maneggiatore.getGestoreMouse().setGestoreUi(gestoreUi);
		
		errore = erroreExt;
		
		JTextField inputNomeGiocatore = new JTextField();
		inputNomeGiocatore.setBounds(252, 252, 96, 96);
		inputNomeGiocatore.setVisible(true);
		
		if(errore)
		{
			tempoCorrente = System.currentTimeMillis();
			tempoCorrente2 = System.currentTimeMillis();
		}
		
		gestoreUi.aggiungiOggetto(new InputTesto(maneggiatore, 200, 250, 200, 25, new AscoltatoreClick()
		{
			@Override
			public void onClick()
			{
				gestoreUi.getOggetti().get(0).setScrivibile(true);
			}
		}));
		
		gestoreUi.aggiungiOggetto(new ImmagineBottoneUi(maneggiatore, 252, 300, 96, 96, Risorse.bottone_start, new AscoltatoreClick()
		{
			@Override
			public void onClick()
			{
				String nome = gestoreUi.getOggetti().get(0).getNome();
				
				if(!nome.equals(""))
				{
					maneggiatore.setNomeGiocatore(nome);
					maneggiatore.setTempoInizio(System.currentTimeMillis());
					
					maneggiatore.getGestoreMouse().setGestoreUi(null);
					maneggiatore.getGioco().resettaStati();
					maneggiatore.getGioco().setStatoIntro(new StatoIntro(maneggiatore));
					Stato.setStato(maneggiatore.getGioco().statoIntro);
				}
				else
				{
					maneggiatore.getGestoreMouse().setGestoreUi(null);
					maneggiatore.getGioco().resettaStati();
					maneggiatore.getGioco().setStatoInput(new StatoInput(maneggiatore, true));
					Stato.setStato(maneggiatore.getGioco().statoInput);
				}
			}
		}));
	}

	@Override
	public void aggiornamento()
	{
		gestoreUi.aggiornamento();
		
		if(errore)
		{
			animazione();
		}
	}

	@Override
	public void renderizzazione(Graphics disegnatore)
	{
		disegnatore.setColor(Risorse.coloreSfondo);
		disegnatore.fillRect(0,0,600,600);
		
		if(errore)
		{
			if(anim)
			{
				Testo.disegnaStringa(disegnatore, "Inserire nome Giocatore:", 300, 200, true,
						Risorse.coloreScritte, Risorse.fontDanno);
			}
			else
			{
				Testo.disegnaStringa(disegnatore, "Inserire nome Giocatore:", 300, 200, true,
						Color.RED, Risorse.fontDanno);
			}
		}
		else
		{
			Testo.disegnaStringa(disegnatore, "Inserire nome Giocatore:", 300, 200, true,
					Risorse.coloreScritte, Risorse.fontDanno);
		}
		
		gestoreUi.renderizzazione(disegnatore);
	}
	
	public void animazione()
	{
		tempoImpiegato = System.currentTimeMillis() - tempoCorrente;
		
		if(tempoImpiegato < tempoAnimazione)
		{
			tempoImpiegato2 = System.currentTimeMillis() - tempoCorrente2;
			
			if(tempoImpiegato2 >= tempoAnimazione2)
			{
				anim = !anim;
				
				tempoCorrente2 = System.currentTimeMillis();
			}
		}
		else
		{
			anim = true;
		}
	}
}
