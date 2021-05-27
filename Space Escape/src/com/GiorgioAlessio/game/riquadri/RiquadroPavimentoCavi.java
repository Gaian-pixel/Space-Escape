package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroPavimentoCavi extends Riquadro
{
	public RiquadroPavimentoCavi(int id)
	{
		super(Risorse.pavimentoCavi, id);
	}

	@Override
	public boolean ËSolido()
	{
		return false;
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