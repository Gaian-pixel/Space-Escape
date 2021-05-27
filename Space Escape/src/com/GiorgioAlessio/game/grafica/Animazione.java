package com.GiorgioAlessio.game.grafica;

import java.awt.image.BufferedImage;

public class Animazione
{
	private int velocità,i;
	private BufferedImage[] frame;
	private long ultimaVolta,timer;
	
	public Animazione(int velocità, BufferedImage[] frame)
	{
		this.velocità = velocità;
		this.frame = frame;
		i = 0;
		ultimaVolta = System.currentTimeMillis();
		timer = 0;
	}
	
	public void aggiornamento()
	{
		timer = timer + System.currentTimeMillis() - ultimaVolta;
		ultimaVolta = System.currentTimeMillis();
		
		if(timer > velocità)
		{
			i++;
			timer = 0;
			
			if(i >= frame.length)
			{
				i = 0;
			}
		}
	}
	
	public BufferedImage getFrameCorrente()
	{
		return frame[i];
	}
}
