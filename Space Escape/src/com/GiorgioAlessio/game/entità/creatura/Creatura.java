package com.GiorgioAlessio.game.entità.creatura;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.entità.Entità;
import com.GiorgioAlessio.game.riquadri.Riquadro;

/*Definizione della classe astratta "Creatura", sottoclasse di "Entità".
 Definisce le caratteristiche generali delle entità "Creatura".*/
public abstract class Creatura extends Entità
{
	//Dichiarazione attributi.
	public static final float VELOCITÁ_DEFAULT = 3.0f;
	public static final int LARGHEZZA_CREATURA_DEFAULT = 128,
							ALTEZZA_CREATURA_DEFAULT = 128;
	
	protected float velocità;
	
	protected float xMovimento;
	protected float yMovimento;
	
	/*Valori Direzione;
	 * 0 = Giu
	 * 1 = Destra
	 * 2 = Sinistra
	 * 3 = Su
	 * */
	protected int direzione;
	
	public Creatura(Maneggiatore maneggiatore, float x, float y, int larghezza, int altezza)
	{
		super(maneggiatore, x, y, larghezza, altezza);
		velocità = VELOCITÁ_DEFAULT;
		xMovimento = 0;
		yMovimento = 0;
	}
	
	/*Metodi*/
	public void movimento()
	{
		if(controlloCollisioniEntità(xMovimento, 0f) == null)
		{
			muoviX();
		}
		
		if(controlloCollisioniEntità(0f, yMovimento) == null)
		{
			muoviY();
		}
	}
	
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
				x = tx * Riquadro.LARGHEZZA_RIQUADRO - bordo.x - bordo.width - 1;
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
				x = tx * Riquadro.LARGHEZZA_RIQUADRO + Riquadro.LARGHEZZA_RIQUADRO - bordo.x;
			}
		}
	}
	
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
				y = ty * Riquadro.ALTEZZA_RIQUADRO + Riquadro.ALTEZZA_RIQUADRO - bordo.y;
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
				y = ty * Riquadro.ALTEZZA_RIQUADRO - bordo.y - bordo.height - 1;
			}
		}
	}
	
	/*-----------------------------------------------------------------------------------------------
	  ----------------------------------------------------------------------------------------------
	 ------------------------------------------------------------------------------------------------*/
	public boolean seRenderizzaX()
	{
		if(xMovimento > 0)
		{
			//Ci stiamo muovendo a destra
			
			//Definizione dello spigolo in alto a destra del riquadro di collisione.
			int tx = (int) (x + xMovimento + bordo.x + bordo.width) / Riquadro.LARGHEZZA_RIQUADRO;
			
			//Controllo delle collisioni quando ci si muove a destra
			if(!renderizzazioneSottoRiquadro(tx,(int) (y + bordo.y) / Riquadro.ALTEZZA_RIQUADRO) && 
				!renderizzazioneSottoRiquadro(tx,(int) (y + bordo.y + bordo.height) / Riquadro.ALTEZZA_RIQUADRO))
			{
				return true;
			}
			else 
			{
				//Viene eliminato il gap di px fra il riquadro di collisione e i riquadri solidi
				return false;
			}
		}
		else if(xMovimento < 0)
		{
			//Ci stiamo muovendo a sinistra
			//Definizione dello spigolo in alto a sinistra del riquadro di collisione.
			int tx = (int) (x + xMovimento + bordo.x) / Riquadro.LARGHEZZA_RIQUADRO;
			
			//Controllo delle collisioni quando ci si muove a destra
			if(!renderizzazioneSottoRiquadro(tx,(int) (y + bordo.y) / Riquadro.ALTEZZA_RIQUADRO) && 
				!renderizzazioneSottoRiquadro(tx,(int) (y + bordo.y + bordo.height) / Riquadro.ALTEZZA_RIQUADRO))
			{
				return true;
			}
			else 
			{
				//Viene eliminato il gap di px fra il riquadro di collisione e i riquadri solidi
				return false;
			}
		}
		
		return true;
	}
	
	public boolean seRenderizzaY()
	{
		if(yMovimento < 0)
		{
			//Ci stiamo muovendo in alto
			int ty = (int) (y + yMovimento + bordo.y) / Riquadro.ALTEZZA_RIQUADRO;
			
			if(!renderizzazioneSottoRiquadro((int) (x + bordo.x) / Riquadro.LARGHEZZA_RIQUADRO, ty) &&
					!renderizzazioneSottoRiquadro((int) (x + bordo.x + bordo.width) / Riquadro.LARGHEZZA_RIQUADRO, ty))
			{
				return true;
			}
			else
			{
				//Viene eliminato il gap di px fra il riquadro di collisione e i riquadri solidi
				return false;
			}
		}
		else if(yMovimento > 0)
		{
			//Ci stiamo muovendo in basso
			int ty = (int) (y + yMovimento + bordo.y + bordo.height) / Riquadro.ALTEZZA_RIQUADRO;
			
			if(!renderizzazioneSottoRiquadro((int) (x + bordo.x) / Riquadro.LARGHEZZA_RIQUADRO, ty) &&
					!renderizzazioneSottoRiquadro((int) (x + bordo.x + bordo.width) / Riquadro.LARGHEZZA_RIQUADRO, ty))
			{
				return true;
			}
			else 
			{
				//Viene eliminato il gap di px fra il riquadro di collisione e i riquadri solidi
				return false;
			}
		}
		
		return true;
	}
	
	public boolean seRiquadroPorta()
	{
		int SuDestra = maneggiatore.getMondo().getRiquadro(
				(int) (x + bordo.x + bordo.width) / Riquadro.LARGHEZZA_RIQUADRO, 
				(int) (y + bordo.y) / Riquadro.ALTEZZA_RIQUADRO).getRiquadro().getId();
		
		int GiuDestra = maneggiatore.getMondo().getRiquadro(
				(int) (x + bordo.x + bordo.width) / Riquadro.LARGHEZZA_RIQUADRO, 
				(int) (y + bordo.y + bordo.height) / Riquadro.ALTEZZA_RIQUADRO).getRiquadro().getId();
		
		int SuSinistra = maneggiatore.getMondo().getRiquadro(
				(int) (x + bordo.x) / Riquadro.LARGHEZZA_RIQUADRO, 
				(int) (y + bordo.y) / Riquadro.ALTEZZA_RIQUADRO).getRiquadro().getId();
		
		int GiuSinistra = maneggiatore.getMondo().getRiquadro(
				(int) (x + bordo.x) / Riquadro.LARGHEZZA_RIQUADRO, 
				(int) (y + bordo.y + bordo.height) / Riquadro.ALTEZZA_RIQUADRO).getRiquadro().getId();
		
		int vet[] = new int[4];
		
		vet[0] = SuDestra;
		vet[1] = GiuDestra;
		vet[2] = SuSinistra;
		vet[3] = GiuSinistra;
		
		boolean sePorta = false;
		
		for(int i=0; i<4; i++)
		{
			for(int j=i+1; j<4; j++)
			{
				if((vet[i] == vet[j]) && (vet[i]>=21 && vet[i]<=24))
				{
					sePorta = true;
				}
			}
		}
		
		return sePorta;
	}
	
	/*-----------------------------------------------------------------------------------------------
	  ----------------------------------------------------------------------------------------------
	 ------------------------------------------------------------------------------------------------*/
	
	//Controlla se il riquadro è solido per poter gestire le collisioni.
	protected boolean collisioniConRiquadro(int x, int y)
	{
		return maneggiatore.getMondo().getRiquadro(x, y).èSolido();
	}
	
	protected boolean renderizzazioneSottoRiquadro(int x, int y)
	{
		return maneggiatore.getMondo().getRiquadro(x, y).renderizzaSotto();
	}
	
	/*Getter e Setter*/
	public int getSalute() 
	{
		return salute;
	}

	public void setSalute(int salute)
	{
		this.salute = salute;
	}

	public float getVelocità()
	{
		return velocità;
	}

	public void setVelocità(float velocità)
	{
		this.velocità = velocità;
	}

	public float getxMovimento()
	{
		return xMovimento;
	}

	public void setxMovimento(int xMovimento)
	{
		this.xMovimento = xMovimento;
	}

	public float getyMovimento()
	{
		return yMovimento;
	}

	public void setyMovimento(int yMovimento)
	{
		this.yMovimento = yMovimento;
	}
}