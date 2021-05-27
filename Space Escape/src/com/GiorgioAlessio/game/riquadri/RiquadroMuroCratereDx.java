package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroMuroCratereDx extends Riquadro
{
	public RiquadroMuroCratereDx(int id)
	{
		super(Risorse.muroCratere[1], id);
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