package com.GiorgioAlessio.game.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.GiorgioAlessio.game.Maneggiatore;

public class ImmagineBottoneUi extends OggettoUi
{
	private BufferedImage[] immagini;
	private AscoltatoreClick clicker;
	
	/*private Color verde = new Color(57, 255, 20);
	private Color rosso = new Color(128, 0, 0);*/
	private int r,g,b;
	
	private Color sfondoAnimazione;
	
	private int anim = 0;

	public ImmagineBottoneUi(Maneggiatore maneggiatore, float x, float y, int larghezza, int altezza, BufferedImage[] immagini,AscoltatoreClick clicker) 
	{
		super(maneggiatore, x, y, larghezza, altezza);
		this.immagini = immagini;
		this.clicker = clicker;
		
		r=255;
		g=0;
		b=0;
	}

	@Override
	public void aggiornamento()
	{
		animazione();
		
		/*if(anim)
		{
			sfondoAnimazione = verde;
		}
		else
		{
			sfondoAnimazione = rosso;
		}*/
		
		sfondoAnimazione = new Color(r,g,b);
	}

	@Override
	public void renderizzazione(Graphics disegnatore) 
	{
		if(sovrapposizione)
		{
			disegnatore.setColor(sfondoAnimazione);
			disegnatore.fillRect((int) x, (int) y, larghezza, altezza);
			
			disegnatore.setColor(Color.black);
			disegnatore.drawRect((int) x, (int) y, larghezza, altezza);
			
			disegnatore.drawImage(immagini[1], (int) x , (int) y , larghezza, altezza,null);
		}
		else
		{
			disegnatore.setColor(Color.LIGHT_GRAY);
			disegnatore.fillRect((int) x, (int) y, larghezza, altezza);
			
			disegnatore.setColor(Color.black);
			disegnatore.drawRect((int) x, (int) y, larghezza, altezza);
			
			disegnatore.drawImage(immagini[0], (int) x , (int) y , larghezza, altezza,null);
		}
	}

	@Override
	public void onClick()
	{
		clicker.onClick();
	}
	
	public void animazione()
	{
		/*tempoImpiegato = System.currentTimeMillis() - tempoCorrente;
		
		if(tempoImpiegato >= tempoAnimazione)
		{
			anim = !anim;
			tempoCorrente = System.currentTimeMillis();
		}*/
		
		switch(anim)
		{
			case 0:
				g += 5;
				
				if(g==255)
				{
					anim = 1;
				}
				break;
			case 1:
				r -= 5;
				
				if(r==0)
				{
					anim = 2;
				}
				break;
			case 2:
				b += 5;
				
				if(b==255)
				{
					anim = 3;
				}
				break;
			case 3:
				g -= 5;
				
				if(g==0)
				{
					anim = 4;
				}
				break;
			case 4:
				r += 5;
				
				if(r==255)
				{
					anim = 5;
				}
				break;
			case 5:
				b -= 5;
				
				if(b==0)
				{
					anim = 0;
				}
				break;
			default:
				break;
		}
	}
}
