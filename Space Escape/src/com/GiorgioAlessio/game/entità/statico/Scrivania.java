package com.GiorgioAlessio.game.entità.statico;

import java.awt.Graphics;
import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Risorse;

public class Scrivania extends EntitàStatica
{
	public Scrivania(Maneggiatore maneggiatore, float x, float y)
	{
		super(maneggiatore, x, y, 128, 128);
		bordo.x = 24;
		bordo.y = 70;
		bordo.width = 78;
		bordo.height = 12;
		
		bordoColpi.x = 24;
		bordoColpi.y = 70;
		bordoColpi.width = 78;
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
		disegnatore.drawImage(Risorse.scrivania,(int)(x - maneggiatore.getCamera().getxOffset()),
				(int)(y - maneggiatore.getCamera().getyOffset()),larghezza,altezza,null);
		
		/*disegnatore.setColor(Color.red);
		disegnatore.fillRect((int) (x + bordo.x - maneggiatore.getCamera().getxOffset()),
				(int) (y + bordo.y - maneggiatore.getCamera().getyOffset()),
				bordo.width, bordo.height);
		
		disegnatore.setColor(Color.blue);
		disegnatore.fillRect((int) (flagDxSu.x - maneggiatore.getCamera().getxOffset()),
				(int) (flagDxSu.y - maneggiatore.getCamera().getyOffset()),
				flagDxSu.width, flagDxSu.height);

		disegnatore.setColor(Color.red);
		disegnatore.fillRect((int) (flagSxSu.x - maneggiatore.getCamera().getxOffset()),
				(int) (flagSxSu.y - maneggiatore.getCamera().getyOffset()),
				flagSxSu.width, flagSxSu.height);

		disegnatore.setColor(Color.yellow);
		disegnatore.fillRect((int) (flagDxGiù.x - maneggiatore.getCamera().getxOffset()),
				(int) (flagDxGiù.y - maneggiatore.getCamera().getyOffset()),
				flagDxGiù.width, flagDxGiù.height);

		disegnatore.setColor(Color.cyan);
		disegnatore.fillRect((int) (flagSxGiù.x - maneggiatore.getCamera().getxOffset()),
				(int) (flagSxGiù.y - maneggiatore.getCamera().getyOffset()),
				flagSxGiù.width, flagSxGiù.height);*/
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