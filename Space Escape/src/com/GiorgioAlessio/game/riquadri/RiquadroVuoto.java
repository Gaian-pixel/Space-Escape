package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroVuoto extends Riquadro
{
	public RiquadroVuoto(int id)
	{
		super(Risorse.vuoto, id);
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