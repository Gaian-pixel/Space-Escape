package com.GiorgioAlessio.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*Creazione della classe "GestoreTasti" che prende in input gli eventi
 da tastiera. Ogni volta che un tasto da tastiera viene rilasciato o
 premuto questa classe si "attiva".*/
public class GestoreTasti implements KeyListener
{
	/*Dichiarazione del vettore dei tasti. Viene aggiornato ad ogni click
	 * di un qualsiasi tasto della tastiera.*/
	private boolean[] tasti, appenaPremuto, nonPremibile;
	
	/*Dichiarazione delle variabili su,giu,destra,sinistra, che servono
	 * al movimento del giocatore.*/
	public boolean su, giù, destra, sinistra;
	
	/*Dichiarazione delle variabili su,giu,destra,sinistra, che servono
	 * ad indicare la direzione dell'attacco del giocatore.*/
	public boolean attaccoSu, attaccoGiù, attaccoDestra, attaccoSinistra;
	
	public boolean attaccoPrimario,ricaricaMunizioni,garza,mediKit;
	public boolean apriCassa,spostaUnoInGiocatore,spostaItemInGiocatore,spostaTuttoInGiocatore;
	
	public boolean usaItem;
	
	public static boolean pausa;
	
	public GestoreTasti()
	{
		super();
		tasti = new boolean[256];
		appenaPremuto = new boolean[tasti.length];
		nonPremibile = new boolean[tasti.length];
	}
	
	/*Aggiornamento delle variabili di movimento.*/
	public void aggiornamento()
	{
		for(int i=0; i<tasti.length; i++)
		{
			if(nonPremibile[i] && !tasti[i])
			{
				nonPremibile[i] = false;
			}
			else if(appenaPremuto[i])
			{
				nonPremibile[i] = true;
				appenaPremuto[i] = false;
			}
			
			if(!nonPremibile[i] && tasti[i])
			{
				appenaPremuto[i] = true;
			}
		}
		
		su = tasti[KeyEvent.VK_W];
		giù = tasti[KeyEvent.VK_S];
		destra = tasti[KeyEvent.VK_D];
		sinistra = tasti[KeyEvent.VK_A];
		
		attaccoSu = tasti[KeyEvent.VK_UP];
		attaccoGiù = tasti[KeyEvent.VK_DOWN];
		attaccoDestra = tasti[KeyEvent.VK_RIGHT];
		attaccoSinistra = tasti[KeyEvent.VK_LEFT];
		
		attaccoPrimario = tasti[KeyEvent.VK_SPACE];
		ricaricaMunizioni = tasti[KeyEvent.VK_R];
		
		apriCassa = tasti[KeyEvent.VK_F];
		garza = tasti[KeyEvent.VK_1];
		mediKit = tasti[KeyEvent.VK_2];
		
		spostaUnoInGiocatore = tasti[KeyEvent.VK_ENTER];
		
		pausa = tasti[KeyEvent.VK_ESCAPE];
		
		if(tasti[KeyEvent.VK_ALT] && tasti[KeyEvent.VK_O])
		{
			spostaItemInGiocatore = true;
		}
		
		if(tasti[KeyEvent.VK_ALT] && tasti[KeyEvent.VK_P])
		{
			spostaTuttoInGiocatore = true;
		}
		
		usaItem = tasti[KeyEvent.VK_SPACE];
	}
	
	public boolean tastoAppenaPremuto(int codiceTasto)
	{
		if(codiceTasto < 0 || codiceTasto >= tasti.length)
			return false;
			
		return appenaPremuto[codiceTasto];
	}
	
	/*I metodi che seguono servono a prendere in input gli eventi
	 * della tastiera e a memorizzarli nel vettore booleano.*/
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() < 0 || e.getKeyCode() >= tasti.length)
			return;
		
		tasti[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() < 0 && e.getKeyCode() >= tasti.length)
			return;
		
		tasti[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}

	public String getTastiTastiera()
	{
		String tastoPremuto = "";
		
		for(int i=0; i<256; i++)
		{
			if(tastoAppenaPremuto(i) && ((i>='A' && i<='Z') || (i>='0' && i<='9')))
			{
				tastoPremuto = tastoPremuto + (char) i;
			}
			
			if(tastoAppenaPremuto(i) && i == KeyEvent.VK_BACK_SPACE)
			{
				tastoPremuto = "-1";
			}
		}
		
		return tastoPremuto;
	}
}