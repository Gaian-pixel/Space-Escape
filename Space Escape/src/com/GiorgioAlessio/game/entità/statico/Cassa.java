package com.GiorgioAlessio.game.entit‡.statico;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.inventario.Inventario;
import com.GiorgioAlessio.game.inventario.ItemDellInventario;

public class Cassa extends Entit‡Statica
{
	private BufferedImage[] skin = new BufferedImage[2];
	private Inventario inventario;
	
	private long timerUltimaRaccolta,
		attesaRaccolta = 100,
		timerRaccolta = timerUltimaRaccolta;
	
	private boolean cassaAperta;
	
	public Cassa(Maneggiatore maneggiatore, float x, float y, int direzione)
	{
		super(maneggiatore, x, y, 128, 128);
		seCassa = true;
		contieneFlag = false;
		
		impostaSkin(direzione);
		
		inventario = new Inventario(maneggiatore, "Cassa", false);
		
		cassaAperta = false;
		
		riempiCassa();
	}

	@Override
	public void aggiornamento() 
	{
		aperturaCassa();
		raccoltaItem();
		
		inventario.aggiornamento();
	}

	@Override
	public void renderizzazione(Graphics disegnatore) 
	{
		if(cassaAperta)
		{
			disegnatore.drawImage(skin[1],
					(int)(x - maneggiatore.getCamera().getxOffset()),
					(int)(y - maneggiatore.getCamera().getyOffset()),larghezza,altezza,null);
		}
		else
		{
			disegnatore.drawImage(skin[0],
				(int)(x - maneggiatore.getCamera().getxOffset()),
				(int)(y - maneggiatore.getCamera().getyOffset()),larghezza,altezza,null);
		}
	}
	
	public void dopoRenderizzazione(Graphics disegnatore)
	{
		inventario.renderizzazione(disegnatore);
	}

	@Override
	public void morte()
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void danno()
	{
		
	}
	
	public void impostaSkin(int direzione)
	{
		switch(direzione)
		{
			case 0:
				//giu
				skin = Risorse.cassaDavanti;
				
				bordo.x = 40;
				bordo.y = 56;
				bordo.width = 46;
				bordo.height = 4;
				
				bordoColpi.x = 40;
				bordoColpi.y = 50;
				bordoColpi.width = 46;
				bordoColpi.height = 22;
				break;
			case 1:
				//destra
				skin = Risorse.cassaDestra;
				
				bordo.x = 50;
				bordo.y = 56;
				bordo.width = 24;
				bordo.height = 4;
				
				bordoColpi.x = 50;
				bordoColpi.y = 50;
				bordoColpi.width = 24;
				bordoColpi.height = 22;
				break;
			case 2:
				//sinistra
				skin = Risorse.cassaSinistra;
				
				bordo.x = 50;
				bordo.y = 56;
				bordo.width = 24;
				bordo.height = 4;
				
				bordoColpi.x = 50;
				bordoColpi.y = 50;
				bordoColpi.width = 24;
				bordoColpi.height = 22;
				break;
			case 3:
				//su
				skin = Risorse.cassaDietro;
				
				bordo.x = 40;
				bordo.y = 56;
				bordo.width = 46;
				bordo.height = 4;
				
				bordoColpi.x = 40;
				bordoColpi.y = 50;
				bordoColpi.width = 46;
				bordoColpi.height = 22;
				break;
			default:
				break;
		}
	}
	
	public void aperturaCassa()
	{
		if(maneggiatore.getMondo().getManagerEntit‡().getGiocatore().getInventario().isAttivo())
		{
			return;
		}
		
		if(maneggiatore.getGestoreTasti().apriCassa)
		{
			int dimensioniRettangoloCassa = 20;
			
			Rectangle rettangoloCassaSu = new Rectangle(0,0,
					dimensioniRettangoloCassa,dimensioniRettangoloCassa);
			Rectangle rettangoloCassaGiu = new Rectangle(0,0,
					dimensioniRettangoloCassa,dimensioniRettangoloCassa);
			Rectangle rettangoloCassaDestra = new Rectangle(0,0,
					dimensioniRettangoloCassa,dimensioniRettangoloCassa);
			Rectangle rettangoloCassaSinistra = new Rectangle(0,0,
					dimensioniRettangoloCassa,dimensioniRettangoloCassa);
			Rectangle bordoColpi = getBordoColpi(0,0);
			
			rettangoloCassaSu.width = bordoColpi.width;
			rettangoloCassaSu.x = bordoColpi.x;
			rettangoloCassaSu.y = bordoColpi.y - rettangoloCassaSu.height;
			
			rettangoloCassaGiu.width = bordoColpi.width;
			rettangoloCassaGiu.x = bordoColpi.x;
			rettangoloCassaGiu.y = bordoColpi.y + bordoColpi.height;
			
			rettangoloCassaDestra.height = bordoColpi.height;
			rettangoloCassaDestra.x = bordoColpi.x + bordoColpi.width;
			rettangoloCassaDestra.y = bordoColpi.y;
			
			rettangoloCassaSinistra.height = bordoColpi.height;
			rettangoloCassaSinistra.x = bordoColpi.x - rettangoloCassaSinistra.width;
			rettangoloCassaSinistra.y = bordoColpi.y;
			
			if(maneggiatore.getMondo().getManagerEntit‡().getGiocatore().
					getBordoCollisioni(0f, 0f).intersects(rettangoloCassaSu) ||
				maneggiatore.getMondo().getManagerEntit‡().getGiocatore().
					getBordoCollisioni(0f, 0f).intersects(rettangoloCassaGiu) ||
				maneggiatore.getMondo().getManagerEntit‡().getGiocatore().
					getBordoCollisioni(0f, 0f).intersects(rettangoloCassaDestra) ||
				maneggiatore.getMondo().getManagerEntit‡().getGiocatore().
					getBordoCollisioni(0f, 0f).intersects(rettangoloCassaSinistra))
			{
				if(maneggiatore.getGestoreTasti().tastoAppenaPremuto(KeyEvent.VK_F))
				{
					apriCassa();
				}
			}
		}
		else
		{
			return;
		}
	}
	
	public void apriCassa()
	{
		boolean questoInventario = inventario.isAttivo();
		boolean inventarioEsterno = maneggiatore.getMondo().getManagerEntit‡().isInventarioAttivo();
		
		if(questoInventario)
		{
			inventario.setAttivo(false);
			maneggiatore.getMondo().getManagerEntit‡().setInventarioAttivo(false);
			cassaAperta = true;
		}
		else if(!questoInventario && !inventarioEsterno)
		{
			inventario.setAttivo(true);
			maneggiatore.getMondo().getManagerEntit‡().setInventarioAttivo(true);
		}
		else
		{
			return;
		}
	}
	
	public void riempiCassa()
	{
		Random random = new Random();
		
		for(int i=0; i<10; i++)
		{
			int probabilit‡ = random.nextInt(8);
			ItemDellInventario item = null;
			
			if(probabilit‡>=0 && probabilit‡<4)
			{
				item = new ItemDellInventario(maneggiatore, Risorse.itemGarza, 1);
			}
			
			if(probabilit‡>=4 && probabilit‡<7)
			{
				item = new ItemDellInventario(maneggiatore, Risorse.itemAmmo, 1);
			}
			
			if(probabilit‡ == 7)
			{
				item = new ItemDellInventario(maneggiatore, Risorse.itemMediKit, 1);
			}
			
			
			inventario.aggiungiItemsNellInventario(item);
		}
	}
	
	public void raccoltaItem()
	{
		/*private long timerUltimaRaccolta,
		attesaRaccolta = 1000,
		timerRaccolta = timerUltimaRaccolta;*/
		
		timerRaccolta = timerRaccolta + System.currentTimeMillis() - timerUltimaRaccolta;
		timerUltimaRaccolta = System.currentTimeMillis();

		if(timerRaccolta < attesaRaccolta)
		{
			return;
		}
		
		if(inventario.isAttivo() && maneggiatore.getGestoreTasti().spostaUnoInGiocatore && 
				inventario.getItemsNellInventario().size() > 0)
		{
			ItemDellInventario item = inventario.getItemSelezionato();
			inventario.riduciQuantit‡(item);
			
			maneggiatore.getMondo().getManagerEntit‡().getGiocatore().getInventario().
				aggiungiItemsNellInventario(item);
		}
		
		/*if(inventario.isAttivo() && maneggiatore.getGestoreTasti().spostaTuttoInGiocatore && 
				inventario.getItemsNellInventario().size() > 0)
		{
			int n = inventario.getQuantit‡(inventario.getItemSelezionato());
			
			for(int i=0; i<n; i++)
			{
				ItemDellInventario item = inventario.getItemSelezionato();
				inventario.riduciQuantit‡(item);
				
				maneggiatore.getMondo().getManagerEntit‡().getGiocatore().
					getInventario().aggiungiItemsNellInventario(item);
			}
		}*/
		
		/*if(inventario.isAttivo() && maneggiatore.getGestoreTasti().spostaTuttoInGiocatore && 
				inventario.getItemsNellInventario().size() > 0)
		{
			int n = inventario.getItemsNellInventario().size();
			
			for(int i=0; i<n; i++)
			{
				int m = inventario.getQuantit‡(inventario.getItemsNellInventario().get(i));
				
				for(int j=0; j<m; j++)
				{
					ItemDellInventario item = inventario.getItemSelezionato();
					inventario.riduciQuantit‡(item);
					
					maneggiatore.getMondo().getManagerEntit‡().getGiocatore().
						getInventario().aggiungiItemsNellInventario(item);
				}
			}
		}*/
		
		timerRaccolta = 0;
	}

	public Inventario getInventario()
	{
		return inventario;
	}

	public void setInventario(Inventario inventario)
	{
		this.inventario = inventario;
	}
}