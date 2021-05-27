package com.GiorgioAlessio.game.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.grafica.Testo;

public class InputTesto extends OggettoUi
{
	private AscoltatoreClick clicker;
	private String nome = "";
	private boolean caratteriMassimi = false;

	public InputTesto(Maneggiatore maneggiatore, float x, float y, int larghezza, int altezza,AscoltatoreClick clicker) 
	{
		super(maneggiatore, x, y, larghezza, altezza);
		this.clicker = clicker;
	}
	
	@Override
	public void aggiornamento()
	{
		if(scrivibile)
		{
			String tastoPremuto = maneggiatore.getGestoreTasti().getTastiTastiera();
			
			if(tastoPremuto.equals("-1"))
			{
				if(nome.length() != 0)
				{
					nome = nome.substring(0, nome.length()-1);
					caratteriMassimi = false;
				}
			}
			else if(nome.length()<11)
			{
				nome = nome + tastoPremuto;
			}
			else
			{
				caratteriMassimi = true;
			}
		}
	}

	@Override
	public void renderizzazione(Graphics disegnatore) 
	{
		if(sovrapposizione)
		{
			disegnatore.setColor(Color.red);
		}
		else
		{
			disegnatore.setColor(Color.gray);
		}
		
		disegnatore.drawRect((int) x , (int) y , larghezza, altezza);
		Testo.disegnaStringa(disegnatore, nome, 200, 270, false, Risorse.coloreScritte, Risorse.fontDanno);
		
		if(caratteriMassimi)
		{
			Testo.disegnaStringa(disegnatore, "Caratteri massimi raggiunti (11)", (int) (x + (larghezza/2)), 
					(int) (y + (altezza*1.5)), true, Color.red, Risorse.fontErrore);
		}
	}

	@Override
	public void onClick()
	{
		clicker.onClick();
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}
}