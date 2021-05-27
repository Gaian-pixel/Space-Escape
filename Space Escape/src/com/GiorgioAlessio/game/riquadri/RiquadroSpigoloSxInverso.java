package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroSpigoloSxInverso extends Riquadro
{
	public RiquadroSpigoloSxInverso(int id)
	{
		super(Risorse.spigoloInverso[3], id);
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