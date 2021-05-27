package com.GiorgioAlessio.game.stati;

import java.awt.Color;
import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.grafica.Testo;

public class BarraDiStato
{
	private Maneggiatore maneggiatore;
	private int saluteGiocatore;
	private int nColpi;
	
	private int x,y,larghezza,altezza;
	private String nomeGiocatore;
	
	private int contatoreKill;
	private int punteggio;
	
	public BarraDiStato(Maneggiatore maneggiatore, int x, int y, int larghezza, int altezza)
	{
		this.maneggiatore = maneggiatore;
		this.x = x;
		this.y = y;
		this.larghezza = larghezza;
		this.altezza = altezza;
		
		nomeGiocatore = maneggiatore.getNomeGiocatore();
	}
	
	public void aggiornamento()
	{
		saluteGiocatore = maneggiatore.getMondo().getManagerEntità().getGiocatore().getSalute();
		nColpi = maneggiatore.getMondo().getManagerEntità().getColpi().size();
		contatoreKill = maneggiatore.getMondo().getContatoreKill();
		punteggio = maneggiatore.getPunteggio();
	}
	
	public void renderizzazione(Graphics disegnatore)
	{
		disegnatore.setColor(Risorse.coloreSfondo);
		disegnatore.fillRect(x, y, larghezza, altezza);
		
		disegnatore.setColor(Color.red);
		disegnatore.fillRect(x+18, y+35, (int) (saluteGiocatore * 1.5), 15);
		
		disegnatore.setColor(Color.black);
		disegnatore.fillRect(x, y, larghezza, 3);
		disegnatore.drawRect(x+18, y+35, 150, 15);
		
		Testo.disegnaStringa(disegnatore, "Health", x+100, y+20, 
				true, Risorse.coloreScritte, Risorse.fontDanno);
		
		Testo.disegnaStringa(disegnatore, saluteGiocatore + "%", x+203, y+43, 
				true, Risorse.coloreScritte, Risorse.fontDanno);
		
		disegnatore.setColor(Color.black);
		disegnatore.fillRect(x, y, larghezza, 3);
		
		disegnatore.fillRect(x + 240, y, 3, altezza);
		
		Testo.disegnaStringa(disegnatore, "Ammo", x + 260, y + 30, 
				false, Risorse.coloreScritte, Risorse.fontDanno);
		
		Testo.disegnaStringa(disegnatore, nColpi + "/50", x + 250, y + 50, 
				false, Risorse.coloreScritte, Risorse.fontDanno);
		
		disegnatore.setColor(Color.black);
		disegnatore.fillRect(x + 350, y, 3, altezza);
		
		Testo.disegnaStringa(disegnatore, nomeGiocatore, x + 475, y + 15, 
				true, Risorse.coloreScritte, Risorse.fontDanno);
		
		Testo.disegnaStringa(disegnatore, "Uccisioni: " + contatoreKill, x + 360, y + 40, 
				false, Risorse.coloreScritte, Risorse.fontDanno);
		
		Testo.disegnaStringa(disegnatore, "Punti: " + punteggio, x + 360, y + 60, 
				false, Risorse.coloreScritte, Risorse.fontDanno);
	}
}