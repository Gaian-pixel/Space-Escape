package com.GiorgioAlessio.game.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import com.GiorgioAlessio.game.Maneggiatore;

public class GestoreUi
{
	private Maneggiatore maneggiatore;
	private ArrayList<OggettoUi> oggetti;
	
	public GestoreUi(Maneggiatore maneggiatore)
	{
		this.maneggiatore = maneggiatore;
		oggetti = new ArrayList<OggettoUi>();
	}
	
	public void aggiornamento()
	{
		for(OggettoUi oggetto : oggetti)
		{
			oggetto.aggiornamento();
		}
	}
	
	public void renderizzazione(Graphics disegnatore)
	{
		for(OggettoUi oggetto : oggetti)
		{
			oggetto.renderizzazione(disegnatore);
		}
	}
	
	public void movimentoMouse(MouseEvent e)
	{
		for(OggettoUi oggetto : oggetti)
		{
			oggetto.movimentoMouse(e);
		}
	}
	
	public void rilascioMouse(MouseEvent e)
	{
		for(OggettoUi oggetto : oggetti)
		{
			oggetto.rilascioMouse(e);
		}
	}

	public void aggiungiOggetto(OggettoUi oggetto)
	{
		oggetti.add(oggetto);
	}
	
	public void rimuoviOggetto(OggettoUi oggetto)
	{
		oggetti.remove(oggetto);
	}
	
	//Metodi Getter e Setter
	public Maneggiatore getManeggiatore() {
		return maneggiatore;
	}

	public void setManeggiatore(Maneggiatore maneggiatore) {
		this.maneggiatore = maneggiatore;
	}

	public ArrayList<OggettoUi> getOggetti() {
		return oggetti;
	}

	public void setOggetti(ArrayList<OggettoUi> oggetti) {
		this.oggetti = oggetti;
	}
}