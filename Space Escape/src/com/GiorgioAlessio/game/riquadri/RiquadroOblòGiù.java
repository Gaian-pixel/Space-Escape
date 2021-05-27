package com.GiorgioAlessio.game.riquadri;

import com.GiorgioAlessio.game.grafica.Animazione;
import com.GiorgioAlessio.game.grafica.Risorse;

public class RiquadroOblòGiù extends Riquadro
{
	private Animazione animazioneTile;
	
	public RiquadroOblòGiù(int id)
	{
		super(Risorse.oblòGiù[0], id);
		animazioneTile = new Animazione(500,Risorse.oblòGiù);
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
		return èSolido();
	}
}