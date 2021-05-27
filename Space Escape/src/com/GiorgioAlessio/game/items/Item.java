package com.GiorgioAlessio.game.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.inventario.ItemDellInventario;

public class Item
{
	//Classe
	public static final int LARGHEZZAITEM = 64, ALTEZZAITEM = 64;
	
	protected Maneggiatore maneggiatore;
	protected BufferedImage texture;
	protected String nome;
	protected final int id;
	
	protected Rectangle bordo;
	
	protected int x,y;
	
	protected boolean acquisito = false;
	
	protected long timer, timerUltimaVolta, attesa;
	
	public Item(BufferedImage texture, String nome, int id, int attesa)
	{
		this.texture = texture;
		this.nome = nome;
		this.id = id;
		
		this.attesa = attesa;
		
		bordo = new Rectangle(x,y,LARGHEZZAITEM,ALTEZZAITEM);
	}
	
	public void aggiornamento()
	{
		if(maneggiatore.getMondo().getManagerEntità().getGiocatore().getBordoCollisioni(0f, 0f).intersects(bordo))
		{
			acquisito = true;
			maneggiatore.getMondo().getManagerEntità().getGiocatore().getInventario()
				.aggiungiItemsNellInventario(new ItemDellInventario(maneggiatore, this,1));
		}
	}
	
	public void renderizzazione(Graphics disegnatore)
	{
		if(maneggiatore == null)
		{
			return;
		}
		
		renderizzazione(disegnatore, (int) (x - maneggiatore.getCamera().getxOffset()), (int) (y - maneggiatore.getCamera().getyOffset()));
	}
	
	public void renderizzazione(Graphics disegnatore, int x, int y)
	{
		disegnatore.drawImage(texture, x, y, LARGHEZZAITEM, ALTEZZAITEM, null);
	}
	
	public Item creaNuovo()
	{
		Item i = new Item(texture, nome, id, (int) attesa);
		i.setPosizione(x, y);
		
		i.setAcquisito(true);
		
		return i;
	}
	
	public Item creaNuovo(int x, int y)
	{
		Item i = new Item(texture, nome, id, (int) attesa);
		i.setPosizione(x, y);
		
		return i;
	}
	
	public void setPosizione(int x, int y)
	{
		setX(x);
		setY(y);
		
		bordo.x = x;
		bordo.y = y;
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

	public BufferedImage getTexture()
	{
		return texture;
	}

	public void setTexture(BufferedImage texture)
	{
		this.texture = texture;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getId()
	{
		return id;
	}

	public boolean isAcquisito()
	{
		return acquisito;
	}

	public void setAcquisito(boolean acquisito)
	{
		this.acquisito = acquisito;
	}

	public long getTimer()
	{
		return timer;
	}

	public void setTimer(long timer)
	{
		this.timer = timer;
	}

	public long getTimerUltimaVolta()
	{
		return timerUltimaVolta;
	}

	public void setTimerUltimaVolta(long timerUltimaVolta)
	{
		this.timerUltimaVolta = timerUltimaVolta;
	}

	public long getAttesa()
	{
		return attesa;
	}

	public void setAttesa(long attesa)
	{
		this.attesa = attesa;
	}
}