package com.GiorgioAlessio.game.entità;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.GiorgioAlessio.game.Maneggiatore;

/*Definizione della classe astratta "Entità", definisce le caratteristiche
 generali delle entità*/
public abstract class Entità
{
	//Dichiarazione costanti.
	public static final int SALUTE_DEFAULT = 100;
	
	//Dichiarazione attributi. Gioco viene usato per passare la camera.
	protected Maneggiatore maneggiatore;
	protected float x,y;
	protected int larghezza, altezza;
	protected int salute;
	protected boolean attivo = true;
	
	protected boolean seGiocatore = false;
	protected boolean seNemico = false;
	protected boolean contieneFlag = true;
	protected boolean colpo = false;
	protected boolean seCassa = false;
	
	protected boolean morente,animMorente;
	
	protected boolean colpito = false;
	protected int dannoSubito;
	protected long timerInizioDanno, attesaDanno = 300, timerDanno = attesaDanno;
	
	//Parametro che definisce il rettangolo che percepisce le collisioni.
	protected Rectangle bordo;
	protected Rectangle bordoColpi;
	
	//Flag
	protected Rectangle flagDxSu, flagSxSu, flagDxGiù, flagSxGiù;
	final protected int dimensioneFlag = 10;
	final protected int distanzaFlagX = 74;
	final protected int distanzaFlagY = 98;

	public Entità(Maneggiatore maneggiatore, float x, float y, int larghezza, int altezza)
	{
		this.maneggiatore = maneggiatore;
		this.x = x;
		this.y = y;
		this.larghezza = larghezza;
		this.altezza = altezza;
		salute = SALUTE_DEFAULT;
		
		//Il bounding box avrà dimensioni uguali all'immagine del pg.
		bordo = new Rectangle(0,0,larghezza,altezza);
		bordoColpi = new Rectangle(0,0,larghezza,altezza);
		
		seGiocatore = false;
		
		morente = false;
		animMorente = false;
	}
	
	//Metodi Getter e Setter.
	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public int getLarghezza()
	{
		return larghezza;
	}

	public void setLarghezza(int larghezza)
	{
		this.larghezza = larghezza;
	}

	public int getAltezza()
	{
		return altezza;
	}

	public void setAltezza(int altezza)
	{
		this.altezza = altezza;
	}

	public int getSalute()
	{
		return salute;
	}

	public void setSalute(int salute)
	{
		this.salute = salute;
	}

	public boolean isAttivo()
	{
		return attivo;
	}

	public void setAttivo(boolean attivo)
	{
		this.attivo = attivo;
	}

	public boolean isColpito()
	{
		return colpito;
	}

	public void setColpito(boolean colpito)
	{
		this.colpito = colpito;
	}

	public long getTimerInizioDanno()
	{
		return timerInizioDanno;
	}

	public void setTimerInizioDanno(long timerInizioDanno)
	{
		this.timerInizioDanno = timerInizioDanno;
	}

	public int getDannoSubito()
	{
		return dannoSubito;
	}

	public void setDannoSubito(int dannoSubito)
	{
		this.dannoSubito = dannoSubito;
	}

	public Rectangle getFlagDxSu()
	{
		return flagDxSu;
	}

	public void setFlagDxSu(Rectangle flagDxSu)
	{
		this.flagDxSu = flagDxSu;
	}

	public Rectangle getFlagSxSu()
	{
		return flagSxSu;
	}

	public void setFlagSxSu(Rectangle flagSxSu)
	{
		this.flagSxSu = flagSxSu;
	}

	public Rectangle getFlagDxGiù()
	{
		return flagDxGiù;
	}

	public void setFlagDxGiù(Rectangle flagDxGiù)
	{
		this.flagDxGiù = flagDxGiù;
	}

	public Rectangle getFlagSxGiù()
	{
		return flagSxGiù;
	}

	public void setFlagSxGiù(Rectangle flagSxGiù)
	{
		this.flagSxGiù = flagSxGiù;
	}

	public int getDimensioneFlag()
	{
		return dimensioneFlag;
	}

	public int getDistanzaFlagX()
	{
		return distanzaFlagX;
	}
	
	public int getDistanzaFlagY()
	{
		return distanzaFlagX;
	}
	
	public boolean getSeGiocatore()
	{
		return seGiocatore;
	}

	public void setSeGiocatore(boolean seGiocatore)
	{
		this.seGiocatore = seGiocatore;
	}

	public boolean isContieneFlag()
	{
		return contieneFlag;
	}

	public void setContieneFlag(boolean seContieneFlag)
	{
		this.contieneFlag = seContieneFlag;
	}

	public boolean isColpo() {
		return colpo;
	}

	public void setColpo(boolean colpo) {
		this.colpo = colpo;
	}

	public boolean isSeCassa() {
		return seCassa;
	}

	public void setSeCassa(boolean seCassa) {
		this.seCassa = seCassa;
	}

	public boolean isSeNemico() {
		return seNemico;
	}

	public void setSeNemico(boolean seNemico) {
		this.seNemico = seNemico;
	}

	public boolean isMorente() {
		return morente;
	}

	public void setMorente(boolean morente) {
		this.morente = morente;
	}

	public boolean isAnimMorente() {
		return animMorente;
	}

	public void setAnimMorente(boolean animMorente) {
		this.animMorente = animMorente;
	}

	public abstract void aggiornamento();
	
	public abstract void renderizzazione(Graphics disegnatore);
	
	public abstract void dopoRenderizzazione(Graphics disegnatore);
	
	public abstract void morte();
	
	public Rectangle getBordoCollisioni(float xOffset, float yOffset)
	{
		return new Rectangle((int)(x + bordo.x + xOffset), (int)(y + bordo.y + yOffset),bordo.width,bordo.height);
	}
	
	public Rectangle getBordoColpi(float xOffset, float yOffset)
	{
		return new Rectangle((int)(x + bordoColpi.x + xOffset), 
				(int)(y + bordoColpi.y + yOffset),bordoColpi.width,bordoColpi.height);
	}
	
	public Entità controlloCollisioniEntità(float xOffset, float yOffset)
	{
		for(Entità e: maneggiatore.getMondo().getManagerEntità().getEntità())
		{
			if(e.equals(this))
			{
				continue;
			}
			
			if(e.getBordoCollisioni(0f, 0f).intersects(getBordoCollisioni(xOffset,yOffset)))
			{
				return e;
			}
		}
		
		return null;
	}
	
	public Entità controlloColpiEntità(float xOffset, float yOffset)
	{
		for(Entità e: maneggiatore.getMondo().getManagerEntità().getEntità())
		{
			if(e.equals(this))
			{
				continue;
			}
			
			if(e.getBordoColpi(0f, 0f).intersects(getBordoColpi(xOffset,yOffset)))
			{
				return e;
			}
		}
		
		return null;
	}
	
	public void danno()
	{
		salute = salute - dannoSubito;
		
		if(salute <= 0)
		{
			attivo = false;
			morte();
		}
	}
	
	public void definizioneFlag()
	{
		int xSin,xDes,ySu,yGiù;
		
		xDes = (int) (x + bordo.x + bordo.width);
		xSin = (int) (x + bordo.x - distanzaFlagX - dimensioneFlag);
		
		ySu = (int) (y + bordo.y - distanzaFlagY - dimensioneFlag);
		yGiù = (int) (y + bordo.y + bordo.height);
		
		flagDxSu = new Rectangle(xDes,ySu,dimensioneFlag,dimensioneFlag);
		flagSxSu = new Rectangle(xSin,ySu,dimensioneFlag,dimensioneFlag);
		
		flagDxGiù = new Rectangle(xDes,yGiù,dimensioneFlag,dimensioneFlag);
		flagSxGiù = new Rectangle(xSin,yGiù,dimensioneFlag,dimensioneFlag);
	}
}
