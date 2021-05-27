package com.GiorgioAlessio.game;

import com.GiorgioAlessio.game.grafica.Camera;
import com.GiorgioAlessio.game.input.GestoreMouse;
import com.GiorgioAlessio.game.input.GestoreTasti;
import com.GiorgioAlessio.game.mondi.Mondo;

public class Maneggiatore
{
	private Gioco gioco;
	private Mondo mondo;
	
	private String nomeGiocatore;
	private long tempoInizio;
	private int punteggio = 0;
	
	public Maneggiatore(Gioco gioco)
	{
		this.gioco = gioco;
	}

	public int getLarghezza()
	{
		return gioco.getLarghezza();
	}

	public int getAltezza()
	{
		return gioco.getAltezza();
	}

	public GestoreTasti getGestoreTasti()
	{
		return gioco.getGestoreTasti();
	}
	
	public GestoreMouse getGestoreMouse()
	{
		return gioco.getGestoreMouse();
	}

	public Camera getCamera()
	{
		return gioco.getCamera();
	}
	
	public Gioco getGioco()
	{
		return gioco;
	}

	public void setGioco(Gioco gioco)
	{
		this.gioco = gioco;
	}

	public Mondo getMondo()
	{
		return mondo;
	}

	public void setMondo(Mondo mondo)
	{
		this.mondo = mondo;
	}

	public String getNomeGiocatore()
	{
		return nomeGiocatore;
	}

	public void setNomeGiocatore(String nomeGiocatore)
	{
		this.nomeGiocatore = nomeGiocatore;
	}

	public long getTempoInizio()
	{
		return tempoInizio;
	}

	public void setTempoInizio(long tempoInizio)
	{
		this.tempoInizio = tempoInizio;
	}

	public int getPunteggio()
	{
		return punteggio;
	}

	public void setPunteggio(int punteggio)
	{
		this.punteggio = punteggio;
	}
}
