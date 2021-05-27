package com.GiorgioAlessio.game.entit‡.statico;

import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Animazione;
import com.GiorgioAlessio.game.grafica.Risorse;

public class Ologramma extends Entit‡Statica
{
	private Animazione animazione;
	
	public Ologramma(Maneggiatore maneggiatore, float x, float y)
	{
		super(maneggiatore, x, y, 64, 64);
		bordo.x = 13;
		bordo.y = 31;
		bordo.width = 37;
		bordo.height = 8;
		
		bordoColpi.x = 13;
		bordoColpi.y = 31;
		bordoColpi.width = 37;
		bordoColpi.height = 8;
		
		definizioneFlag();
		
		animazione = new Animazione(500,Risorse.ologramma);
	}

	@Override
	public void aggiornamento() 
	{
		animazione.aggiornamento();
	}

	@Override
	public void renderizzazione(Graphics disegnatore) 
	{
		disegnatore.drawImage(animazione.getFrameCorrente(),(int)(x - maneggiatore.getCamera().getxOffset()),(int)(y - maneggiatore.getCamera().getyOffset()),larghezza,altezza,null);
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