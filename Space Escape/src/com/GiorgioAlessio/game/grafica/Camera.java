package com.GiorgioAlessio.game.grafica;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.entità.Entità;
import com.GiorgioAlessio.game.riquadri.Riquadro;

/*Classe che gestisce la camera del gioco.*/
public class Camera
{
	//Attributi che indicano l'offset delle dimensioni x e y.
	private Maneggiatore maneggiatore;
	private float xOffset, yOffset;
	
	//Costruttore.
	public Camera(Maneggiatore maneggiatore, float xOffset, float yOffset)
	{
		this.maneggiatore = maneggiatore;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void controlloSpaziBianchi()
	{
		if(xOffset < 0)
		{
			xOffset = 0;
		}
		else if(xOffset > maneggiatore.getMondo().getLargezza() * Riquadro.LARGHEZZA_RIQUADRO - maneggiatore.getLarghezza())
		{
			xOffset = maneggiatore.getMondo().getLargezza() * Riquadro.LARGHEZZA_RIQUADRO - maneggiatore.getLarghezza();
		}
		
		if(yOffset < 0)
		{
			yOffset = 0;
		}
		else if(yOffset > maneggiatore.getMondo().getAltezza() * Riquadro.ALTEZZA_RIQUADRO - maneggiatore.getAltezza())
		{
			yOffset = maneggiatore.getMondo().getAltezza() * Riquadro.ALTEZZA_RIQUADRO - maneggiatore.getAltezza();
		}
	}
	
	public void focusSuEntità(Entità entità)
	{
		/*Gestisce l'offset della camera per centrare il giocatore (o il player) selezionato.*/
		xOffset = entità.getX() - maneggiatore.getLarghezza() / 2 + entità.getLarghezza() / 2;
		yOffset = entità.getY() - maneggiatore.getAltezza() / 2 + entità.getAltezza() / 2;
		controlloSpaziBianchi();
	}
	
	//Getter.
	public float getxOffset()
	{
		return xOffset;
	}

	public float getyOffset()
	{
		return yOffset;
	}

	//Setter.
	public void setxOffset(float xOffset)
	{
		this.xOffset = xOffset;
	}

	public void setyOffset(float yOffset)
	{
		this.yOffset = yOffset;
	}

	/*Metodo che sposta la camera in base alle nostre esigenze.*/
	public void movimento(float xQuantità, float yQuantità)
	{
		xOffset = xOffset + xQuantità;
		yOffset = yOffset + yQuantità;
		controlloSpaziBianchi();
	}
	
}