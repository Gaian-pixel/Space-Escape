package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroPavimento extends Riquadro
{
	public RiquadroPavimento(int id)
	{
		super(Risorse.pavimento, id);
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