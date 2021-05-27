package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroSpigoloGiùInverso extends Riquadro
{
	public RiquadroSpigoloGiùInverso(int id)
	{
		super(Risorse.spigoloInverso[2], id);
	}
	
	@Override
	public boolean èSolido()
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
		return èSolido();
	}
}