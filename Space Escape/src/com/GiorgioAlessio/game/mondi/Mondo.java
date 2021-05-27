package com.GiorgioAlessio.game.mondi;

import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.entità.ManagerEntità;
import com.GiorgioAlessio.game.entità.creatura.Giocatore;
import com.GiorgioAlessio.game.entità.creatura.Nemico;
import com.GiorgioAlessio.game.entità.statico.Cassa;
import com.GiorgioAlessio.game.entità.statico.Ologramma;
import com.GiorgioAlessio.game.entità.statico.Scrivania;
import com.GiorgioAlessio.game.entità.statico.Sedia;
import com.GiorgioAlessio.game.items.GestoreItems;
import com.GiorgioAlessio.game.riquadri.Riquadro;
import com.GiorgioAlessio.game.strumenti.Strumenti;

/*Classe che serve a generare i mondi*/
public class Mondo
{
	/*Metodi per definire il mondo. Il vettore bidimensionale "riquadri" contiene i riquadri del mondo.*/
	private Maneggiatore maneggiatore;
	private int larghezza, altezza;
	private int spawnX, spawnY;
	private int[][] riquadri;
	private boolean cassaAperta = false;
	
	private int contatoreKill = 0;
	
	public static final int DIMENSIONE_RIQUADRO = 64;
	
	//entità
	public ManagerEntità managerEntità;
	
	//items
	private GestoreItems gestoreItems;
	
	//Costruttore
	public Mondo(Maneggiatore maneggiatore, String percorso)
	{
		this.maneggiatore = maneggiatore;
		caricaMondo(percorso);
		
		gestoreItems = new GestoreItems(maneggiatore);
		
		managerEntità = new ManagerEntità(maneggiatore, new Giocatore(maneggiatore,
				spawnX,spawnY));
		
		//Nemici
		managerEntità.aggiungiEntità(new Nemico(maneggiatore,
				DIMENSIONE_RIQUADRO*15,DIMENSIONE_RIQUADRO*4,managerEntità));
		managerEntità.aggiungiEntità(new Nemico(maneggiatore,
				DIMENSIONE_RIQUADRO*16,DIMENSIONE_RIQUADRO*6,managerEntità));
		managerEntità.aggiungiEntità(new Nemico(maneggiatore,
				DIMENSIONE_RIQUADRO*14,DIMENSIONE_RIQUADRO*8,managerEntità));
		managerEntità.aggiungiEntità(new Nemico(maneggiatore,
				DIMENSIONE_RIQUADRO*18,DIMENSIONE_RIQUADRO*16,managerEntità));
		managerEntità.aggiungiEntità(new Nemico(maneggiatore,
				DIMENSIONE_RIQUADRO*32,DIMENSIONE_RIQUADRO*6,managerEntità));
		
		//Entità
		managerEntità.aggiungiEntità(new Scrivania(maneggiatore,
				(int)(DIMENSIONE_RIQUADRO*10.5),(int)(DIMENSIONE_RIQUADRO*7)));
		managerEntità.aggiungiEntità(new Ologramma(maneggiatore,
				(int)(DIMENSIONE_RIQUADRO*1),(int)(DIMENSIONE_RIQUADRO*1)));
		managerEntità.aggiungiEntità(new Sedia(maneggiatore,
				(int)(DIMENSIONE_RIQUADRO*10.5),(int)(DIMENSIONE_RIQUADRO*6.5)));
		
		managerEntità.aggiungiEntità(new Cassa(maneggiatore,
				(int)(DIMENSIONE_RIQUADRO*18.5),(int)(DIMENSIONE_RIQUADRO*7.75),3));
		managerEntità.aggiungiEntità(new Cassa(maneggiatore,
				(int)(DIMENSIONE_RIQUADRO*36.75),(int)(DIMENSIONE_RIQUADRO*0.25),2));
		managerEntità.aggiungiEntità(new Cassa(maneggiatore,
				(int)(DIMENSIONE_RIQUADRO*26.25),(int)(DIMENSIONE_RIQUADRO*7.75),1));
	}
	
	public void aggiornamento()
	{
		for(Riquadro r : Riquadro.riquadri)
		{
			r.aggiornamento();
		}
		
		gestoreItems.aggiornamento();
		managerEntità.aggiornamento();
	}
	
	/*Renderizza il nostro riquadro a schermo.*/
	public void renderizzazione(Graphics disegnatore)
	{
		/*Limitazione del rendering rispetto alle dimensioni della camera.*/
		int xInizio = (int) Math.max(0, maneggiatore.getCamera().getxOffset() / Riquadro.LARGHEZZA_RIQUADRO);
		int xFine = (int) Math.min(larghezza, (maneggiatore.getCamera().getxOffset() + 
				maneggiatore.getLarghezza()) / Riquadro.LARGHEZZA_RIQUADRO + 1);
		int yInizio = (int) Math.max(0, maneggiatore.getCamera().getyOffset() / Riquadro.ALTEZZA_RIQUADRO);
		int yFine = (int) Math.min(altezza, (maneggiatore.getCamera().getyOffset() + 
				maneggiatore.getAltezza()) / Riquadro.ALTEZZA_RIQUADRO + 1);
		
		for(int y=yInizio; y<yFine; y++)
		{
			for(int x=xInizio; x<xFine; x++)
			{
				/*Per renderizzare tutti i riquadri moltiplichiamo le variabili della posizione
				 dei riquadri per le loro dimensioni.*/
				getRiquadro(x,y).renderizzazione(disegnatore,(int) (x*Riquadro.LARGHEZZA_RIQUADRO
						- maneggiatore.getCamera().getxOffset()),(int) (y*Riquadro.ALTEZZA_RIQUADRO - 
						maneggiatore.getCamera().getyOffset()));
			}
		}
		
		//renderizzazione items
		gestoreItems.renderizzazione(disegnatore);
		
		//renderizzazione entità
		managerEntità.renderizzazione(disegnatore);
	}
	
	/*Metodo che restituisce il riquadro da stampare. Se il riquadro richiamato non esiste
	 restituisce un riquadro di terra.*/
	public Riquadro getRiquadro(int x, int y)
	{
		//Condizione che evita il crash del gioco in caso il player esca dalla mappa.
		//Se il player esce dalla mappa questa condizione dice che il player percepisce di
		//essere in un riquadro pavimento.
		if(x<0 || y<0 || x>=larghezza || y>=altezza)
		{
			return Riquadro.riquadroPavimento;
		}
		else
		{
			Riquadro riquadro = Riquadro.riquadri.get(riquadri[x][y]);
			
			if(riquadro == null)
			{
				return Riquadro.riquadroPavimento;
			}
			else
			{
				return riquadro;
			}
		}
	}
	
	/*Metodo che serve a caricare il mondo partendo da un file di generazione casuale di mondi.*/
	public void caricaMondo(String percorso)
	{
		String file = Strumenti.caricaFileComeStringa(percorso);
		
		/*Suddivisione delle cifre del file all'interno di un vettore di stringhe.
		 Il parametro del metodo split indica che bisogna escludere dal vettore gli spazi e 
		 i ritorni a capo.*/
		String[] gettone = file.split("\\s+");
		
		larghezza = Strumenti.parseInt(gettone[0]);
		altezza = Strumenti.parseInt(gettone[1]);
		
		spawnX = Strumenti.parseInt(gettone[2]);
		spawnY = Strumenti.parseInt(gettone[3]);
		
		riquadri = new int[larghezza][altezza];
		for(int y=0; y<altezza; y++)
		{
			for(int x=0; x<larghezza; x++)
			{
				riquadri[x][y] = Strumenti.parseInt(gettone[(x+y*larghezza)+4]);
			}
		}
	}
	
	public int getLargezza() 
	{
		return larghezza;
	}
	
	public int getAltezza() 
	{
		return altezza;
	}

	public ManagerEntità getManagerEntità()
	{
		return managerEntità;
	}

	public Maneggiatore getManeggiatore()
	{
		return maneggiatore;
	}

	public void setManeggiatore(Maneggiatore maneggiatore)
	{
		this.maneggiatore = maneggiatore;
	}

	public GestoreItems getGestoreItems()
	{
		return gestoreItems;
	}

	public void setGestoreItems(GestoreItems gestoreItems)
	{
		this.gestoreItems = gestoreItems;
	}

	public boolean isCassaAperta()
	{
		return cassaAperta;
	}

	public void setCassaAperta(boolean cassaAperta)
	{
		this.cassaAperta = cassaAperta;
	}

	public int getContatoreKill()
	{
		return contatoreKill;
	}

	public void setContatoreKill(int contatoreKill)
	{
		this.contatoreKill = contatoreKill;
	}
}
