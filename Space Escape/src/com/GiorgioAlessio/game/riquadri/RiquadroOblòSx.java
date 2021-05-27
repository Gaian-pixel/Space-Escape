package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Animazione;
import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroOblÚSx extends Riquadro
{
	private Animazione animazioneTile;
	
	public RiquadroOblÚSx(int id)
	{
		super(Risorse.oblÚSx[0], id);
		animazioneTile = new Animazione(500,Risorse.oblÚSx);
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
	public void aggiornamento()
	{
		animazioneTile.aggiornamento();
		texture = animazioneTile.getFrameCorrente();
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