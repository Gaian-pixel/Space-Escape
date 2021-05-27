package com.GiorgioAlessio.game.armi;

import java.awt.Color;
import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.entit‡.Entit‡;
import com.GiorgioAlessio.game.entit‡.creatura.Creatura;
import com.GiorgioAlessio.game.riquadri.Riquadro;

public class Colpo extends Creatura
{
	private static final int DIMENSIONE_COLPO = 3;
	
	Maneggiatore maneggiatore;
	
	private int velocit‡ = 15;
	private int dannoPrimario = 20;
	
	private boolean usato;
	
	public Colpo(Maneggiatore maneggiatore, int x, int y)
	{
		super(maneggiatore, x, y, DIMENSIONE_COLPO, DIMENSIONE_COLPO);
		this.maneggiatore = maneggiatore;
		
		salute = 1;
		
		bordo.x = x;
		bordo.y = y;
		bordo.width = DIMENSIONE_COLPO;
		bordo.height = DIMENSIONE_COLPO;
		
		bordoColpi.x = x;
		bordoColpi.y = y;
		bordoColpi.width = DIMENSIONE_COLPO;
		bordoColpi.height = DIMENSIONE_COLPO;
		
		contieneFlag = false;
		usato = false;
		
		colpo = true;
	}
	
	public void aggiornamento()
	{
		movimento();
	}
	
	@Override
	public void movimento()
	{
		Entit‡ ex = controlloColpiEntit‡(xMovimento, 0f);
		Entit‡ ey = controlloColpiEntit‡(0f, yMovimento);
		
		if(ex == null || ex.getSeGiocatore())
		{
			muoviX();
		}
		else
		{
			ex.setDannoSubito(dannoPrimario);
			ex.danno();
			ex.setColpito(true);
			
			ex.setTimerInizioDanno(System.currentTimeMillis());
			
			attivo = false;
		}
		
		if(ey == null || ey.getSeGiocatore())
		{
			muoviY();
		}
		else
		{
			ey.setDannoSubito(dannoPrimario);
			ey.danno();
			ey.setColpito(true);
			
			ey.setTimerInizioDanno(System.currentTimeMillis());
			
			attivo = false;
		}
	}
	
	@Override
	public void muoviX()
	{
		if(xMovimento > 0)
		{
			//Ci stiamo muovendo a destra
			
			//Definizione dello spigolo in alto a destra del riquadro di collisione.
			int tx = (int) (x + xMovimento + bordo.x + bordo.width) / Riquadro.LARGHEZZA_RIQUADRO;
			
			//Controllo delle collisioni quando ci si muove a destra
			if(!collisioniConRiquadro(tx,(int) (y + bordo.y) / Riquadro.ALTEZZA_RIQUADRO) && 
				!collisioniConRiquadro(tx,(int) (y + bordo.y + bordo.height) / Riquadro.ALTEZZA_RIQUADRO))
			{
				x = x + xMovimento;
			}
			else 
			{
				//Viene eliminato il gap di px fra il riquadro di collisione e i riquadri solidi
				attivo = false;
			}
		}
		else if(xMovimento < 0)
		{
			//Ci stiamo muovendo a sinistra
			//Definizione dello spigolo in alto a sinistra del riquadro di collisione.
			int tx = (int) (x + xMovimento + bordo.x) / Riquadro.LARGHEZZA_RIQUADRO;
			
			//Controllo delle collisioni quando ci si muove a destra
			if(!collisioniConRiquadro(tx,(int) (y + bordo.y) / Riquadro.ALTEZZA_RIQUADRO) && 
				!collisioniConRiquadro(tx,(int) (y + bordo.y + bordo.height) / Riquadro.ALTEZZA_RIQUADRO))
			{
				x = x + xMovimento;
			}
			else 
			{
				//Viene eliminato il gap di px fra il riquadro di collisione e i riquadri solidi
				attivo = false;
			}
		}
	}
	
	@Override
	public void muoviY()
	{
		if(yMovimento < 0)
		{
			//Ci stiamo muovendo in alto
			int ty = (int) (y + yMovimento + bordo.y) / Riquadro.ALTEZZA_RIQUADRO;
			
			if(!collisioniConRiquadro((int) (x + bordo.x) / Riquadro.LARGHEZZA_RIQUADRO, ty) &&
					!collisioniConRiquadro((int) (x + bordo.x + bordo.width) / Riquadro.LARGHEZZA_RIQUADRO, ty))
			{
				y = y + yMovimento;
			}
			else 
			{
				//Viene eliminato il gap di px fra il riquadro di collisione e i riquadri solidi
				attivo = false;
			}
		}
		else if(yMovimento > 0)
		{
			//Ci stiamo muovendo in basso
			int ty = (int) (y + yMovimento + bordo.y + bordo.height) / Riquadro.ALTEZZA_RIQUADRO;
			
			if(!collisioniConRiquadro((int) (x + bordo.x) / Riquadro.LARGHEZZA_RIQUADRO, ty) &&
					!collisioniConRiquadro((int) (x + bordo.x + bordo.width) / Riquadro.LARGHEZZA_RIQUADRO, ty))
			{
				y = y + yMovimento;
			}
			else 
			{
				//Viene eliminato il gap di px fra il riquadro di collisione e i riquadri solidi
				attivo = false;
			}
		}
	}
	
	public void renderizzazione(Graphics disegnatore)
	{
		if(usato)
		{
			disegnatore.setColor(Color.cyan);
			disegnatore.fillRect((int)(x + bordo.x - maneggiatore.getCamera().getxOffset()),
					(int)(y + bordo.y - maneggiatore.getCamera().getyOffset()),
					bordo.width, bordo.height);
		}
	}

	@Override
	public void morte()
	{
		
	}
	
	public void setDirezione(int direzioneColpo)
	{
		switch(direzioneColpo)
		{
			case 0:
				bordo.height = 15;
				yMovimento = velocit‡;
				break;
			case 1:
				bordo.width = 15;
				xMovimento = velocit‡;
				break;
			case 2:
				bordo.width = 15;
				xMovimento = -velocit‡;
				break;
			case 3:
				bordo.height = 15;
				yMovimento = -velocit‡;
				break;
			default:
				return;
		}
	}
	
	@Override
	protected boolean collisioniConRiquadro(int x, int y)
	{
		boolean collisione;
		
		if(maneggiatore.getMondo().getRiquadro(x, y).ËSolido() || 
				maneggiatore.getMondo().getRiquadro(x, y).isAperto())
		{
			collisione=true;
		}
		else
		{
			collisione=false;
		}
		
		return collisione;
	}
	
	public boolean isUsato()
	{
		return usato;
	}
	
	public void setUsato(boolean usato)
	{
		this.usato = usato;
	}

	@Override
	public void dopoRenderizzazione(Graphics disegnatore)
	{
		// TODO Auto-generated method stub
		
	}
}
