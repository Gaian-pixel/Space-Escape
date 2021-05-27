package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroSpigoloDx extends Riquadro
{
	public RiquadroSpigoloDx(int id)
	{
		super(Risorse.spigolo[1], id);
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