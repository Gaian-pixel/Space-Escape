package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroMuroGraffioGiù extends Riquadro
{
	public RiquadroMuroGraffioGiù(int id)
	{
		super(Risorse.muroGraffio[2], id);
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