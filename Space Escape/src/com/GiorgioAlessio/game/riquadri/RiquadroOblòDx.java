package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Animazione;
import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroOblÚDx extends Riquadro
{
	private Animazione animazioneTile;
	
	public RiquadroOblÚDx(int id)
	{
		super(Risorse.oblÚDx[0], id);
		animazioneTile = new Animazione(500,Risorse.oblÚDx);
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
	public void aggiornamento()
	{
		animazioneTile.aggiornamento();
		texture = animazioneTile.getFrameCorrente();
	}

	@Override
	public boolean isAperto()
	{
		return ËSolido();
	}
}