package com.GiorgioAlessio.game.entità;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.armi.Colpo;
import com.GiorgioAlessio.game.entità.creatura.Giocatore;

public class ManagerEntità {
	public static final int MUNIZIONI_MASSIME = 50;
	
	private Maneggiatore maneggiatore;
	private Giocatore giocatore;
	private ArrayList<Entità> entità;
	private ArrayList<Colpo> colpi;
	
	private boolean inventarioAttivo = false;
	private int nNemici;
	
	private Comparator<Entità> selettoreRendering = new Comparator<Entità>(){
		@Override
		public int compare(Entità a, Entità b)
		{
			if((a.getBordoCollisioni(0f,0f).y + a.getBordoCollisioni(0f,0f).height) < 
					(b.getBordoCollisioni(0f,0f).y + b.getBordoCollisioni(0f,0f).height))
			{
				return -1;
			}
			else
			{
				return 1;
			}
		}
	};
	
	public ManagerEntità(Maneggiatore maneggiatore, Giocatore giocatore)
	{
		this.maneggiatore = maneggiatore;
		this.giocatore = giocatore;
		entità = new ArrayList<Entità>();
		colpi = new ArrayList<Colpo>();
		aggiungiEntità(giocatore);
		
		for(int i=0; i<MUNIZIONI_MASSIME; i++)
		{
			colpi.add(new Colpo(maneggiatore,0,0));
		}
	}
	
	public void aggiornamento() 
	{
		contaNemici();
		
		Iterator<Entità> iteratore = entità.iterator();
		
		while(iteratore.hasNext())
		{
			Entità e = iteratore.next();
			e.aggiornamento();
			
			e.timerDanno = System.currentTimeMillis() - e.timerInizioDanno;
			
			if(e.isColpito() && (e.timerDanno >= e.attesaDanno))
			{
				e.setColpito(false);
			}
			
			if(!e.isAttivo())
			{
				iteratore.remove();
			}
		}
		
		Iterator<Colpo> iteratoreColpo = colpi.iterator();
		
		while(iteratoreColpo.hasNext())
		{
			Entità e = iteratoreColpo.next();
			e.aggiornamento();
			
			e.timerDanno = System.currentTimeMillis() - e.timerInizioDanno;
			
			if(e.isColpito() && (e.timerDanno >= e.attesaDanno))
			{
				e.setColpito(false);
			}
			
			if(!e.isAttivo())
			{
				iteratoreColpo.remove();
			}
		}
		
		entità.sort(selettoreRendering);
	}
	
	public void renderizzazione(Graphics disegnatore) 
	{
		for(Entità e : entità)
		{
			e.renderizzazione(disegnatore);
		}
		
		for(Colpo e : colpi)
		{
			e.renderizzazione(disegnatore);
		}
		
		for(Entità e : entità)
		{
			e.dopoRenderizzazione(disegnatore);
		}
	}
	
	public void aggiungiEntità(Entità e)
	{
		entità.add(e);
	}
	
	//Metodi get/set

	public Maneggiatore getManeggiatore()
	{
		return maneggiatore;
	}

	public void setManeggiatore(Maneggiatore maneggiatore)
	{
		this.maneggiatore = maneggiatore;
	}

	public Giocatore getGiocatore()
	{
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore)
	{
		this.giocatore = giocatore;
	}

	public ArrayList<Entità> getEntità()
	{
		return entità;
	}
	
	public Entità getEntità(int pos)
	{
		return entità.get(pos);
	}

	public void setEntità(ArrayList<Entità> entità)
	{
		this.entità = entità;
	}

	public ArrayList<Colpo> getColpi()
	{
		return colpi;
	}

	public void setColpi(ArrayList<Colpo> colpi)
	{
		this.colpi = colpi;
	}
	
	public boolean isInventarioAttivo() {
		return inventarioAttivo;
	}

	public void setInventarioAttivo(boolean inventarioAttivo) {
		this.inventarioAttivo = inventarioAttivo;
	}

	public int getNNemici()
	{
		return nNemici;
	}

	public void setNNemici(int nNemici)
	{
		this.nNemici = nNemici;
	}

	public void aggiungiColpi(int n)
	{
		for(int i=0; i<n; i++)
		{
			colpi.add(new Colpo(maneggiatore, 0, 0));
		}
	}
	
	public boolean ricarica()
	{
		boolean ricarica = false;
		
		for(int i = colpi.size(); i<MUNIZIONI_MASSIME; i++)
		{
			colpi.add(new Colpo(maneggiatore,0,0));
			ricarica = true;
		}
		
		return ricarica;
	}
	
	public void contaNemici()
	{
		int contaNemici = 0;
		
		for(Entità e : entità)
		{
			if(e.isSeNemico() && !e.isAnimMorente() && !e.isMorente())
			{
				contaNemici++;
			}
		}
		
		nNemici = contaNemici;
	}
}
