
package com.GiorgioAlessio.game.entità.creatura;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.armi.Colpo;
import com.GiorgioAlessio.game.entità.Entità;
import com.GiorgioAlessio.game.grafica.Animazione;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.grafica.Testo;
import com.GiorgioAlessio.game.inventario.Inventario;
import com.GiorgioAlessio.game.inventario.ItemDellInventario;
import com.GiorgioAlessio.game.stati.Stato;
import com.GiorgioAlessio.game.stati.StatoMorte;

/*Definizione della classe "Giocatore", sottoclasse di "Creatura".
Definisce le caratteristiche delle entità "Giocatore".*/
public class Giocatore extends Creatura
{
	//Animazioni
	private Animazione animGiu;
	private Animazione animSin;
	private Animazione animSu;
	private Animazione animDes;
	
	//Timer Attacchi
	private long timerUltimoAttaccoPrimario, 
	attesaAttaccoPrimario = 160, 
	timerAttaccoPrimario = attesaAttaccoPrimario;
	
	private long timerUltimoAttaccoSecondario, 
		attesaAttaccoSecondario = 80, 
		timerAttaccoSecondario = attesaAttaccoSecondario;
	
	private long timerUltimaRicarica,
		attesaRicarica = 1000,
		timerRicarica = attesaRicarica;
	
	private long timerUltimaCura,
	attesaCura = 300,
	timerCura = attesaCura;
	
	private int dannoSecondario = 5;
	
	private Inventario inventario;
	
	private boolean curato;
	private int dannoCurato;
	private String curaUsata;
	
	private int nUccisi;
	
	public Giocatore(Maneggiatore maneggiatore, float x, float y)
	{
		super(maneggiatore, x, y, Creatura.LARGHEZZA_CREATURA_DEFAULT, Creatura.ALTEZZA_CREATURA_DEFAULT);
		
		//Viene definito il bounding box del nostro player.
		bordo.x = 54;
		bordo.y = 69;
		bordo.width = 20;
		bordo.height = 29;
		
		bordoColpi.x = 53;
		bordoColpi.y = 47;
		bordoColpi.width = 20;
		bordoColpi.height = 22;
		
		contieneFlag = false;
		
		//Animazioni
		animGiu = new Animazione(75,Risorse.player_giù);
		animSin = new Animazione(75,Risorse.player_sinistra);
		animSu = new Animazione(75,Risorse.player_su);
		animDes = new Animazione(75,Risorse.player_destra);
		
		inventario = new Inventario(maneggiatore, "Inventario", true);
		
		seGiocatore = true;
		
		curato = false;
		dannoCurato = 0;
		nUccisi = 0;
		
	}
	
	@Override
	public void aggiornamento()
	{
		if(!seRiquadroPorta())
		{
			if(!maneggiatore.getMondo().getManagerEntità().isInventarioAttivo())
			{
				//Animazioni
				animGiu.aggiornamento();
				animSin.aggiornamento();
				animSu.aggiornamento();
				animDes.aggiornamento();
				
				//Movimento
				prendiInput();
				movimento();
				ricarica();
				cura();
				maneggiatore.getCamera().focusSuEntità(this);
				
				//Controllo attacchi
				controlloAttacco();
				controlloAttaccoSecondario();
			}
			
			//Inventario
			apriInventario();
			inventario.aggiornamento();
			
			if(curato && (timerCura >= attesaCura))
			{
				curato = false;
			}
		}
		else
		{
			switch(direzione)
			{
				case 0:
					//Giu
					yMovimento = velocità;
					break;
				case 1:
					xMovimento = velocità;
					//Destra
					break;
				case 2:
					xMovimento = -velocità;
					//Sinistra
					break;
				case 3:
					yMovimento = -velocità;
					//Su
					break;
				default:
					break;
			}
			//Animazioni
			animGiu.aggiornamento();
			animSin.aggiornamento();
			animSu.aggiornamento();
			animDes.aggiornamento();
			
			movimento();
		}
	}
	
	public void controlloAttacco()
	{
		timerAttaccoPrimario = timerAttaccoPrimario + System.currentTimeMillis() - timerUltimoAttaccoPrimario;
		timerUltimoAttaccoPrimario = System.currentTimeMillis();

		if(timerAttaccoPrimario < attesaAttaccoPrimario)
		{
			return;
		}
		
		if(maneggiatore.getGestoreTasti().attaccoPrimario)
		{
			//Codice che definisce l'attacco primario
			if(!maneggiatore.getMondo().getManagerEntità().getColpi().isEmpty())
			{
				for(Colpo colpo : maneggiatore.getMondo().getManagerEntità().getColpi())
				{
					if(!colpo.isUsato())
					{
						colpo.setX(x + 62);
						colpo.setY(y + 54);
						colpo.setDirezione(direzione);
						colpo.setUsato(true);
						break;
					}
				}
			}
		}
		else
		{
			return;
		}
		
		timerAttaccoPrimario = 0;
	}
	
	public void controlloAttaccoSecondario()
	{
		timerAttaccoSecondario = timerAttaccoSecondario + System.currentTimeMillis() - timerUltimoAttaccoSecondario;
		timerUltimoAttaccoSecondario = System.currentTimeMillis();

		if(timerAttaccoSecondario < attesaAttaccoSecondario)
		{
			return;
		}
		
		Rectangle rettangoloAttacco = new Rectangle();
		Rectangle bordoColpi = getBordoColpi(0,0);
		
		int dimensioniRettangoloAttacco = 20;
		rettangoloAttacco.width = dimensioniRettangoloAttacco;
		rettangoloAttacco.height = dimensioniRettangoloAttacco;
		
		if(maneggiatore.getGestoreTasti().attaccoSu)
		{
			rettangoloAttacco.width = bordoColpi.width;
			
			//rettangoloAttacco.x = bordoCollisioni.x + (bordoCollisioni.width / 2) - (dimensioniRettangoloAttacco / 2);
			//rettangoloAttacco.y = bordoCollisioni.y - rettangoloAttacco.height;
			rettangoloAttacco.x = bordoColpi.x;
			rettangoloAttacco.y = bordoColpi.y - rettangoloAttacco.height;
		}
		else if(maneggiatore.getGestoreTasti().attaccoGiù)
		{
			rettangoloAttacco.width = bordoColpi.width;
			
			//rettangoloAttacco.x = bordoCollisioni.x + (bordoCollisioni.width / 2) - (dimensioniRettangoloAttacco / 2);
			//rettangoloAttacco.y = bordoCollisioni.y + rettangoloAttacco.height;
			rettangoloAttacco.x = bordoColpi.x;
			rettangoloAttacco.y = bordoColpi.y + bordoColpi.height;
		}
		else if(maneggiatore.getGestoreTasti().attaccoSinistra)
		{
			rettangoloAttacco.height = bordoColpi.height;
			
			//rettangoloAttacco.x = bordoCollisioni.x - rettangoloAttacco.width;
			//rettangoloAttacco.y = bordoCollisioni.y + (bordoCollisioni.height / 2) - (dimensioniRettangoloAttacco / 2);
			rettangoloAttacco.x = bordoColpi.x - rettangoloAttacco.width;
			rettangoloAttacco.y = bordoColpi.y;
		}
		else if(maneggiatore.getGestoreTasti().attaccoDestra)
		{
			rettangoloAttacco.height = bordoColpi.height;
			
			//rettangoloAttacco.x = bordoCollisioni.x + rettangoloAttacco.width;
			//rettangoloAttacco.y = bordoCollisioni.y + (bordoCollisioni.height / 2) - (dimensioniRettangoloAttacco / 2);
			rettangoloAttacco.x = bordoColpi.x + bordoColpi.width;
			rettangoloAttacco.y = bordoColpi.y;
		}
		else
		{
			return;
		}
		
		timerAttaccoSecondario = 0;
		
		for(Entità e : maneggiatore.getMondo().getManagerEntità().getEntità())
		{
			if(e.equals(this))
			{
				continue;
			}
			
			if(e.getBordoColpi(0, 0).intersects(rettangoloAttacco))
			{
				e.setDannoSubito(dannoSecondario);
				e.danno();
				e.setColpito(true);
				
				e.setTimerInizioDanno(System.currentTimeMillis());
				
				return;
			}
		}
	}
	
	/*Metodo che gestisce il movimento del giocatore in base all'input e alla velocità.*/
	public void prendiInput()
	{
		xMovimento = 0;
		yMovimento = 0;
		
		if(maneggiatore.getGestoreTasti().su)
		{
			yMovimento = -velocità;
			direzione = 3;
		}
		
		if(maneggiatore.getGestoreTasti().giù)
		{
			yMovimento = velocità;
			direzione = 0;
		}
		
		if(maneggiatore.getGestoreTasti().sinistra)
		{
			xMovimento = - velocità;
			direzione = 2;
		}
		
		if(maneggiatore.getGestoreTasti().destra)
		{
			xMovimento = velocità;
			direzione = 1;
		}
	}
	
	public void ricarica()
	{
		timerRicarica = timerRicarica + System.currentTimeMillis() - timerUltimaRicarica;
		timerUltimaRicarica = System.currentTimeMillis();

		if(timerRicarica < attesaRicarica)
		{
			return;
		}
		
		if(maneggiatore.getGestoreTasti().ricaricaMunizioni)
		{
			//Codice che definisce la ricarica dell'attacco primario
			boolean seRicarica = false;
			
			ItemDellInventario item = new ItemDellInventario(maneggiatore, Risorse.itemAmmo,1);
			
			if(inventario.getQuantità(item) > 0)
			{
				seRicarica = maneggiatore.getMondo().getManagerEntità().ricarica();
			}
			
			if(seRicarica)
			{
				inventario.riduciQuantità(new ItemDellInventario(maneggiatore, Risorse.itemAmmo,1));
			}
		}
		else
		{
			return;
		}
		
		timerRicarica = 0;
	}
	
	public void cura()
	{
		timerCura = timerCura + System.currentTimeMillis() - timerUltimaCura;
		timerUltimaCura = System.currentTimeMillis();

		if(timerCura < attesaCura)
		{
			return;
		}
		
		if(maneggiatore.getGestoreTasti().garza)
		{
			curato = false;
			
			ItemDellInventario item = new ItemDellInventario(maneggiatore, Risorse.itemGarza,1);
			
			if(inventario.getQuantità(item) > 0)
			{
				if(salute < 100)
				{
					int salutePrecedente = salute;
					salute = salute + 10;
					
					if(salute > 100)
					{
						salute = 100;
					}
					
					dannoCurato = salute - salutePrecedente;
					curato = true;
				}
			}
			
			if(curato)
			{
				inventario.riduciQuantità(new ItemDellInventario(maneggiatore, Risorse.itemGarza,1));
				curaUsata = "garza";
			}
		}
		else if(maneggiatore.getGestoreTasti().mediKit)
		{
			curato = false;
			
			ItemDellInventario item = new ItemDellInventario(maneggiatore, Risorse.itemMediKit,1);
			
			if(inventario.getQuantità(item) > 0)
			{
				if(salute < 100)
				{
					int salutePrecedente = salute;
					salute = salute + 30;
					
					if(salute > 100)
					{
						salute = 100;
					}
					
					dannoCurato = salute - salutePrecedente;
					curato = true;
				}
			}
			
			if(curato)
			{
				inventario.riduciQuantità(new ItemDellInventario(maneggiatore, Risorse.itemMediKit,1));
				curaUsata = "mediKit";
			}
		}
		else
		{
			return;
		}
		
		timerCura = 0;
	}

	@Override
	public void renderizzazione(Graphics disegnatore)
	{
		if(seRenderizzaX() && seRenderizzaY())
		{
			disegnatore.drawImage(getFrameAnimazioneCorrente(), (int) (x - maneggiatore.getCamera().getxOffset()), 
					(int) (y - maneggiatore.getCamera().getyOffset()), larghezza, altezza, null);
			
			if(colpito)
			{
				Testo.disegnaStringa(disegnatore, "-" + dannoSubito + " PV", 
						(int) (x - maneggiatore.getCamera().getxOffset() + 63), 
						(int) (y - maneggiatore.getCamera().getyOffset() - (timerDanno / 15) + 24), 
						true, Color.red, Risorse.fontDanno);
			}
			
			if(curato)
			{
				Testo.disegnaStringa(disegnatore, "+" + dannoCurato + " PV", 
						(int) (x - maneggiatore.getCamera().getxOffset() + 63), 
						(int) (y - maneggiatore.getCamera().getyOffset() - (timerCura / 15) + 24), 
						true, Color.green, Risorse.fontDanno);
				
				Testo.disegnaStringa(disegnatore, "-1 " + curaUsata, 
						(int) (x - maneggiatore.getCamera().getxOffset() + 63), 
						(int) ((y - maneggiatore.getCamera().getyOffset() - (timerCura / 15) + 24) - 20), 
						true, Color.yellow, Risorse.fontDanno);
			}
		}
		
		/*disegnatore.setColor(Color.red);
		disegnatore.fillRect((int) (x + bordo.x - maneggiatore.getCamera().getxOffset()),
				(int) (y + bordo.y - maneggiatore.getCamera().getyOffset()),
				bordo.width, bordo.height);*/
	}
	
	public void dopoRenderizzazione(Graphics disegnatore)
	{
		inventario.renderizzazione(disegnatore);
	}

	@Override
	public void morte()
	{
		Stato.setStato(new StatoMorte(maneggiatore));
	}
	
	private BufferedImage getFrameAnimazioneCorrente()
	{
		if(xMovimento < 0)
		{
			direzione = 2;
			return animSin.getFrameCorrente();
		}
		else if(xMovimento > 0)
		{
			direzione = 1;
			return animDes.getFrameCorrente();
		}
		else if(yMovimento < 0)
		{
			direzione = 3;
			return animSu.getFrameCorrente();
		}
		else if(yMovimento > 0)
		{
			direzione = 0;
			return animGiu.getFrameCorrente();
		}
		else
		{
			return Risorse.player_fermo[direzione];
		}
	}

	public Inventario getInventario()
	{
		return inventario;
	}

	public void setInventario(Inventario inventario)
	{
		this.inventario = inventario;
	}
	
	public boolean isCurato()
	{
		return curato;
	}

	public void setCurato(boolean curato)
	{
		this.curato = curato;
	}

	public int getDannoCurato()
	{
		return dannoCurato;
	}

	public void setDannoCurato(int dannoCurato)
	{
		this.dannoCurato = dannoCurato;
	}

	public String getCuraUsata()
	{
		return curaUsata;
	}

	public void setCuraUsata(String curaUsata)
	{
		this.curaUsata = curaUsata;
	}

	public int getnUccisi()
	{
		return nUccisi;
	}

	public void setnUccisi(int nUccisi)
	{
		this.nUccisi = nUccisi;
	}

	public void apriInventario()
	{
		if(maneggiatore.getGestoreTasti().tastoAppenaPremuto(KeyEvent.VK_E))
		{
			boolean questoInventario = inventario.isAttivo();
			boolean inventarioEsterno = maneggiatore.getMondo().getManagerEntità().isInventarioAttivo();
			
			if(questoInventario)
			{
				inventario.setAttivo(false);
				maneggiatore.getMondo().getManagerEntità().setInventarioAttivo(false);
			}
			else if(!questoInventario && !inventarioEsterno)
			{
				inventario.setAttivo(true);
				maneggiatore.getMondo().getManagerEntità().setInventarioAttivo(true);
			}
			else
			{
				return;
			}
		}
	}
}