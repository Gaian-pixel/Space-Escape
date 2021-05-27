package com.GiorgioAlessio.game.entit‡.statico;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.entit‡.Entit‡;

public abstract class Entit‡Statica extends Entit‡
{
	public Entit‡Statica(Maneggiatore maneggiatore, float x, float y, int larghezza, int altezza)
	{
		super(maneggiatore,x,y,larghezza,altezza);
	}
}