package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroSpigoloSu extends Riquadro
{
	public RiquadroSpigoloSu(int id)
	{
		super(Risorse.spigolo[0], id);
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