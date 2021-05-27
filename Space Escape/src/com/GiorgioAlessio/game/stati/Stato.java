package com.GiorgioAlessio.game.stati;

import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;

/*Definizione della classe astratta "Stato", con i metodi aggiornamento e renderizzazione.
 Le sottoclassi di questa classe (di tipo abstract) sono obbligate a definirli.*/
public abstract class Stato
{
	/*Definizione dell'attributo "statoCorrente" di tipo "Stato" e dei suoi
	 metodi getter e setter.*/
	private static Stato statoCorrente = null;
	
	public static Stato getStato()
	{
		return statoCorrente;
	}

	public static void setStato(Stato stato)
	{
		statoCorrente = stato;
	}
	
	/*Dichiarazione dell'attributo di tipo "Maneggiatore" e del costruttore di
	 "Stato".*/
	protected Maneggiatore maneggiatore;
	
	public Stato(Maneggiatore maneggiatore)
	{
		this.maneggiatore = maneggiatore;
	}

	public abstract void aggiornamento();
	
	public abstract void renderizzazione(Graphics disegnatore);
}