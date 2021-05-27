package com.GiorgioAlessio.game.entità.creatura;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.GiorgioAlessio.game.Maneggiatore;
import com.GiorgioAlessio.game.entità.Entità;
import com.GiorgioAlessio.game.entità.ManagerEntità;
import com.GiorgioAlessio.game.grafica.Animazione;
import com.GiorgioAlessio.game.grafica.Risorse;
import com.GiorgioAlessio.game.grafica.Testo;

public class Nemico extends Creatura
{
	//Animazioni
	private Animazione animGiu;
	private Animazione animSin;
	private Animazione animSu;
	private Animazione animDes;
	private Animazione animMorte;
	private int direzione = 0;
	
	private ManagerEntità managerEntità;
	
	private int xObiettivo,yObiettivo;
	private long timerUltimoAttacco, attesaAttacco = 300, timerAttacco = attesaAttacco;
	private int dannoSecondario = 10;

	private long attimoMorte = 0;
		
	public Nemico(Maneggiatore maneggiatore, float x, float y, ManagerEntità managerEntità)
	{
		super(maneggiatore, x, y, Creatura.LARGHEZZA_CREATURA_DEFAULT, Creatura.ALTEZZA_CREATURA_DEFAULT);
		
		this.managerEntità = managerEntità;
		
		//Viene definito il bounding box del nostro nemico.
		bordo.x = 54;
		bordo.y = 69;
		bordo.width = 20;
		bordo.height = 29;
		
		bordoColpi.x = 53;
		bordoColpi.y = 47;
		bordoColpi.width = 20;
		bordoColpi.height = 22;
		
		//Animazioni
		animGiu = new Animazione(75,Risorse.nemico_giù);
		animSin = new Animazione(75,Risorse.nemico_sinistra);
		animSu = new Animazione(75,Risorse.nemico_su);
		animDes = new Animazione(75,Risorse.nemico_destra);
		animMorte = new Animazione(75,Risorse.nemico_morto);
		
		velocità = 1;
		
		contieneFlag = false;
		
		seNemico = true;
	}

	@Override
	public void aggiornamento()
	{
		if(!maneggiatore.getMondo().getManagerEntità().isInventarioAttivo())
		{
			if(animMorente)
			{
				animMorte.aggiornamento();
				
				if(animMorte.getFrameCorrente().equals(Risorse.nemico_morto[3]))
				{
					animMorente = false;
					attimoMorte = System.currentTimeMillis();
					morente = true;
					
					bordo.height = 0;
					bordo.width = 0;
					bordoColpi.height = 0;
					bordoColpi.width = 0;
				}
			}
			else if(!morente)
			{
				//Animazioni
				animGiu.aggiornamento();
				animSin.aggiornamento();
				animSu.aggiornamento();
				animDes.aggiornamento();
				
				// TODO Auto-generated method stub
				xObiettivo = (int)managerEntità.getGiocatore().getX();
				yObiettivo = (int)managerEntità.getGiocatore().getY();
				
				seguiTarget(xObiettivo,yObiettivo);
				movimento();
				attaccaGiocatore();
			}
			
			if((System.currentTimeMillis() - attimoMorte) >= (6 * 1000) && morente)
			{
				attivo = false;
			}
		}
	}

	@Override
	public void renderizzazione(Graphics disegnatore)
	{
		// TODO Auto-generated method stub
		if(seRenderizzaX() && seRenderizzaY())
		{
			if(animMorente)
			{
				disegnatore.drawImage(getFrameAnimazioneCorrente(), (int) (x - maneggiatore.getCamera().getxOffset()), 
						(int) (y - maneggiatore.getCamera().getyOffset()), larghezza, altezza, null);
			}
			else if(morente)
			{
				disegnatore.drawImage(getFrameAnimazioneCorrente(), (int) (x - maneggiatore.getCamera().getxOffset()), 
						(int) (y - maneggiatore.getCamera().getyOffset()), larghezza, altezza, null);
			}
			else
			{
				disegnatore.drawImage(getFrameAnimazioneCorrente(), (int) (x - maneggiatore.getCamera().getxOffset()), 
						(int) (y - maneggiatore.getCamera().getyOffset()), larghezza, altezza, null);
			}
			
			/*disegnatore.setColor(Color.red);
			disegnatore.fillRect((int)(x + bordo.x - maneggiatore.getCamera().getxOffset()),
					(int)(y + bordo.y - maneggiatore.getCamera().getyOffset()),
					bordo.width, bordo.height);*/
			
			if(colpito && !morente)
			{
				Testo.disegnaStringa(disegnatore, "-" + dannoSubito + " PV", 
						(int) (x - maneggiatore.getCamera().getxOffset() + 63), 
						(int) (y - maneggiatore.getCamera().getyOffset() - (timerDanno / 15) + 24), 
						true, Color.orange, Risorse.fontDanno);
			}
		}
	}
	
	private BufferedImage getFrameAnimazioneCorrente()
	{
		if(!animMorente && !morente)
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
				return Risorse.nemico_fermo[direzione];
			}
		}
		else
		{
			return animMorte.getFrameCorrente();
		}
	}
	
	@Override
	public void danno()
	{
		if(!morente)
		{
			salute = salute - dannoSubito;
			
			if(salute <= 0)
			{
				morte();
			}
		}
		else
		{
			maneggiatore.getMondo().getManagerEntità().getGiocatore().setnUccisi(
					maneggiatore.getMondo().getManagerEntità().getGiocatore().getnUccisi() + 1);
		}
	}
	
	@Override
	public void morte()
	{
		// TODO Auto-generated method stub
		if(!animMorente)
		{
			maneggiatore.getMondo().setContatoreKill(maneggiatore.getMondo().getContatoreKill() + 1);
			maneggiatore.setPunteggio(maneggiatore.getPunteggio() + 10);
			
			animMorente = true;
			
			if(maneggiatore.getMondo().getManagerEntità().getNNemici() == 1)
			{
				maneggiatore.getMondo().getGestoreItems().aggiungiItem(
						Risorse.itemVittoria.creaNuovo((int) x + bordo.x,  (int) y + bordo.y));
			}
			else if(maneggiatore.getMondo().getManagerEntità().getNNemici() == 3)
			{
				maneggiatore.getMondo().getGestoreItems().aggiungiItem(
						Risorse.itemPass.creaNuovo((int) x + bordo.x, (int) y + bordo.y));
			}
			else
			{
				maneggiatore.getMondo().getGestoreItems().aggiungiItem(
						Risorse.itemAmmo.creaNuovo((int) x + bordo.x, (int) y + bordo.y));
			}
		}
	}
	
	public void seguiTarget(double xObiettivo, double yObiettivo)
	{
		double x1,x2;
		double y1,y2;
		
		x1=x;
		y1=y;
		
		x2=xObiettivo;
		y2=yObiettivo;
		
		double dis=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
		
		if(dis<=400)
		{
			xMovimento = 0;
			yMovimento = 0;
			
			if(x > xObiettivo)
			{
				xMovimento = -velocità;
				direzione = 2;
			}
			
			if(x < xObiettivo)
			{
				xMovimento = velocità;
				direzione = 1;
			}
			
			if(y > yObiettivo)
			{
				yMovimento = -velocità;
				direzione = 3;
			}
			
			if(y < yObiettivo)
			{
				yMovimento = velocità;
				direzione = 0;
			}
		}
		
		/*if(obiettivoFlag && xMovimento == 0 && yMovimento == 0)
		{
			obiettivoFlag = false;
		}*/
	}
	
	public void attaccaGiocatore()
	{
		timerAttacco = timerAttacco + System.currentTimeMillis() - timerUltimoAttacco;
		timerUltimoAttacco = System.currentTimeMillis();

		if(timerAttacco < attesaAttacco)
		{
			return;
		}
		
		Rectangle rettangoloAttacco = new Rectangle();
		Rectangle bordoColpi = getBordoColpi(0,0);
		Rectangle bordoGiocatore = managerEntità.getGiocatore().getBordoColpi(0,0);
		
		int dimensioniRettangoloAttacco = 20;
		rettangoloAttacco.width = dimensioniRettangoloAttacco;
		rettangoloAttacco.height = dimensioniRettangoloAttacco;
		
		if(direzione == 3 && (bordoColpi.y - (bordoGiocatore.y + bordoGiocatore.height)) <= dimensioniRettangoloAttacco)
		{
			rettangoloAttacco.width = bordoColpi.width;
			
			//rettangoloAttacco.x = bordoCollisioni.x + (bordoCollisioni.width / 2) - (dimensioniRettangoloAttacco / 2);
			//rettangoloAttacco.y = bordoCollisioni.y - rettangoloAttacco.height;
			rettangoloAttacco.x = bordoColpi.x;
			rettangoloAttacco.y = bordoColpi.y - rettangoloAttacco.height;
		}
		else if(direzione == 0 && ((bordoGiocatore.y)-(bordoColpi.y + bordoColpi.height)) <= dimensioniRettangoloAttacco)
		{
			rettangoloAttacco.width = bordoColpi.width;
			
			//rettangoloAttacco.x = bordoCollisioni.x + (bordoCollisioni.width / 2) - (dimensioniRettangoloAttacco / 2);
			//rettangoloAttacco.y = bordoCollisioni.y + rettangoloAttacco.height;
			rettangoloAttacco.x = bordoColpi.x;
			rettangoloAttacco.y = bordoColpi.y + bordoColpi.height;
		}
		else if(direzione == 2 && (bordoColpi.x - (bordoGiocatore.x + bordoGiocatore.width)) <= dimensioniRettangoloAttacco)
		{
			rettangoloAttacco.height = bordoColpi.height;
			
			//rettangoloAttacco.x = bordoCollisioni.x - rettangoloAttacco.width;
			//rettangoloAttacco.y = bordoCollisioni.y + (bordoCollisioni.height / 2) - (dimensioniRettangoloAttacco / 2);
			rettangoloAttacco.x = bordoColpi.x - rettangoloAttacco.width;
			rettangoloAttacco.y = bordoColpi.y;
		}
		else if(direzione == 1 && (bordoGiocatore.x - (bordoColpi.x + bordoColpi.width)) <= dimensioniRettangoloAttacco)
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
		
		timerAttacco = 0;
		
		for(Entità e : managerEntità.getEntità())
		{
			if(e.equals(this))
			{
				continue;
			}
			
			if(e.getSeGiocatore() && e.getBordoColpi(0, 0).intersects(rettangoloAttacco))
			{
				e.setDannoSubito(dannoSecondario);
				e.danno();
				e.setColpito(true);
				
				e.setTimerInizioDanno(System.currentTimeMillis());
				
				return;
			}
		}
	}
	
	@Override
	public void movimento()
	{
		Entità ex = controlloCollisioniEntità(xMovimento, 0f);
		Entità ey = controlloCollisioniEntità(0f, yMovimento);
		
		if(ex == null)
		{
			muoviX();
		}
		
		if(ey == null)
		{
			muoviY();
		}
	}

	@Override
	public void dopoRenderizzazione(Graphics disegnatore)
	{
		
	}
}