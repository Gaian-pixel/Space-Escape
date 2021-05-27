package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Animazione;
import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroOblÚSu extends Riquadro
{
	private Animazione animazioneTile;
	
	public RiquadroOblÚSu(int id)
	{
		super(Risorse.oblÚSu[0], id);
		animazioneTile = new Animazione(500,Risorse.oblÚSu);
	}
	
	@Override
	public boolean ËSolido()
	{
		return true;
	}

	@Override
	public boolean renderizzaSotto()
	{
		return false;
	}
	
	@Override
	public void aggiornamento()
	{
		animazioneTile.aggiornamento();
		texture = animazioneTile.getFrameCorrente();
	}

	@Override
	public Riquadro getRiquadro()
	{
		return this;
	}

	@Override
	public boolean isAperto()
	{
		return ËSolido();
	}
}