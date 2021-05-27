package com.GiorgioAlessio.game.riquadri;

import java.awt.Graphics;

import com.GiorgioAlessio.game.grafica.Animazione;
import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroPortaSu extends Riquadro
{
	private Animazione animazioneTile;
	private boolean aperta;
	private boolean aperturaPorta;
	
	public RiquadroPortaSu(int id)
	{
		super(Risorse.porta[0], id);
		animazioneTile = new Animazione(150,Risorse.portaSu);
		
		aperta = false;
		aperturaPorta = false;
	}
	
	@Override
	public boolean ËSolido()
	{
		if(aperta)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean renderizzaSotto()
	{
		if(aperta)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Riquadro getRiquadro()
	{
		return this;
	}
	
	@Override
	public void aggiornamento()
	{
		seGiocatoreConPass();
		
		if(aperturaPorta)
		{
			animazioneTile.aggiornamento();
			texture = animazioneTile.getFrameCorrente();
			
			if(texture.equals(Risorse.portaSu[3]))
			{
				aperturaPorta = false;
				aperta = true;
			}
		}
		else if(aperta)
		{
			texture = Risorse.portaSu[3];
		}
		else
		{
			texture = Risorse.porta[0];
		}
	}
	
	@Override
	public void renderizzazione(Graphics disegnatore, int x, int y)
	{
		disegnatore.drawImage(texture, x, y, LARGHEZZA_RIQUADRO, ALTEZZA_RIQUADRO, null);
	}
	
	public void seGiocatoreConPass()
	{
		if(maneggiatore.getMondo().getManagerEntit‡().getGiocatore().getInventario().isSePass() && !aperta)
		{
			aperturaPorta = true;
		}
	}

	@Override
	public boolean isAperto()
	{
		return aperta;
	}

	public void setAperto(boolean aperta)
	{
		this.aperta = aperta;
	}
}