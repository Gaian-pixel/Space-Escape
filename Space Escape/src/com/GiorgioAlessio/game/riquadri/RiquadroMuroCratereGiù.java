package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroMuroCratereGiù extends Riquadro
{
	public RiquadroMuroCratereGiù(int id)
	{
		super(Risorse.muroCratere[2], id);
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