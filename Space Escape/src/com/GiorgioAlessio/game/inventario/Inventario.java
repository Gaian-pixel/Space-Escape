package com.GiorgioAlessio.game.inventario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.grafica.Testo;
import com.GiorgioAlessio.game.items.Item;

public class Inventario
{
	private Maneggiatore maneggiatore;
	private boolean attivo;
	private ArrayList<ItemDellInventario> itemsNellInventario;
	
	private int inventarioX = 64, inventarioY = 48, inventarioLarghezza = 512, inventarioAltezza = 384;
	private int centroListaItemX = inventarioX + 171, centroListaItemY = inventarioY + inventarioAltezza / 2 + 5;
	private int spaziaturaLista = 30;
	
	private int immagineInventarioX = 420, immagineInventarioY = 50, immagineInventarioLarghezza = 128,
			immagineInventarioAltezza = 128;
	
	private int quantit‡X = 484, quantit‡Y = 172;
	
	private int itemSelezionato = 0;
	
	private String nome;
	
	private boolean sePass;
	boolean seGiocatore;
	
	public Inventario(Maneggiatore maneggiatore, String nome, boolean seGiocatore)
	{
		this.maneggiatore = maneggiatore;
		itemsNellInventario = new ArrayList<ItemDellInventario>();
		
		this.nome = nome;
		
		sePass = false;
		
		this.seGiocatore = seGiocatore;
	}
	
	public void aggiungiItemsNellInventario(ItemDellInventario item)
	{
		boolean nuovoItem = true;
		
		for(ItemDellInventario i : itemsNellInventario)
		{
			if(i.getItem().getId() == item.getItem().getId())
			{
				i.setQuantit‡(i.getQuantit‡() + 1);
				nuovoItem = false;
				break;
			}
		}
		
		if(nuovoItem && item != null)
		{
			itemsNellInventario.add(new ItemDellInventario(maneggiatore, item.getItem(),1));
		}
		
		if(seGiocatore)
		{
			maneggiatore.setPunteggio(maneggiatore.getPunteggio() + 5);
		}
	}
	
	public void rimuoviItemsNellInventario()
	{
		Iterator<ItemDellInventario> iteratoreItem = itemsNellInventario.iterator();
		
		while(iteratoreItem.hasNext())
		{
			ItemDellInventario i = iteratoreItem.next();
			
			if(i.getQuantit‡() == 0)
			{
				iteratoreItem.remove();
				
				if(itemSelezionato >= itemsNellInventario.size())
				{
					itemSelezionato--;
				}
			}
		}
	}
	
	public void aggiornamento()
	{
		if(!attivo)
			return;
		
		if(maneggiatore.getGestoreTasti().tastoAppenaPremuto(KeyEvent.VK_W))
		{
			itemSelezionato--;
		}
		
		if(maneggiatore.getGestoreTasti().tastoAppenaPremuto(KeyEvent.VK_S))
		{
			itemSelezionato++;
		}
		
		if(itemSelezionato < 0)
		{
			itemSelezionato = itemsNellInventario.size() - 1;
		}
		else if(itemSelezionato >= itemsNellInventario.size())
		{
			itemSelezionato = 0;
		}
		
		if(maneggiatore.getGestoreTasti().tastoAppenaPremuto(KeyEvent.VK_SPACE))
		{
			usaItem(getItemSelezionato());
		}
		
		rimuoviItemsNellInventario();
	}
	
	public void renderizzazione(Graphics disegnatore)
	{
		if(!attivo)
			return;
		
		disegnatore.drawImage(Risorse.inventario, inventarioX, inventarioY, 
				inventarioLarghezza, inventarioAltezza, null);
		
		Testo.disegnaStringa(disegnatore, nome, 64*5, 65, true, 
				Color.GRAY, Risorse.fontInventario);
		
		int lunghezza = itemsNellInventario.size();
		if(lunghezza == 0)
		{
			return;
		}
		else
		{
			for(int i=-5; i<6; i++)
			{
				if(itemSelezionato + i < 0 || itemSelezionato + i >= lunghezza)
				{
					continue;
				}
				
				if(i == 0)
				{
					Testo.disegnaStringa(disegnatore, "> " + 
							itemsNellInventario.get(itemSelezionato + i).getItem().getNome()
							+ " <", centroListaItemX, centroListaItemY + i * spaziaturaLista, 
							true, Color.GREEN, Risorse.fontInventario);
				}
				else
				{
					Testo.disegnaStringa(disegnatore, itemsNellInventario.get(itemSelezionato
							+ i).getItem().getNome(), centroListaItemX, centroListaItemY + 
							i * spaziaturaLista, true, Color.WHITE, Risorse.fontInventario);
				}
			}
		}
		
		ItemDellInventario item = itemsNellInventario.get(itemSelezionato);
		disegnatore.drawImage(item.getItem().getTexture(), immagineInventarioX, immagineInventarioY,
				immagineInventarioLarghezza, immagineInventarioAltezza,null);
		Testo.disegnaStringa(disegnatore, Integer.toString(item.getQuantit‡()), quantit‡X, quantit‡Y, true, Color.GREEN, Risorse.fontInventario);
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

	public boolean isAttivo()
	{
		return attivo;
	}

	public void setAttivo(boolean attivo)
	{
		this.attivo = attivo;
	}
	
	public int getQuantit‡(ItemDellInventario item)
	{
		int quantit‡ = 0;
		
		for(ItemDellInventario i : itemsNellInventario)
		{
			if(i.getItem().getId() == item.getItem().getId())
			{
				quantit‡ = i.getQuantit‡();
				break;
			}
		}
		
		return quantit‡;
	}
	
	public void riduciQuantit‡(ItemDellInventario item)
	{
		for(ItemDellInventario i : itemsNellInventario)
		{
			if(i.getItem().getId() == item.getItem().getId())
			{
				i.setQuantit‡(i.getQuantit‡() - 1);
				break;
			}
		}
	}
	
	public void usaItem(ItemDellInventario item)
	{
		boolean usato = false;
		
		if(getQuantit‡(item) > 0)
		{
			usato = item.usaItem();
		}
		
		if(usato)
		{
			riduciQuantit‡(item);
		}
	}
	
	public void setQuantit‡(Item item, int quantit‡)
	{
		for(ItemDellInventario i : itemsNellInventario)
		{
			if(i.getItem().getId() == item.getId())
			{
				i.setQuantit‡(quantit‡);
				break;
			}
		}
	}
	
	public ItemDellInventario getItemSelezionato()
	{
		ItemDellInventario itemDellInventario;
		
		itemDellInventario = itemsNellInventario.get(itemSelezionato);
		
		return itemDellInventario;
	}

	public ArrayList<ItemDellInventario> getItemsNellInventario()
	{
		return itemsNellInventario;
	}

	public void setItemsNellInventario(ArrayList<ItemDellInventario> itemsNellInventario)
	{
		this.itemsNellInventario = itemsNellInventario;
	}

	public boolean isSePass() {
		return sePass;
	}

	public void setSePass(boolean sePass) {
		this.sePass = sePass;
	}
}
