package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroMuroGiù extends Riquadro
{
	public RiquadroMuroGiù(int id)
	{
		super(Risorse.muro[2], id);
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