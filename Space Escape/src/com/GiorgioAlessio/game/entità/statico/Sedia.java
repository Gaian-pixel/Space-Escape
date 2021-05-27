package com.GiorgioAlessio.game.entit‡.statico;

import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Risorse;

public class Sedia extends Entit‡Statica
{
	public Sedia(Maneggiatore maneggiatore, float x, float y)
	{
		super(maneggiatore, x, y, 128, 128);
		bordo.x = 40;
		bordo.y = 74;
		bordo.width = 46;
		bordo.height = 12;
		
		bordoColpi.x = 40;
		bordoColpi.y = 74;
		bordoColpi.width = 46;
		bordoColpi.height = 12;
		
		definizioneFlag();
	}

	@Override
	public void aggiornamento() 
	{
		
	}

	@Override
	public void renderizzazione(Graphics disegnatore) 
	{
		disegnatore.drawImage(Risorse.sedia,(int)(x - maneggiatore.getCamera().getxOffset()),
				(int)(y - maneggiatore.getCamera().getyOffset()),larghezza,altezza,null);
	}

	@Override
	public void morte()
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void danno()
	{
		
	}

	@Override
	public void dopoRenderizzazione(Graphics disegnatore) {
		// TODO Auto-generated method stub
		
	}
}