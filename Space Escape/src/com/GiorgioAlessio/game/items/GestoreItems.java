package com.GiorgioAlessio.game.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import com.GiorgioAlessio.game.Maneggiatore;

public class GestoreItems
{
	private Maneggiatore maneggiatore;
	private ArrayList<Item> items;
	
	public GestoreItems(Maneggiatore maneggiatore)
	{
		this.maneggiatore = maneggiatore;
		items = new ArrayList<Item>();
	}
	
	public void aggiornamento()
	{
		Iterator<Item> iteratore = items.iterator();
		
		while(iteratore.hasNext())
		{
			Item i = iteratore.next();
			i.aggiornamento();
			
			if(i.isAcquisito())
			{
				iteratore.remove();
			}
		}
	}
	
	public void renderizzazione(Graphics disegnatore)
	{
		for(Item i : items)
		{
			i.renderizzazione(disegnatore);
		}
	}
	
	public void aggiungiItem(Item i)
	{
		i.setManeggiatore(maneggiatore);
		items.add(i);
	}
	
	//Metodi Getter e Setter

	public Maneggiatore getManeggiatore()
	{
		return maneggiatore;
	}

	public void setManeggiatore(Maneggiatore maneggiatore)
	{
		this.maneggiatore = maneggiatore;
	}
}
